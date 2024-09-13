package esisar.fr.tdm3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class JeuTCP {
	public static void main(String[] args) throws IOException {
		JeuTCP jeu = new JeuTCP();
		jeu.execute();
	}
	private void execute() throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket();
		
		InetSocketAddress adrDest = new InetSocketAddress("192.168.130.150", 7500);
		socket.connect(adrDest);
		
		while (true) {
	        byte[] bufR = new byte[2048];
	        InputStream is = socket.getInputStream();
	        int lenBufR = is.read(bufR);
	         
	        if (lenBufR != -1) {
	        	String message = new String(bufR, 0, lenBufR);
	        	System.out.println("Message reçu: " + message);
	        	
	        	String[] operations = message.split("=?");
	        	for (String t : operations) {
	        			System.out.println(t);
	        			int index = t.indexOf('+');
	        			System.out.println(index);
	        			int operande1 = Integer.parseInt(t.substring(0, index));
	        			int operande2 = Integer.parseInt(t.substring(index+1));
	        			System.out.println(operande1 + operande2 + ';');
	        		}
	        	}
	        }
		}
	}
