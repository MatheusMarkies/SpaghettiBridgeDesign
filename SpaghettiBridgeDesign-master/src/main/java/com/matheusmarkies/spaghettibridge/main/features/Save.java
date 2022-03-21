/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main.features;

import com.matheusmarkies.spaghettibridge.main.manager.BridgeManager;
import com.matheusmarkies.spaghettibridge.material.Material;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
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

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            String extension = "";

            boolean changeFileExtension = true;
            for (int i =0;i< selectedFile.toString().split("\\.").length;i++)
                if(i > 0)
                    if(selectedFile.toString().split("\\.")[i].equals("bridge"))
                        changeFileExtension = false;

            if(changeFileExtension)
                extension = ".bridge";

            File bridgeFile = new File(selectedFile.getAbsolutePath() + extension);

            FileOutputStream fileOutput = new FileOutputStream(bridgeFile);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput);

            objectStream.writeObject(bridge);

            objectStream.close();
            fileOutput.close();
        }
    }

    public static void saveMaterial(Material material) throws FileNotFoundException, IOException {
        File materialFile = new File(BridgeManager.getMaterialDataFolder());

        File appdata = new File(BridgeManager.getAppdataDir());
        if(!appdata.exists())
            appdata.mkdirs();
        if(materialFile.createNewFile()) {

        }

        FileOutputStream fileOutput = new FileOutputStream(materialFile);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileOutput);

        objectStream.writeObject(material);

        objectStream.close();
        fileOutput.close();
    }

    public static Bridge openBridge(File selectedFile) throws FileNotFoundException, IOException, ClassNotFoundException {
        Bridge bridge = null;

            FileInputStream fileInput = new FileInputStream(selectedFile);

            ObjectInputStream objectStream = new ObjectInputStream(fileInput);

            bridge = (Bridge) objectStream.readObject();

            objectStream.close();
            fileInput.close();

        return bridge;
    }
    public static Material openMaterial(File selectedFile) throws FileNotFoundException, IOException, ClassNotFoundException {
        Material material = null;

        FileInputStream fileInput = new FileInputStream(selectedFile);

        ObjectInputStream objectStream = new ObjectInputStream(fileInput);

        material = (Material) objectStream.readObject();

        objectStream.close();
        fileInput.close();

        return material;
    }
}
