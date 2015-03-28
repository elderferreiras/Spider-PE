/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpa.spider.pe.view.execution.persistence;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

import br.ufpa.spider.pe.view.execution.model.*;

/**
 *
 * @author Specktro
 */
public class ModellingFileManager {

    private static XStream xStream;

    private ModellingFileManager() {
    }

    private static void configure() {
        xStream = new XStream();
        xStream.alias("component", SimpleComponent.class);
        xStream.alias("componentType", ComponentType.class);
        xStream.alias("diagram", SimpleDiagram.class);
        xStream.alias("diagramComponent", DiagramComponent.class);
        xStream.alias("diagramComponentStereotypesGroup", DiagramComponentStereotypesGroup.class);
        xStream.alias("diagramRelationship", DiagramRelationship.class);
        xStream.alias("diagramRelationshipStereotypesGroup", DiagramRelationshipStereotypesGroup.class);
        xStream.alias("extendibleElementType", ExtendibleElementType.class);
        xStream.alias("humanResource", HumanResource.class);
        xStream.alias("modelling", SimpleModelling.class);
        xStream.alias("relationship", Relationship.class);
        xStream.alias("relationshipType", RelationshipType.class);
        xStream.alias("stereotype", Stereotype.class);
        xStream.alias("stereotypeGroup", StereotypesGroup.class);
        xStream.alias("modellingVersion", ModellingVersion.class);
    }

    public static void load(String path) throws FileNotFoundException, IOException {
    	
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        if (xStream == null) {
            configure();
        }
        SimpleModelling modelling = (SimpleModelling) xStream.fromXML(bufferedReader);
        bufferedReader.close();
        modelling.flush();
    }
    
    public static SimpleModelling load2(String path) throws FileNotFoundException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        if (xStream == null) {
            configure();
        }
        SimpleModelling modelling = (SimpleModelling) xStream.fromXML(bufferedReader);
        bufferedReader.close();
        return modelling;
    }
    
    

    public static void save(String path) throws IOException {
        SimpleModelling modelling = new SimpleModelling();
        modelling.fill();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
        if (xStream == null) {
            configure();
        }
        xStream.toXML(modelling, bufferedWriter);
        bufferedWriter.close();
    }
    
    public static void save(File file, int idProcess, SimpleModelling mod) throws IOException {
        SimpleModelling modelling = mod.fill(idProcess);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        if (xStream == null) {
            configure();
        }
        xStream.toXML(modelling, bufferedWriter);
        bufferedWriter.close();
    }

}
