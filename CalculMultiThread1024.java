//Ce code est pour 1024 threads

package fr.esisar.ne441;

import java.util.ArrayList;
import java.util.List;

public class CalculMultiThread1024 extends Thread {
	
	long start;
	long end;
	double res = 0;
	
	public CalculMultiThread1024(long start, long end)
	{
		this.start = start;
		this.end = end;
	}
	public void run(){
		// TODO Auto-generated method stub
		for (long i = start; i <= end; i++) {
			res += 1d/(i*i);
		}
	}
	
	public static void main(String[] args ) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		double resultat = 0;
		
		List<CalculMultiThread1024> Threads = new ArrayList<CalculMultiThread1024>();

		for (long i = 0; i <= 1024; i++)
		{
			CalculMultiThread1024 cmt = new CalculMultiThread1024((i*2000000000)/1024+1, (i+1)*2000000000/1024);
			Threads.add((int)i,cmt);
		}
		
		for (int j = 0; j <= 1024; j++) 
		{
			Threads.get(j).start();
		}
		
		for (int k = 0; k <= 1024; k++)
		{
			Threads.get(k).join();
		}
		
		for (int l = 0; l <= 1024; l++)
		{
			resultat += Threads.get(l).res;
		}
		
		System.out.println("Le résultat obtenu est: " + resultat);
		
		long stopTime = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stopTime-startTime)+" ms");
	}
}
