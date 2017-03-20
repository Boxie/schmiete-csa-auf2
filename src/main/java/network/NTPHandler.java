package network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
/**
 * implements {@link input}<br><br>
 * class, which is the basic handler class for a NTPClient to request the Time via a NTPProtocol<br>
 * it provides everything needed to get a message and the time from the NTP Server<br><br>
 * {@code Variables}:<br>
 *{@link NetworkAddress}<br>
 *{@link Client}<br>
 *{@link Message}<br>
 *{@link Time}<br>
 *{@link output}<br><br>
 *{@code Methods}:<br>
 *{@link getMessage}<br>
 *{@link getMessageString}<br>
 *{@link getStratumString}<br>
 *{@link getTime}<br>
 *{@link setNetworkAddress}<br>
 *{@link refresh}<br>
 *{@link printMessage}<br>
 *{@link printStratum}<br>
 *{@link printDate}<br>  
 * @author Jonas Grunert
 *@see InetAddress
 *@see InetrAdressHandler
 *@see NTUPDClient
 *@see NtpV3Packet
 */
public class NTPHandler{
	/**
	 * {@link InetAddressHandler} The Connection to the Server
	 */
	private InetAddressHandler NetworkAddress;
	/**
	 * {@link NTPUDPClient} The Client, which requests the Time from the Server
	 */
	protected NTPUDPClient Client;
	/**
	 * {@link NtpV3Packet} The Message, which contains all Information from the server
	 */
	private NtpV3Packet Message;
	/**
	 * {@link Date} The Time decoded from the Message
	 */
	private Date Time;
	/**
	 * {@link PrintStream} The output where the results are to print
	 */
	private PrintStream Output;
	
	/**
	 * private connection method, which opens the connection, requests the data, decodes and saves it into the variables
	 * @throws IOException
	 */
	private void connect() throws IOException{
		Client = new NTPUDPClient();
		TimeInfo timeStamp = Client.getTime(NetworkAddress.getNetwork());
		Message = timeStamp.getMessage();
		long serverTime = Message.getTransmitTimeStamp().getTime();
		Time = new Date(serverTime);
	}
	
	/**
	 * Handler to use the NTPProtocol<br>
	 * initializes based on the given {@link NetworkAddress} where the Server is and the given {@link output} to which print the output
	 * @param {@link InetAddressHandler} NetworkTime
	 * @param {@link PrintStream} out
	 * @throws IOException
	 */
	public NTPHandler (InetAddressHandler NetworkTime, PrintStream out) throws IOException{
		NetworkAddress = NetworkTime;
		connect();
		Output = out;
	}
	
	/**
	 * 
	 * @return {@link NtpV3Packet} Mesagge, which the Client received
	 */
	public NtpV3Packet getMessage() {
		return Message;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMessageString(){
		return Message.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getStratumString(){
		return Message.getStratum();
	}

	/**
	 * 
	 * @return
	 */
	public Date getTime() {
		return Time;
	}

	/**
	 * 
	 * @param networkAddress
	 * @throws IOException
	 */
	public void setNetworkAddress(InetAddressHandler networkAddress) throws IOException {
		NetworkAddress = networkAddress;
		connect();
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public Date refresh() throws IOException{
		connect();
		return Time;
	}
	
	/**
	 * 
	 */
	public void printMessage(){
		Output.println(Message.toString());
	}
	
	/**
	 * 
	 */
	public void printStratum(){
		Output.println("Stratum: "+Message.getStratum());
	}
	
	/**
	 * 
	 */
	public void printDate(){
		Output.println("Aktuelle zeit von "+NetworkAddress.getAddress()+": "+Time);
	}

}
