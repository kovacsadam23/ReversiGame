package reversi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLHandler {
    int gameType = 0;
    int enemyType = 0;
    String player1 = "";
    String player2 = "PLayer 2";

    File settings = new File("settings.xml");

    public int getGameType() {
        return this.gameType;
    }

    public int getEnemyType() {
        return this.enemyType;
    }

    public String getPlayer1() {
        return this.player1;
    }

    public String getPlayer2() {
        return this.player2;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void readXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(settings);

        Element pl1 = (Element) doc.getElementsByTagName("player1").item(0);
        player1 = pl1.getElementsByTagName("name").item(0).getTextContent();

        Element pl2 = (Element) doc.getElementsByTagName("player2").item(0);
        player2 = pl2.getElementsByTagName("name").item(0).getTextContent();

        Element et = (Element) doc.getElementsByTagName("enemyType").item(0);
        enemyType = Integer.parseInt(et.getElementsByTagName("type").item(0).getTextContent());

        Element gt = (Element) doc.getElementsByTagName("gameType").item(0);
        gameType = Integer.parseInt(gt.getElementsByTagName("type").item(0).getTextContent());
    }

    public void writeXML() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(settings);

        Element pl1 = (Element) doc.getElementsByTagName("player1").item(0);
        pl1.getElementsByTagName("name").item(0).setTextContent(player1);

        Element pl2 = (Element) doc.getElementsByTagName("player2").item(0);
        pl2.getElementsByTagName("name").item(0).setTextContent(player2);

        Element et = (Element) doc.getElementsByTagName("enemyType").item(0);
        et.getElementsByTagName("type").item(0).setTextContent(String.valueOf(enemyType));

        Element gt = (Element) doc.getElementsByTagName("gameType").item(0);
        gt.getElementsByTagName("type").item(0).setTextContent(String.valueOf(gameType));

        Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("settings.xml")));
    }
}
