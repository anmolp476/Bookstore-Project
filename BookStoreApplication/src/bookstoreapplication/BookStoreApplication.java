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
    
    
    public void accessUI(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        Button btn2 = new Button();
        btn2.setText("Say 'test'");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("setset!");
            }
        });
        
        StackPane root = new StackPane();
        VBox vbox = new VBox();
  
        vbox.getChildren().add(btn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        vbox.getChildren().add(btn2);
        root.getChildren().add(vbox);
        Scene scene = new Scene(root, 560, 480);
        
        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
