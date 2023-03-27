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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

    public void accessUI(Stage primaryStage) {
        TableView<Book> table = new TableView<>();
        table.setEditable(true);

        List<Book> books = new ArrayList<>();
        //books.add(new Book("Temp", 99.99));
        //books.add(new Book("Temp", 99.99));
        table.setItems(FXCollections.observableArrayList(books));

        CustomerData CD = (CustomerData) LM.getCurrentUser();
        Label topParagraph = new Label("Wetlcome " + CD.getUsername() + ". You have " + CD.getPoints() + " points. Your status is " + CD.getStatus() + ".");

        BorderPane.setAlignment(topParagraph, Pos.CENTER);

        TableColumn<Book, String> col1 = new TableColumn<>("Name of Book");
        TableColumn<Book, Double> col2 = new TableColumn<>("Price of Book");
        TableColumn<Book, Boolean> col3 = new TableColumn<>("Selection Box");

        double tableWidth = LoginGUI.defaultWidth;

        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        col3.prefWidthProperty().bind(table.widthProperty().multiply(0.33));

        table.getColumns().addAll(col1, col2, col3);

        Button buyBtn = new Button("Buy");
        buyBtn.setOnAction(e -> RegularPurchase(primaryStage));
        
        Button redeemBtn = new Button("Redeem Points to Buy");
        redeemBtn.setOnAction(e -> PointPurchase(primaryStage));
        
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> logoutSequence(primaryStage));
        
        FlowPane buttons = new FlowPane(10, 10, buyBtn, redeemBtn, logoutBtn);
        buttons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topParagraph);
        root.setCenter(table);
        root.setBottom(buttons);
        Scene scene = new Scene(root, LoginGUI.defaultWidth, LoginGUI.defaultHeight);
        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void RegularPurchase(Stage primaryStage){
        //CALL THE CART MANAGER CLASS FOR A REGULAR PURCHASE
        //PUT MOST OF THE LOGIC IN THE CART MANAGER CLASS
        
        
    }
    
    private void PointPurchase(Stage primaryStage){
        //CALL THE CART MANAGER CLASS FOR A POINT PURCHASE
        //PUT MOST OF THE LOGIC IN THE CART MANAGER CLASS
        
        
    }
    
    private void logoutSequence(Stage primaryStage) {
        LoginGUI GUI = new LoginGUI();
        GUI.setLoginPresenter(LM);
        
        GUI.shutdownSequence(primaryStage);
       
    }
}
