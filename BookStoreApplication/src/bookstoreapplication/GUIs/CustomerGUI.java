/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.GUIs.ApplicationGUI;
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

import javafx.scene.layout.FlowPane;
import javafx.scene.control.TableColumn;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
/**
 *
 * @author deeps
 */
public class CustomerGUI extends ApplicationGUI{
    
    public LoginManager LM;

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
        
        Label topParagraph = new Label("Wetlcome " + LM.getCurrentUser().getUsername() + ". You have " + ((CustomerData) LM.getCurrentUser()).getPoints() + " points. Your status is " + ((CustomerData) LM.getCurrentUser()).getStatus() + ".");
        
        BorderPane.setAlignment(topParagraph, Pos.CENTER);
    
        
        TableColumn<Book, String> col1 = new TableColumn<>("Name of Book");
        TableColumn<Book, Double> col2 = new TableColumn<>("Price of Book");
        TableColumn<Book, Boolean> col3 = new TableColumn<>("Selection Box");
        
        double tableWidth = table.getPrefWidth();
       
        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        col3.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        
        table.getColumns().addAll(col1, col2, col3);
      
        Button buyBtn = new Button("Buy");
        Button redeemBtn = new Button("Redeem Points to Buy");
        Button logoutBtn = new Button("Logout");
        
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
}
