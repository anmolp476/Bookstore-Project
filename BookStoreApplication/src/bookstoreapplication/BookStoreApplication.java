/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
/**
 *
 * @author Victor
 */
public class BookStoreApplication implements Viewable {

    private final FileGateway gateway;
    
    
    public BookStoreApplication() {
        this.gateway = new FileGateway();;
    }
    
    
    public void accessUI(Stage primaryStage) {
        
    }

    
    public FileGateway getLocalFileGateway(){
        return gateway;
    }

    
}
