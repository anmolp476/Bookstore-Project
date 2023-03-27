/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.GUIs.ApplicationGUI;
import static bookstoreapplication.GUIs.LoginGUI.defaultHeight;
import static bookstoreapplication.GUIs.LoginGUI.defaultWidth;
import java.awt.print.Book;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import bookstoreapplication.LoginManager;
import java.io.File;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TableColumn;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import bookstoreapplication.DataStructures.*;
import javafx.scene.control.cell.CheckBoxTableCell;

/**
 *
 * @author deeps
 */
public class CustomerGUI extends ApplicationGUI {

    public LoginManager LM;
    private Scene CustomerCostScene;
   
    public CustomerGUI(LoginManager LM) {
        this.LM = LM;
    }
   
    @Override
    public void accessUI(Stage primaryStage) {
        TableView<Book> table = new TableView<>();
        table.setEditable(false);

        CustomerData CD = (CustomerData) LM.getCurrentUser();
        Label topParagraph = new Label("Welcome " + CD.getUsername() + ". You have " + CD.getPoints() + " points. Your status is " + CD.getStatus() + ".");
        topParagraph.setMinHeight(40);
        BorderPane.setAlignment(topParagraph, Pos.CENTER);
        
        TableColumn<Book, String> col1 = new TableColumn<>("Name of Book");
        TableColumn<Book, Float> col2 = new TableColumn<>("Price of Book");
        TableColumn<Book, Boolean> col3 = new TableColumn<>("Selection Box");
        
        double tableWidth = LoginGUI.defaultWidth;
     
        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        col3.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        
        col1.setStyle("-fx-border-width: 0 0 0 1px; -fx-border-color: gray;");
        col2.setStyle("-fx-border-width: 0 0 0 1px; -fx-border-color: gray;");
        col3.setStyle("-fx-border-width: 0 0 0 1px; -fx-border-color: gray;");
           
        table.getColumns().addAll(col1, col2, col3);
        
        ObservableList<Book> booksList = FXCollections.observableArrayList(
            new Book(),
            new Book(),
            new Book()
        );
        
        table.setItems(booksList);
       
        Button buyBtn = new Button("Buy");
        buyBtn.setOnAction(e -> RegularPurchase(primaryStage));
        
        Button redeemBtn = new Button("Redeem Points to Buy");
        redeemBtn.setOnAction(e -> PointPurchase(primaryStage));
        
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> logoutSequence(primaryStage));
        
        FlowPane buttons = new FlowPane(10, 10, buyBtn, redeemBtn, logoutBtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setMinHeight(40);

        BorderPane root = new BorderPane();
        root.setTop(topParagraph);
        root.setCenter(table);
        root.setBottom(buttons);
        Scene scene = new Scene(root, LoginGUI.defaultWidth, LoginGUI.defaultHeight);
        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
    private void SetupCostScene(Stage primaryStage, double totalCost, double points, String status){

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logoutSequence(primaryStage));
        
        Label totalCostLabel = new Label("Total Cost:");
        Label pointsLabel = new Label("Points:");
        Label statusLabel = new Label("Status:");
        Label titleLabel = new Label("Hello, CUSTOMER. This is your reciept");        
        
        logoutButton.setTranslateX(375);
        logoutButton.setTranslateY(-150);
        logoutButton.setMinSize(150, 50);
        logoutButton.setFont(Font.font("Arial", 20));
        
        TextField totalCostField = new TextField();
        TextField pointsField = new TextField();
        TextField statusField = new TextField();
        
        titleLabel.setFont(Font.font("Arial", 35));
        totalCostLabel.setFont(Font.font("Arial", 30));
        pointsLabel.setFont(Font.font("Arial", 30));
        statusLabel.setFont(Font.font("Arial", 30));
       
        titleLabel.setTranslateY(100);
        totalCostField.setMinWidth(80 + Double.toString(Math.floor(totalCost)).length());
        pointsField.setMinWidth(80 + Double.toString(Math.floor(points)).length());
        statusField.setMinWidth(80);
        totalCostField.setEditable(false);
        pointsField.setEditable(false);
        statusField.setEditable(false);
        totalCostField.setText(Double.toString(totalCost));
        pointsField.setText(Double.toString(points));
        statusField.setText(status);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(totalCostLabel, 0, 1);
        grid.add(totalCostField, 25, 1);
        grid.add(pointsLabel, 0, 2);
        grid.add(pointsField, 25, 2);
        grid.add(statusLabel, 0, 3);
        grid.add(statusField, 25, 3);
        
        grid.setTranslateY(150);
        grid.setTranslateX(190);
                
        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setCenter(grid);
        root.setAlignment(grid, Pos.CENTER);
        root.setBottom(logoutButton);

        CustomerCostScene = new Scene(root, defaultWidth, defaultHeight);
    }

    private void RegularPurchase(Stage primaryStage){
        //CALL THE CART MANAGER CLASS FOR A REGULAR PURCHASE
        //PUT MOST OF THE LOGIC IN THE CART MANAGER CLASS
        
        System.out.println("REGULAR PURCHASE TEST");
        
        CustomerData CD = (CustomerData) LM.getCurrentUser(); 
        double points = CD.getPoints();
        String status = CD.getStatus();
        SetupCostScene(primaryStage, 0, points, status);//UPDATE THIS AFTER YOU DO THE LOGIC FOR CALCULATING COST< POINTS< STATUS
        primaryStage.setScene(CustomerCostScene);
        
    }
    
    private void PointPurchase(Stage primaryStage){
        //CALL THE CART MANAGER CLASS FOR A POINT PURCHASE
        //PUT MOST OF THE LOGIC IN THE CART MANAGER CLASS
        
        CustomerData CD = (CustomerData) LM.getCurrentUser(); 
        double points = CD.getPoints();
        String status = CD.getStatus();
        SetupCostScene(primaryStage, 0, points, status);//UPDATE THIS AFTER YOU DO THE LOGIC FOR CALCULATING COST< POINTS< STATUS
        primaryStage.setScene(CustomerCostScene);
    }
    
    private void logoutSequence(Stage primaryStage) {
        LoginGUI GUI = new LoginGUI();
        GUI.setLoginPresenter(LM);
        
        GUI.shutdownSequence(primaryStage);
       
    }
}
