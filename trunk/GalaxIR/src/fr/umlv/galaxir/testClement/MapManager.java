package fr.umlv.galaxir.testClement;


import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * ATTENTION IL FAUT PERMETTRE DE METTRE UN PLAYER LORS DU LOAD!!!!!!!!!!
 */
public class MapManager {
	public static List<Planet> load(File mapXml)throws IOException {
		List<Planet> planetList = new ArrayList<Planet>();
		try{
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fabrique.newDocumentBuilder();
			Document document = builder.parse(mapXml);
			System.out.println(document.toString());
			Element root = document.getDocumentElement();
			String mapLength = root.getAttribute("length");
			String mapHeight = root.getAttribute("height");
			System.out.println(mapLength+" "+mapHeight);
			NodeList planetNodeList = root.getElementsByTagName("planet");
			for(int i = 0; i < planetNodeList.getLength(); i++){
				Element e = (Element)planetNodeList.item(i);
				Element sizeNode = (Element)e.getElementsByTagName("size").item(0);
				int size = Integer.parseInt(sizeNode.getAttribute("value"));
				
				Element shipNode = (Element)e.getElementsByTagName("ship").item(0);
				int nbShip = Integer.parseInt(shipNode.getAttribute("value"));

				Element playerNode = (Element)e.getElementsByTagName("owner").item(0);
				boolean player = Boolean.parseBoolean(playerNode.getAttribute("value"));

				Element prodNode = (Element)e.getElementsByTagName("production").item(0);
				int production = Integer.parseInt(prodNode.getAttribute("value"));

				Element positionNode = (Element)e.getElementsByTagName("position").item(0);
				int x = Integer.parseInt(positionNode.getAttribute("x"));
				int y = Integer.parseInt(positionNode.getAttribute("y"));
				if(player)
					planetList.add(new Planet(nbShip,production,size,new Point(x,y),null) );
				else
					planetList.add(new Planet(nbShip,production,size,new Point(x,y),null) );
			}

		}catch(ParserConfigurationException pce){
			System.err.println("Erreur de configuration du parseur DOM");
			System.err.println("lors de l'appel à fabrique.newDocumentBuilder();");
			throw new IOException("Impossible de charger le fichier :"+ mapXml.getName());
		}catch(SAXException se){
			System.err.println("Erreur lors du parsing du document");
			System.err.println("lors de l'appel à construteur.parse(xml)");
			throw new IOException("Impossible de charger le fichier :"+ mapXml.getName());

		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
			throw new IOException("Impossible de charger le fichier :"+ mapXml.getName());
		}
		return planetList;
	}
	public static void save (File xmlFile, List<Planet> planetList){
		
	}

}
