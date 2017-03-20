package output;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientOutput extends TextOutput{
	private Socket Client;
	
	private void print() throws IOException{
		OutputStream out = Client.getOutputStream();
		out.write(Message.getBytes());
	}
	
	public ClientOutput(String out, Socket sock) throws IOException {
		// TODO Auto-generated constructor stub
		super(out);
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
