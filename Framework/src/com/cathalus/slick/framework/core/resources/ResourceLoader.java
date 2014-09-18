package com.cathalus.slick.framework.core.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Loads resources specified in a XML file
 */
public class ResourceLoader {

    private Element rootElement;
    private Element[] empty = new Element[0];

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


        } catch (Exception e) {
            throw new IOException("Unable to load resource configuration file",e);

        }

    }

}
