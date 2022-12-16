import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class tcpServer {


	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(2000);
			Socket socket = serverSocket.accept();
			
			Scanner socketScanner = new Scanner(socket.getInputStream());
			String fileName = socketScanner.nextLine().trim();
			
			PrintStream printstream = new PrintStream(socket.getOutputStream());
			
			File file = new File(fileName);
			
			if(file.exists()) {
				Scanner fileScanner = new Scanner(file);
				while(fileScanner.hasNextLine())
					printstream.println(fileScanner.nextLine());
				fileScanner.close();
			}else{
				printstream.println("Error: File doesnt exists");
			}
			socket.close();
			serverSocket.close();
			socketScanner.close();
		}catch(IOException e){
			System.out.print(e.getMessage());
		}

	}

}
