/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;

/**
 *
 * @author Anmol
 */
public class CustomerCostGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button logoutButton = new Button("Logout");
        Label totalCostLabel = new Label("Total Cost:");
        Label pointsLabel = new Label("Points:");
        Label statusLabel = new Label("Status:");
        Label titleLabel = new Label("Hello, CUSTOMER. This is your reciept");
        
        logoutButton.setTranslateX(325);
        logoutButton.setTranslateY(-180);
        logoutButton.setMinSize(150, 50);
        logoutButton.setFont(Font.font("Arial", 20));
        
        TextField totalCostField = new TextField();
        TextField pointsField = new TextField();
        TextField statusField = new TextField();
        
        titleLabel.setFont(Font.font("Arial", 35));
        totalCostLabel.setFont(Font.font("Arial", 30));
        pointsLabel.setFont(Font.font("Arial", 30));
        statusLabel.setFont(Font.font("Arial", 30));

        totalCostField.setPrefWidth(130);
        pointsField.setPrefWidth(130);
        statusField.setPrefWidth(130);
        totalCostField.setEditable(false);
        pointsField.setEditable(false);
        statusField.setEditable(false);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(totalCostLabel, 0, 1);
        grid.add(totalCostField, 1, 1);
        grid.add(pointsLabel, 0, 2);
        grid.add(pointsField, 1, 2);
        grid.add(statusLabel, 0, 3);
        grid.add(statusField, 1, 3);
        
        grid.setTranslateY(90);
        grid.setTranslateX(100);
                
        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setCenter(grid);
        root.setAlignment(grid, Pos.CENTER);
        root.setBottom(logoutButton);

        Scene scene = new Scene(root, 800, 550);

        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}











