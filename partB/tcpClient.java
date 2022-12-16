import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class tcpClient {


	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 2000);
			
			Scanner cScan = new Scanner(System.in);
			System.out.println("Enter File Name");
			String fileName = cScan.nextLine();
			
			PrintStream pstream = new PrintStream(socket.getOutputStream());
			pstream.println(fileName);
			
			Scanner socketScanner = new Scanner(socket.getInputStream());
			
			while(socketScanner.hasNextLine()){
				System.out.println(socketScanner.nextLine());
			}
			socket.close();
			socketScanner.close();
			cScan.close();
		}catch(IOException e){
			System.out.print(e.getMessage());
		}

	}

}
