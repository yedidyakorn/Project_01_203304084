/*
package parser;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DOMHandler {

    Map<String, String> sceneAttributes;
    Map<String, String> cameraAttributes;
    Map<String, String> ambientLightAttributes;
    List<Map<String, String>> _spheres;
    List<Map<String, String>> _triangles;


    try

    {
        File fXmlFile = new File("C:\\Users\\Yedidya\\source\\JAVA\\Project_01_203304084\\basicRenderTestTwoColors.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);


        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getElementsByTagName("student");
        // nodeList is not iterable, so we are using for loop
        Element element=(Element) nodeList;
        sceneAttributes=(Map)element.getAttributes();
        Node node=Node.
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            System.out.println("\nNode Name :" + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                System.out.println(": " + eElement.getAttributes());
                System.out.println("Student id: " + eElement.getElementsByTagName("id").item(0).getTextContent());
                System.out.println("First Name: " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Last Name: " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Subject: " + eElement.getElementsByTagName("subject").item(0).getTextContent());
                System.out.println("Marks: " + eElement.getElementsByTagName("marks").item(0).getTextContent());
            }
        }
    }

    catch(  Exception e)

    {
        e.printStackTrace();
    }
}

*/


