import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpServer {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try {
			DatagramSocket ds = new DatagramSocket();
			InetAddress clientAdd = InetAddress.getByName("127.0.0.1");

			String line;
			byte[] buffer;
			DatagramPacket dp;

			System.out.println("Enter the message to send");

			while (true) {
				line = s.nextLine();
				buffer = line.getBytes();
				dp = new DatagramPacket(buffer, buffer.length, clientAdd, 1234);
				ds.send(dp);

				if (line.equalsIgnoreCase("exit")) {
					ds.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.close();
	}

}
