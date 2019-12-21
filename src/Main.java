import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

	final static private int PADDING = 1;

	public static void main(String[] args) throws Exception {
		Set<Token> tokenSet = ReadXMLFile.readGameData();
		if (tokenSet != null) {
			System.out.println("--------Print Game Data----------------");
			printGameData(tokenSet);
		} else
			throw new Exception("No Data");

		System.out.println("--------Draw Board---(X = Target, Padding = " + PADDING + ")-------------");
		drawGame(tokenSet);

	}

	public static void printGameData(Set<Token> tokenSet) {
		for (Token token : tokenSet) {
			System.out.println("x: " + token.getxCoord());
			System.out.println("y: " + token.getyCoord());
			System.out.println("steps: " + token.getSteps());
			System.out.println("extended: " + token.getExtended());
			System.out.println("---------------------------");
		}
	}

	public static void drawGame(Set<Token> tokenSet) {
		Map<String, Integer> boardSize = findMaxCoordinates(tokenSet);
		Token token;
		StringBuilder line = new StringBuilder();
		for (int y = boardSize.get("maxY") + PADDING; y >= boardSize.get("minY") - PADDING; y--) {
			line.setLength(0);
			for (int x = boardSize.get("minX") - PADDING; x <= boardSize.get("maxX") + PADDING; x++) {
				token = getTokenIfExists(x, y, tokenSet);
				line.append(getChar(x, y, token));
			}
			System.out.println(line.toString());
		}
	}

	public static char getChar(int x, int y, Token token) {
		if (x == 0 && y == 0)
			return 'X';
		if (token != null)
			return (char) (token.getSteps() + '0');
		else
			return 'O';
	}

	public static Token getTokenIfExists(int x, int y, Set<Token> tokenSet) {
		for (Token token : tokenSet) {
			if (token.getxCoord() == x && token.getyCoord() == y)
				return token;
		}
		return null;
	}

	public static Map<String, Integer> findMaxCoordinates(Set<Token> tokenSet) {
		Map<String, Integer> boardSize = new HashMap<>();
		int maxX = 0, maxY = 0, minX = 0, minY = 0;
		for (Token token : tokenSet) {
			if (token.getxCoord() > maxX)
				maxX = token.getxCoord();
			if (token.getxCoord() < minX)
				minX = token.getxCoord();
			if (token.getyCoord() > maxY)
				maxY = token.getyCoord();
			if (token.getyCoord() < minY)
				minY = token.getyCoord();
		}
		boardSize.put("maxX", maxX);
		boardSize.put("maxY", maxY);
		boardSize.put("minX", minX);
		boardSize.put("minY", minY);
		return boardSize;
	}
}
