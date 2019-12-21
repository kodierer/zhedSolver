import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	final static private int PADDING = 2;
	public static Map<Integer, Map<Integer, Character>> board = new HashMap<>();
	public static Map<String, Integer> boardSize;

	public static void main(String[] args) throws Exception {
		List<Token> tokenList = ReadXMLFile.readGameData();
		if (tokenList == null)
			throw new Exception("No Data");
		boardSize = determineBoardSize(tokenList);
		arrangeBoard(tokenList);
		System.out.println("--------Print Game Data----------------");
		printGameData(tokenList);
		System.out.println("--Draw Board--(X = Target, Padding = " + PADDING + ")--");
		drawBoard(tokenList);
		extend(4, tokenList.get(2));
		drawBoard(tokenList);
		extend(1, tokenList.get(1));
		drawBoard(tokenList);
		extend(4, tokenList.get(3));
		drawBoard(tokenList);
		extend(1, tokenList.get(0));
		drawBoard(tokenList);
	}

	public static void printGameData(List<Token> tokenList) {
		for (Token token : tokenList) {
			System.out.println("List-Index: " + tokenList.indexOf(token));
			System.out.println("x: " + token.getxCoord());
			System.out.println("y: " + token.getyCoord());
			System.out.println("steps: " + token.getSteps());
			System.out.println("extend direction: " + token.getExtDirection());
			System.out.println("---------------------------");
		}
	}

	public static void arrangeBoard(List<Token> tokenList) {
		Map<Integer, Character> rows;
		Token token;
		for (int y = boardSize.get("maxY") + PADDING; y >= boardSize.get("minY") - PADDING; y--) {
			rows = new HashMap<>();
			for (int x = boardSize.get("maxX") + PADDING; x >= boardSize.get("minX") - PADDING; x--) {
				token = getTokenIfExists(x, y, tokenList);
				rows.put(x, getChar(x, y, token));
			}
			board.put(y, rows);
		}
	}

	public static void drawBoard(List<Token> tokenList) {
		StringBuilder line = new StringBuilder();
		for (int y = boardSize.get("maxY") + PADDING; y >= boardSize.get("minY") - PADDING; y--) {
			line.setLength(0);
			for (int x = boardSize.get("minX") - PADDING; x <= boardSize.get("maxX") + PADDING; x++) {
				line.append(board.get(y).get(x));
				line.append(' ');
			}
			System.out.println(line.toString());
		}
		System.out.println("**********************************");
	}

	public static char getChar(int x, int y, Token token) {
		if (x == 0 && y == 0)
			return 'X';
		if (token != null && token.extendDirection == 0)
			return (char) (token.getSteps() + '0');
		return '-';
	}

	public static Token getTokenIfExists(int x, int y, List<Token> tokenList) {
		for (Token token : tokenList) {
			if (token.getxCoord() == x && token.getyCoord() == y)
				return token;
		}
		return null;
	}

	public static Map<String, Integer> determineBoardSize(List<Token> tokenList) {
		Map<String, Integer> boardSize = new HashMap<>();
		int maxX = 0, maxY = 0, minX = 0, minY = 0;
		for (Token token : tokenList) {
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

	public static void extend(int direction, Token token) throws Exception {
		token.setExtDirection(direction);
		int jump = 0;
		switch (direction) {
		case 1:/* rechts */
			for (int i = token.xCoord; i <= token.xCoord + token.steps; i++) {
				if (board.get(token.yCoord).get(i + jump) != '-' && i > token.xCoord)
					jump++;
				board.get(token.yCoord).put(i + jump, '#');
			}
			break;
		case 2:/* unten */
			for (int i = token.yCoord; i >= token.yCoord - token.steps; i--) {
				if (board.get(i - jump).get(token.xCoord) != '-' && i < token.yCoord)
					jump++;
				board.get(i - jump).put(token.xCoord, '#');
			}
			break;
		case 3:/* links */
			for (int i = token.xCoord; i >= token.xCoord - token.steps; i--) {
				if (board.get(token.yCoord).get(i - jump) != '-' && i < token.xCoord)
					jump++;
				board.get(token.yCoord).put(i - jump, '#');
			}
			break;
		case 4:/* oben */
			for (int i = token.yCoord; i <= token.yCoord + token.steps; i++) {
				if (board.get(i + jump).get(token.xCoord) != '-' && i > token.yCoord)
					jump++;
				board.get(i + jump).put(token.xCoord, '#');
			}
			break;
		default:
			throw new Exception("falsche richtung");
		}
	}
}
