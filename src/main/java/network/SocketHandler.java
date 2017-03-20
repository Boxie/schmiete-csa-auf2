package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import output.ClientOutput;

public class SocketHandler{
	protected Socket sock;
	private byte[] Input;
	private int InputLength;
	private String Out;
	private InetAddressHandler NetworkAddress;
	private int Port;
	private String File;
	private PrintStream Output;
	
	private void connect() throws IOException, UnknownHostException{
		sock = new Socket(NetworkAddress.getNetwork(), Port);
	}
	
	private void disconnect() throws IOException{
		sock.close();
	}

	public SocketHandler(InetAddressHandler Address, int ConnPort, PrintStream out) throws UnknownHostException, IOException{ 
		NetworkAddress = Address;
		Port = ConnPort;
		Input = new byte[1000];
		Output = out;
		connect();
		disconnect();
	}
	
	public SocketHandler(Socket Socket, PrintStream out) throws UnknownHostException{ 
		sock = Socket;
		NetworkAddress = new InetAddressHandler(Socket.getInetAddress().getHostAddress());
		Port = Socket.getPort();
		Input = new byte[1000];
		Output = out;
	}
	
	public SocketHandler(String URL, PrintStream out) throws UnknownHostException{
		int pos = URL.indexOf("/");
		String Host;
		Output = out;
		if (pos != -1){
			Host = URL.substring(0, pos);
			File = URL.substring(pos);
		} else {
			Host = URL;
			File = "index.html";
		}
		NetworkAddress = new InetAddressHandler(Host);
		Input = new byte[100];
	}

	public void writeInput(){
		Output.write(Input, 0, InputLength);
	}
	
	public String getInput() throws IOException{
		InputStream in = sock.getInputStream();
		InputLength = in.read(Input);
		return new String(Input, StandardCharsets.UTF_8); 
	}
	
	public void setOutput (String output) throws IOException{
		Out=output;
		ClientOutput out = new ClientOutput(Out, sock);
	}
	
	public void getOutput () throws IOException{
		ClientOutput out = new ClientOutput(Out, sock);
	}
	
	public void refresh() throws UnknownHostException, IOException{
		connect();
		disconnect();
	}
	
	public void reconnect(InetAddressHandler networkAddress, int port) throws UnknownHostException, IOException{
		NetworkAddress = networkAddress;
		Port = port;
		connect();
		disconnect();
	}
	
	public InetAddressHandler getNetworkAddress() {
		return NetworkAddress;
	}

	public void setNetworkAddress(InetAddressHandler networkAddress) throws IOException {
		NetworkAddress = networkAddress;
		disconnect();
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) throws UnknownHostException, IOException {
		Port = port;
		connect();
		disconnect();
	}
	
	public Socket openConnection() throws UnknownHostException, IOException{
		connect();
		return sock;
	}
	
	public Socket getSock() {
		return sock;
	}

	public void closeConnection() throws IOException{
		disconnect();
	}
	
	public String getAddress(){
		return sock.getInetAddress().getHostAddress();
	}
	
	public void HTTPConnection() throws UnknownHostException, IOException{
		Port = 80;
		connect();
		Input = new byte[1000000];
		String output = "GET " + File + " HTTP/1.0" + "\r\n\r\n";
		setOutput(output);
		getInput();
		closeConnection();
	}
	
	public void TimeConnection() throws UnknownHostException, IOException{
		Port = 13;
		connect();
		getInput();
		closeConnection();
	}
}
