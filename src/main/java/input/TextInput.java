package input;
/**
 * implements {@link input}<br><br>
 * {@code Abstract} class, which is the basic class for all textual inputs<br><br>
 *{@code Variables}:<br>
 *{@link Message}<br>
 *{@link InputText}<br>
 *{@link output}<br><br>
 *{@code Methods}:<br>
 *{@link printInput}<br>
 *{@link getInput}<br>
 *{@link getInputText}<br>
 *{@link getInputNumber}<br>
 * @author Jonas Grunert
 * @see input
 * @see LineReaderInput
 *
 */
public abstract class TextInput implements Input{
	/**{@link String}: Message, which explains the required input to the user   
	 */
	protected String Message;
	/** {@link String}: input, which was given by the user
	 */
	protected String InputText;
	
	/** {@code Abstract} Handler<br>
	 * Initializes based on the {@link Message} to give the  Client on connect
	 * @param InputMessage
	 */
	public TextInput(String InputMessage){
		Message = InputMessage;
	}
	
	/**
	 * gives back the {@link InputText}
	 * @return {@link String}The input Text
	 */
	public String getInputText() {
		return InputText;
	}
	
	/**
	 * gives back the {@link InputText} in Integer format
	 * @return the InputText in int
	 */
	public int getInputNumber() {
		try {
			return Integer.parseInt(InputText);
		} catch (NumberFormatException e){
			throw e;
		}
	}
	
	/**
	 * sets a new welcome message
	 * @param {@link String} Message, which is to explain the input in detail
	 */
	public void setMessage(String message) {
		Message = message;
	}
}
