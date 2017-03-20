package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
/**
 * implements {@link input}<br><br>
 * class to get and store Console Inputs
 * Normally called by Main program to get user input<br><br>
 *{@code Variables}:<br>
 *{@link Message}<br>
 *{@link InputText}<br>
 *{@link output}<br><br>
 *{@code Methods}:<br>
 *{@link printInput}<br>
 *{@link getInput}<br>
 *{@link getInputText}<br>
 *{@link getInputNumber}<br>
 *{@link setMessage}<br>  
 * @author Jonas Grunert
 * @see input
 * @see TextInput
 */
public class LineReaderInput extends TextInput{
	/**
	 * {@link PrintStream} Place to Print the output
	 */
	private PrintStream Output;
	
	/**
	 * {@link InputStream} PLace frome where to get the input
	 */
	private InputStream Input;
	
	/**
	 * Writes a Request into the PrintStream, Asks for input, and writes to internal Variable {@link InputText}<br>
	 * @throws IOException
	 */
	private void print() throws IOException{
		Output.println("Console input : "+Message);
		BufferedReader din = new BufferedReader(new InputStreamReader(Input));
		InputText = din.readLine();
	}

	/**
	 * Handler to read ClientInput <br>
	 * Uses super {@link TextInput}<br>
	 * Initializes based on the {@link Message} for requesting input, and the PrintStream where it gets output {@link output}
	 * @param {@link String}Message to be displayed
	 * @param {@link PrintStream} output to where the Text should go 
	 * @throws IOException 
	 */
	public LineReaderInput(String UserMessage, PrintStream Out, InputStream In) throws IOException {
		// TODO Auto-generated constructor stub
		super(UserMessage);
		Output = Out;
		Input = In;
		print();
	}
	
	/**
	 * just requests the input 
	 * @throws IOException
	 */
	public void printInput() throws IOException{
		print();
	}
	
	/**
	 * requests input and immediately returns the input
	 * @return {@link String} {@link InputText}
	 * @throws IOException
	 */
	public String getInput() throws IOException{
		print();
		return this.InputText;
	}
}