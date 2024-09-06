package esisasr.ne441.tdm01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class Client {
	public static void main(String[] args) throws Exception
	{
		Client client = new Client();
		client.execute();
	}
	
	private void execute() throws IOException
	{
		System.out.println("Démarrage du client...");
		DatagramSocket socket = new DatagramSocket();
		InetSocketAddress dest = new InetSocketAddress("localhost", 2000);
		byte[] message = new String("Hello\n").getBytes();
		DatagramPacket packet = new DatagramPacket(message, message.length, dest);
		socket.send(packet);
		System.out.println("Message envoyé");
		
		socket.close();
		System.out.println("Arrêt du client");
	}
}
