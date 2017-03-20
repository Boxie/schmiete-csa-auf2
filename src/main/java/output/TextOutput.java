package output;

public abstract class TextOutput{
	protected String Message;
	
	public TextOutput(String message) {
		// TODO Auto-generated constructor stub
		Message = message;
	}
	
	public String getMessage() {
		return Message;
	}
}
