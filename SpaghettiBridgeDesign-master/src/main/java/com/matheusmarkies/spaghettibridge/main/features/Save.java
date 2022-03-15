/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.features;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Matheus Markies
 */
public class Save {

    public static void saveBridge(Bridge bridge) throws FileNotFoundException, IOException {
        FileOutputStream fileOutput = new FileOutputStream(new File("D:\\Matheus Markies\\Downloads\\inserts.brd"));
        ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput);

        objectStream.writeObject(bridge);

        objectStream.close();
        fileOutput.close();
    }

    public static Bridge openBridge() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(new File("D:\\Matheus Markies\\Downloads\\inserts.brd"));
        ObjectInputStream objectStream = new ObjectInputStream(fileInput);

        Bridge bridge = (Bridge) objectStream.readObject();

        objectStream.close();
        fileInput.close();
        
        return bridge;
    }
}
