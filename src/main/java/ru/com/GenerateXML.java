package ru.com;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ru.utils.Util.*;

public class GenerateXML {

    private static final List<NodeList> lists = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        Properties properties = new Properties();
        properties.load(new FileReader(args[0]));
        String countXML = properties.getProperty("count.xml");
        String exampleXML = properties.getProperty("path.example.xml");
        String dischargeXML = properties.getProperty("path.discharge.xml");

        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File(exampleXML));
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("nameId");
        NodeList nodeAge = document.getElementsByTagName("age");
        NodeList nodeHigh = document.getElementsByTagName("high");

        lists.add(nodeList);
        lists.add(nodeAge);
        lists.add(nodeHigh);
        for (int i = 0; i < Integer.parseInt(countXML); i++) {
            for (NodeList nodes : lists) {
                for (int j = 0; j < nodes.getLength(); j++){
                    Node employee = nodes.item(j);
                    if(employee.getNodeName().equals("age"))
                        employee.setTextContent(generateAge());
                    else if(employee.getNodeName().equals("high"))
                        employee.setTextContent(generateHigh());
                    else
                        employee.setTextContent(generateWord());
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(dischargeXML + generateNameXML()));
            transformer.transform(source,result);
        }
    }
}
