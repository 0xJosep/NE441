package fr.esisar.ne441;

public class CalculMonoThread{
	public static void main(String[] args) {
		CalculMonoThread cmt = new CalculMonoThread();
		cmt.execute();
	}
	private void execute() {
		// TODO Auto-generated method stub
		double somme = (double) 0;
		for (long i=1; i<2000000000; i++) {
			somme += 1d/(i*i);
		}
		System.out.println("La valeur obtenue est: " + somme);
	}
}
