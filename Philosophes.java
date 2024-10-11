package exo1;

import java.util.ArrayList;
import java.util.List;

import tdm.tdm07.exo3.checker.CodeChecker;

public class Philosophes extends Thread {
	int index;
	Arbitre arbitre;

	public Philosophes(int index, Arbitre arbitre) {
		this.index = index;
		this.arbitre = arbitre;
	}

public class Arbitre {
	public int[] baguettes = new int[51];
	for(int i = 0;i<51;i++)
		{
			baguettes[i] = 0;
		}

	public boolean autorisation(int index)
	{
		if (index != 50) {
			if (baguettes[index] == 0 && baguettes[index+1] == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (baguettes[index] == 0 && baguettes[0] == 0) {
				return true;
			} else {
				return false;
			}
		}
		
	}

	public void liberation(int index)
	{
		if (index != 50) {
			baguettes[index] = 0;
			baguettes[index+1] = 0;
		} else {
			baguettes[index] = 0;
			baguettes[0] = 0;
		}
	}
	}

	synchronized public void manger() throws InterruptedException {
		if (Arbitre.autorisation(index) == true) {
			double tempsManger = Math.random() * 10;
			CodeChecker.startEating(index);
			// System.out.println("Le philosophe " + index +" est entrain de manger pour " +
			// tempsManger + "s");
			Thread.sleep(1000);
			CodeChecker.stopEating(index);
		}
	}

	synchronized public void discuter() throws InterruptedException {
		double tempsDiscuter = Math.random() * 10;
		// System.out.println("Le philosophe " + index + " est entrain de discuter pour
		// " + tempsDiscuter + "s");
		Thread.sleep(1000);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			discuter();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			manger();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Arbitre arbitre = new Arbitre();
		List<Philosophes> philosophes = new ArrayList<Philosophes>();

		for (int i = 0; i < 50; i++) {
			Philosophes philosophe = new Philosophes(i, arbitre);
			philosophes.add(i, philosophe);
		}
		for (int k = 0; k < 50; k++) {
			philosophes.get(k).start();
		}
		for (int l = 0; l < 50; l++) {
			philosophes.get(l).join();
		}
	}
}
