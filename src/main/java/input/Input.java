package input;

import java.io.IOException;

/**
 *Interface for all Inputs<br><br>
 *{@code Methods}:<br>
 *{@link printInput}
 * 
 *@author Jonas Grunert
 *@see TextInput
 *@see LineReaderInput
 *@see ClientReaderInput
 */
interface Input {
	
	/**
	 * General method header for asking for an input<br>
	 * requires the Method so it is for every input the same name
	 * @throws IOException
	 */
	public void printInput() throws IOException;

}
