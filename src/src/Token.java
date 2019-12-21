public class Token {
	int xCoord;
	int yCoord;
	int steps;
	int extendDirection = 0;
	/* 0 => ohne Extension, 1 => rechts, 2 => unten, 3 => links, 4 => oben */

	public Token(int xCoord, int yCoord, int steps) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.steps = steps;
		this.extendDirection = 0;
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

	public int getExtDirection() {
		return extendDirection;
	}

	public void setExtDirection(int extendDirection) {
		this.extendDirection = extendDirection;
	}
	
	

	public boolean isInExtension(int x, int y) {
		if ((this.extendDirection == 1 && x >= this.xCoord && x <= this.xCoord + this.steps && y == this.yCoord) /* rechts */
				|| (this.extendDirection == 3 && x <= this.xCoord && x >= this.xCoord - this.steps
						&& y == this.yCoord) /* links */
				|| (this.extendDirection == 2 && y <= this.yCoord && y >= this.yCoord - this.steps
						&& x == this.xCoord) /* unten */
				|| (this.extendDirection == 4 && y >= this.yCoord && y <= this.yCoord + this.steps
						&& x == this.xCoord)) /* oben */
			return true;

		return false;
	}
}
