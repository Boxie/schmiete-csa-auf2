package output;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class WebOutput extends TextOutput {
	private Socket Client;
	
	private void print() throws IOException{
		PrintStream out = new PrintStream(Client.getOutputStream());
		out.print(Message);
	}
	
	public WebOutput(String message, Socket sock) throws IOException {
		super(message);
		// TODO Auto-generated constructor stub
		Client = sock;
		print();
	}
	
	public void setMessage(String message) throws IOException {
		Message = message;
		print();
	}
	
	public void write() throws IOException{
		print();
	}

}
