/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.matheusmarkies.spaghettibridge.main.manager.BridgeManager;

/**
 *
 * @author Matheus Markies
 */
public class SpaghettiBridgeMain extends Application {

    static Scene scene;

    public static BridgeManager bridgeManager = new BridgeManager();
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlMain = new FXMLLoader(SpaghettiBridgeMain.class.getResource(
                "/com/matheusmarkies/spaghettibridge/MainFrame.fxml"));

        Parent root = fxmlMain.load();

        Scene scene = new Scene(root);
        
        MainFrameController controller = fxmlMain.getController();
        controller.setBridgeMain(this);
        
        stage.setTitle("Spaghetti Bridge Design");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        MainFrameController FXML_Start = new MainFrameController();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            launch(args);
    }

    public static Scene getScene() {
        return scene;
    }

}
