package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * implements {@link input}<br><br>
 * class, which is the basic handler class for an InetAdress<br>
 * it provides everything needed to validate an IPAddress or URL<br><br>
 * {@code Variables}:<br>
 *{@link Address}<br>
 *{@link network}<br><br>
 *{@code Methods}:<br>
 *{@link getNetwork}<br>
 *{@link setNetwork}<br>
 *{@link getAddress}<br>
 *{@link getIP}<br>  
 * @author Jonas Grunert
 *@see InetAddress
 */
public class InetAddressHandler{
	/**
	 * {@link String} Address of the requested Domain
	 */
	private String Address;
	/**
	 * {@link InetAddres} Actual Object Presentation of the requested Domain
	 */
	protected InetAddress Network;
	
	/**
	 * private method of getting the Requested Domain Object
	 * @throws UnknownHostException
	 */
	private void connect() throws UnknownHostException{
		Network = InetAddress.getByName(Address);
	}
	
	/**
	 * Handler to use InetAdress Objects <br>
	 * Initializes based on the {@link Address} for requesting the Domain
	 * @param {@link String} IPorURL
	 * @throws UnknownHostException
	 */
	public InetAddressHandler (String IPorURL) throws UnknownHostException{
		Address = IPorURL;
		connect();
	}

	/**
	 * returns the actual Connection Object
	 * @return {@link InetAddress} the Connection Object
	 */
	public InetAddress getNetwork() {
		return Network;
	}

	/**
	 * changes the address of the object and immediately updates the Connection
	 * @param {@link String} Name of the new Domain
	 * @throws UnknownHostException
	 */
	public void setNetwork(String network) throws UnknownHostException {
		Address = network;
		connect();
	}
	
	/**
	 * returns the Domain Host Name
	 * @return {@link String} Domain Host Name
	 */
	public String getAddress() {
		return Network.getHostName();
	}
	
	/**
	 * returns the Domain Host IP Address
	 * @return {@link String} Domain IP Address
	 */
	public String getIP() {
		return Network.getHostAddress();
	}

}
