package network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import output.ClientOutput;
import input.LineReaderInput;
import threads.BrowserThread;

public class ServerSocketHandler{
	protected ServerSocket Server;
	private int Port;
	private String WelcomeMessage;
	private String CloseMessage;
	private SocketHandler Client; 
	private String pages;
	private PrintStream Output;
	private boolean running = false;
	
	private void init() throws IOException{
		Server = new ServerSocket(Port);
		Output.println("Server started @ "+InetAddress.getLocalHost());
		running = true;
	}
	
	private void open() throws IOException{
		Client = new SocketHandler(Server.accept(), Output);
		Output.println("CONNECTION: "+Client.getNetworkAddress().getAddress());
	}
	
	private void close() throws IOException{
		try {
			Server.close();
		} catch (SocketException e){
			
		}
		Output.println("Server Closed.");
		running = false;
	}
	
	public ServerSocketHandler (int port, String message, PrintStream Out) throws IOException{
		CloseMessage = "Connection closed.";
		Port = port;
		WelcomeMessage = message;
		Output = Out;
	}
	
	public ServerSocketHandler (String Place, PrintStream Out){
		Output = Out;
		pages=Place;
	}
	
	public void TelNetaccept() throws IOException{
		open();
		Socket SClient = Client.sock;
		ClientOutput out = new ClientOutput(WelcomeMessage, SClient);
		out.setMessage("\n");
		Output.println("Connection with "+Client.getAddress());
		LineReaderInput in = new LineReaderInput("Echo", (PrintStream) SClient.getOutputStream(), SClient.getInputStream());
		while (!in.getInput().equals("end")){
			out.setMessage(in.getInputText());
			out.setMessage("\n");
			Output.println(in.getInputText());
		}
		out.setMessage(CloseMessage);
		SClient.close();
		Client.closeConnection();
	}
	
	public void startTelnet() throws IOException {
		init();
	}
	
	public void endTelnet() throws IOException{
		close();
	}
	
	public void startExperimentalWebserver() throws IOException{
		Port=80;
		init();
	}
	
	public void runExperimentalWebserver() throws IOException{
		Socket socket = Server.accept();
		(new BrowserThread(socket, pages, Output)).start();
	}
	
	public void endExperimentalWebserver() throws IOException{
		close();
	}
	
	public void ExperimentalWebserver() throws IOException{
		startExperimentalWebserver();
		while (true){
			runExperimentalWebserver();
		}
	}
	
	public int getPort() {
		return Port;
	}

	public void setPort(int port) throws IOException {
		Port = port;
		init();
	}

	public ServerSocket getServer() {
		return Server;
	}

	public String getMessage() {
		return WelcomeMessage;
	}

	public void setMessage(String message) {
		WelcomeMessage = message;
	}

	public String getCloseMessage() {
		return CloseMessage;
	}

	public void setCloseMessage(String closeMessage) {
		CloseMessage = closeMessage;
	}
	
	public boolean isRunning(){
		return running;
	}
}
