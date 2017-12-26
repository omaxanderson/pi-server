import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Server {

	int port;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	FileHandler fh = null;
	private static Logger LOGGER = Logger.getLogger("info");


	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.Main();
	}
	
	public Server() {
		port = 3333;
		try {
			fh = new FileHandler("server.log");
			LOGGER.addHandler(fh);
			LOGGER.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (IOException e) {
			System.err.println("error creating/opening log file");
		}

		LOGGER.info("Server port: " + port);
	}

	public void Main() throws IOException {
		ServerSocket listener = new ServerSocket(port);

		while(true) {
			try {
				Socket socket = listener.accept();
				System.out.println("connected");
				connect(socket);
				sendGreeting();
		
				socket.close();
			} catch (Exception e) {
				LOGGER.info("error: " + e);
			}
		}
	}

	public void connect(Socket s) throws IOException {
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			s.setSoTimeout(10000);
			LOGGER.info("connected to client");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "error " + e);
		}
	}

	public void sendGreeting() { 
		try {
			dos.writeUTF("hello from max's server!");
			dos.flush();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error " + e);
		}
	}

}
