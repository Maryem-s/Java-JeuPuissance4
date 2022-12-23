package app.model;


public class Coups {
	Partie partie;
	Integer counter;
	private Position pos;

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	public Coups(Position posDepart) {
		this.pos = posDepart;
	}
	
	public Coups(Integer counter, Position posDepart) {
		this.counter = counter;
		this.pos = posDepart;
	}

	public Position getPos() {
		return pos;
	}

	public void setPosDepart(Position pos) {
		this.pos = pos;
	}


	@Override
	public String toString() {
		return "Coup [pos=" + pos +"]";
	}


}
