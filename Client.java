import java.net.*;
import java.io.*;
import java.util.logging.*;

public class Client {
	int port;
	String fqdn;
	Socket sock = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;

	public static void main(String[] args) {
		int port = 3333;
		String fqdn = "10.0.0.16";
		Client client = new Client(port, fqdn);
		client.Main();
	}

	public Client(int port, String fqdn) {
		this.port = port;
		this.fqdn = fqdn;
	}

	public void Main() {
		String greeting = "";
		try {
			connect();
			greeting = read();
			System.out.println(greeting);
		} catch (IOException e) {
			System.out.println("error");
		}
	}

	public void connect() throws IOException {
		sock = new Socket(fqdn, port);
		dis = new DataInputStream(sock.getInputStream());
		dos = new DataOutputStream(sock.getOutputStream());
		sock.setSoTimeout(5000);
	}

	public String read() throws IOException {
		String g = dis.readUTF();
		return g;
	}

}
