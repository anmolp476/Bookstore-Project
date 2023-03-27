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
        TextField totalCostField = new TextField();
        TextField pointsField = new TextField();
        TextField statusField = new TextField();

        totalCostField.setPrefWidth(50);
        pointsField.setPrefWidth(50);
        statusField.setPrefWidth(50);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(logoutButton, 0, 3);
        grid.add(totalCostLabel, 0, 1);
        grid.add(totalCostField, 1, 1);
        grid.add(pointsLabel, 0, 2);
        grid.add(pointsField, 1, 2);
        grid.add(statusLabel, 2, 2);
        grid.add(statusField, 3, 2);

        Scene scene = new Scene(grid, 800, 550);

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











