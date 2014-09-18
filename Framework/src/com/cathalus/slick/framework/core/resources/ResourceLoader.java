package com.cathalus.slick.framework.core.resources;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Loads resources by parsing a XML file specifying them
 * List of XML Elements:
 * basedir      path
 * image        key     file
 * spritesheet  key     file        width           height
 * unicodefont  key     file        fontSize
 * animation    key     sheetName   frameDuration   frames  flipVertical    flipHorizontal  loop
 * music        key     file
 * sound        key     file
 * tiledmap     key     file
 */
public class ResourceLoader {

    private Element rootElement;
    private Element[] empty = new Element[0];
    private String baseDir;


    public void load(InputStream in) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(in);
            rootElement = document.getDocumentElement();
            // Check file is a correct resources file
            if(!rootElement.getNodeName().equals("resources"))
            {
                throw new IOException("Your resource file is not a resource configuration file!");
            }

            Element[] elements = getElementsByTagName("basedir");
            setBaseDirectory(elements[0].getAttribute("path"));

            for (Element element : getElementsByTagName("image"))       { loadImage(element);       }
            for (Element element : getElementsByTagName("spritesheet")) { loadSpriteSheet(element); }
            for (Element element : getElementsByTagName("unicodefont")) { loadUnicodeFont(element); }
            for (Element element : getElementsByTagName("animation"))   { loadAnimation(element);   }
            for (Element element : getElementsByTagName("music"))       { loadMusic(element);       }
            for (Element element : getElementsByTagName("sound"))       { loadSound(element);       }
            for (Element element : getElementsByTagName("tiledmap"))    { loadTiledMap(element);    }

        } catch (Exception e) {
            throw new IOException("Unable to load resource configuration file",e);

        }
    }

    private void loadTiledMap(Element element) throws SlickException {
        checkRequiredAttributes(element, "key", "file");
        String key = element.getAttribute("key");
        String file = element.getAttribute("file");

        TiledMap tiledMap = new TiledMap(baseDir + file);
        ResourceManager.addTiledMap(key, tiledMap);
    }

    private void loadSound(Element element) throws SlickException {
        checkRequiredAttributes(element, "key", "file");
        String key = element.getAttribute("key");
        String file = element.getAttribute("file");
        ResourceManager.addSound(key, new Sound(baseDir + file));
    }

    private void loadMusic(Element element) throws SlickException {
        checkRequiredAttributes(element, "key", "file");
        String key = element.getAttribute("key");
        String file = element.getAttribute("file");
        ResourceManager.addMusic(key, new Music(baseDir + file));
    }

    private void loadAnimation(Element element) {

    }

    private void loadUnicodeFont(Element element) {

    }

    private void loadSpriteSheet(Element element) {

    }

    private void loadImage(Element element) throws SlickException{
        checkRequiredAttributes(element, "key", "file");
        String key = element.getAttribute("key");
        String file = element.getAttribute("file");

        Image image = null;
        try {
            image = new Image(baseDir+file);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        ResourceManager.addImage(key,image);
    }

    private void setBaseDirectory(String baseDirectory) throws SlickException {
        Log.debug("Setting base directory to " + baseDirectory);
        if (baseDirectory == null || baseDirectory.isEmpty()) {
            throw new SlickException("Could not find required BaseDir element.");
        }
        baseDir = baseDirectory;
        if (!baseDir.endsWith("/")) {
            baseDir += "/";
        }
    }

    private Element[] getElementsByTagName(String elementName) {
        NodeList nodes = rootElement.getElementsByTagName(elementName);
        int nodeCount = nodes.getLength();

        if (nodeCount != 0) {
            Element[] elements = new Element[nodeCount];

            for (int i = 0; i < nodeCount; i++) {
                Element element = (Element) nodes.item(i);
                elements[i] = element;
            }
            return elements;
        } else {
            return empty;
        }
    }

    private void checkRequiredAttributes(Element element, String... attributes) {
        boolean requiredAttributeMissing = false;
        for (String attribute : attributes) {
            if (!element.hasAttribute(attribute)) {
                requiredAttributeMissing = true;
            }
        }

        if (requiredAttributeMissing) {
            StringBuffer missingAttributesMessage = new StringBuffer(
                    "Required attribute(s) missing: ");
            for (String attribute : attributes) {
                if (!element.hasAttribute(attribute)) {
                    missingAttributesMessage.append(attribute);
                    missingAttributesMessage.append(", ");
                }
            }
            // Remove trailing ", "
            int length = missingAttributesMessage.length();
            missingAttributesMessage.delete(length - 2, length);
            missingAttributesMessage.append(" from element ").append(
                    element.getNodeName());
            throw new IllegalArgumentException(
                    missingAttributesMessage.toString());
        }
    }

}
