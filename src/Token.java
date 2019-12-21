public class Token {
	int xCoord;
	int yCoord;
	int steps;
	int extended = 0;

	public Token(int xCoord, int yCoord, int steps) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.steps = steps;
		this.extended = 0;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getExtended() {
		return extended;
	}

	public void setExtended(int extended) {
		this.extended = extended;
	}
}
