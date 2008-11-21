package fr.umlv.ir2.galaxir.core;


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

import fr.umlv.ir2.galaxir.items.Planet;

/*
 * ATTENTION IL FAUT PERMETTRE DE METTRE UN PLAYER LORS DU LOAD!!!!!!!!!!
 */
public class MapManager {
	private int width;
	private int height;
	private List<Planet> planetList;
	
	public MapManager() {
		this.planetList = new ArrayList<Planet>();
	}
	
	public void load(File mapXml) throws IOException {
		try{
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fabrique.newDocumentBuilder();
			Document document = builder.parse(mapXml);
			//System.out.println(document.toString());
			Element root = document.getDocumentElement();
			width = Integer.parseInt(root.getAttribute("length"));
			height = Integer.parseInt(root.getAttribute("height"));
			//String mapLength = root.getAttribute("length");
			//String mapHeight = root.getAttribute("height");
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
				if(player) {
					planetList.add(new Planet(nbShip,production,size,new Point(x,y),Player.DEFAULT_PLAYER));
				} else
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
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<Planet> getPlanetList() {
		return planetList;
	}
}
