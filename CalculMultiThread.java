//Ce code est pour 2 threads

package fr.esisar.ne441;



public class CalculMultiThread extends Thread {
	
	long start;
	long end;
	double res = 0;
	
	public CalculMultiThread(long start, long end)
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
		
		CalculMultiThread cmt1 = new CalculMultiThread(1, 1000000);
		CalculMultiThread cmt2 = new CalculMultiThread(1000001, 2000000);
		
		cmt1.start();
		cmt2.start();
		
		cmt1.join();
		cmt2.join();
		
		double resultat = cmt1.res + cmt2.res;
		System.out.println("Le résultat obtenu est: " + resultat);
		
		long stopTime = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stopTime-startTime)+" ms");
	}
}
