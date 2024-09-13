package esisar.fr.tdm3;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceptionTCP {
	public static void main(String[] args) throws IOException {
		ReceptionTCP reception = new ReceptionTCP();
		reception.execute();
	}
	private void execute() throws IOException{
		// TODO Auto-generated method stub
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(6970));
		
		System.out.println("Attente de la connexion d'un client.");
		Socket socketConnection = socketEcoute.accept();
		
		System.out.println("Un client est connecté");
		System.out.println("Adresse IP: " + socketConnection.getInetAddress());
		System.out.println("Numéro de port: " + socketConnection.getPort());
		
		while (true) {
			byte[] bufR = new byte[2048];
			InputStream is = socketConnection.getInputStream();
			int lenBufR = is.read(bufR);
			if (lenBufR != -1) {
				String message = new String(bufR, 0, lenBufR);
				System.out.println("Message reçu: " + message);
			}
		}
		
	}
}
