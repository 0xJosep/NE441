package test_tp_tcp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		CopyFile client = new CopyFile();
		long start = System.currentTimeMillis();
		client.execute();
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = " + (stop - start) + " ms");
	}

	private void execute() throws IOException {
		System.out.println("Début lecture du fichier serveur");

		FileInputStream fis = new FileInputStream("/home/userir/file1");
		FileOutputStream fos = new FileOutputStream("/home/userir/file2");
		byte[] buf = new byte[100];

		int len = fis.read(buf);
		while (len != -1) {

			fos.write(buf, 0, len);
			len = fis.read(buf);
		}
		fis.close();
		fos.close();

		System.out.println("Fin lecture du fichier serveur");
		System.out.println("Fin d'écriture");
	}

}
