import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UdpClient {

	
	public static void main(String[] args) {
		try{
		DatagramSocket ds = new DatagramSocket(1234);
		
		byte[] buffer;
		DatagramPacket dp;
		System.out.println("Message Recieved");
		while(true){
			buffer = new byte[65535];
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			String recieved = new String(buffer).trim();
			System.out.println(recieved);
			
			if(recieved.equalsIgnoreCase("exit")){
				ds.close();
				break;
			}
		}

	}catch(IOException e){
		e.printStackTrace();
	}
	

	}
}
