import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile {

	public static List<Token> readGameData() {
		List<Token> tokenList = new ArrayList<>();
		try {
			File fXmlFile = new File("../Zhet/src/zhetImport.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("token");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					tokenList.add(
							new Token(Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent()),
									Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent()),
									Integer.parseInt(eElement.getElementsByTagName("steps").item(0).getTextContent())));
				}
			}
			return tokenList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
