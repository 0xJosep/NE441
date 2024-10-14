package tdm03;

public class Compteur {

	   private int increment;

	public Compteur() {
		super();
		this.increment = 0;
	}
	 
	public int incrementer() {
		   synchronized(this) {
			   increment++;
			   return increment;
		   }
	   }
	}