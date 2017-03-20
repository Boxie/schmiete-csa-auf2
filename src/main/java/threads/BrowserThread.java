package threads;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import output.WebOutput;
import network.SocketHandler;

public class BrowserThread extends Thread {	
	static final String[][] mimetypes = {
		{"html", "text/html"},
		{"htm", "text/html"},
		{"txt", "text/plain"},
		{"gif", "image/gif"},
		{"jpg", "image/jpeg"},
		{"jpeg", "image/jpeg"}
	};
	 
	private SocketHandler Client;
	private String pages;
	
	private String Command;
	private String URL;
	private String HTTPVersion;
	
	private PrintStream Output;
	
	public BrowserThread(Socket client, String Place, PrintStream Out) throws UnknownHostException{
		Client = new SocketHandler(client, Output);
		pages = Place;
		Output = Out;
	}
	
	public void run(){
		try {
			Output.println("Request from "+Client.getAddress());
			readRequest();
			createResponse();
			Client.closeConnection();
			Output.println("Finished Request from "+Client.getAddress());
		} catch (IOException e) {
			Output.println("Problem occured with Client "+Client.getAddress());
			Output.println(e.getStackTrace());
		}
	}
	
	private void readRequest() throws IOException{
		String ClientR = Client.getInput();
		Output.println(ClientR);
		int pos = ClientR.indexOf(' ');
		if (pos != -1){
			Command = ClientR.substring(0, pos).toUpperCase();
			ClientR = ClientR.substring(pos +1);
			pos = ClientR.indexOf(' ');
			if (pos != -1){
				URL = ClientR.substring(0, pos);
				ClientR = ClientR.substring(pos +1);
				pos = ClientR.indexOf(' ');
				if (pos != -1){
					HTTPVersion = ClientR;
				}
			}
		}
	}
	
	private void createResponse() throws IOException {
		if (Command.equals("GET")){
			if (URL.startsWith("/")){
				String mimestring = "application/octet-stream";
				for (int i=0; i<mimetypes.length; i++){
					if (URL.endsWith(mimetypes[i][0])){
						mimestring = mimetypes[i][1];
						break;
					}
				}
				String seperator = System.getProperty("file.seperator", "/");
				String file = pages;
				if (URL.length()!=1){
					for (int j=0; j<URL.length(); j++){
						if (URL.charAt(j) == '/'){
							file+=seperator;
						} else {
							file+=URL.charAt(j);
						}
					}
				} else {
					file += seperator+"index.html"; 
					mimestring = "text/html";
				}
				try {
					Output.println(file);
					Output.println();
					Output.println();
					FileInputStream File = new FileInputStream(file);
					byte[] content = new byte[10000]; 
					File.read(content);
					WebOutput Out = new WebOutput("HTTP/1.0 200 OK\r\n", Client.getSock());
					Out.setMessage("Server : ExpermintalWebserver\r\n");
					Out.setMessage("Content-type: " + mimestring + "\r\n\r\n");
					Out.setMessage(new String ( content, StandardCharsets.UTF_8));
					File.close();
				} catch (FileNotFoundException e){
					HTTPError(404, "File not found" );
				} catch (IOException e){
					HTTPError(404, "Requested File not readable");
				}
			} else{
				HTTPError(400, "Bad Request");
			}
		}
	}
	
	private void HTTPError(int Code, String Error) throws IOException{
		WebOutput out = new WebOutput("HTTP/1.0 " + Code + " " + Error + "\r\n",Client.getSock());
		out.setMessage("Content-type: text/html\r\n\r\n");
		out.setMessage("<html>\r\n");
		out.setMessage("<head>\r\n");
		out.setMessage("<title>ExperimentalWebServer-Error</title>\r\n");
		out.setMessage("</head>\r\n");
		out.setMessage("<body>\r\n");
		out.setMessage("<h1>HTTP/1.0 " + Code + "</h1>\r\n");
		out.setMessage("<h3>" + Error + "</h3>\r\n");
		out.setMessage("</body>\r\n");
		out.setMessage("</html>\r\n");
	}
}
