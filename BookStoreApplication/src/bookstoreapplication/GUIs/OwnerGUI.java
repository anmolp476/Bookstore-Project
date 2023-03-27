/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

//import java.awt.Color;
import bookstoreapplication.GUIs.ApplicationGUI;

import bookstoreapplication.*;
import static bookstoreapplication.GUIs.LoginGUI.defaultHeight;
import static bookstoreapplication.GUIs.LoginGUI.defaultWidth;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;



/**
 *
 * @author deeps
 */
public class OwnerGUI extends ApplicationGUI{
    
    private Scene Owner_Books_Scene, Owner_Customers_Scene;
    LoginManager LM;

    public OwnerGUI(LoginManager LM) {
        this.LM = LM;
    }
    
    
    
        public void accessUI(Stage primaryStage) {
            
            Button btn1 = new Button();
            String buttonStyle = "-fx-font-family: Helvetica; -fx-background-color: #fff; -fx-font-size: 16pt;";
            btn1.setText("Books");
            btn1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Books test");
                    primaryStage.setScene(Owner_Books_Scene);
                }
            });
            Button btn2 = new Button();
            btn2.setText("Customers");
            btn2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Customer test");
                    primaryStage.setScene(Owner_Customers_Scene);
                }
            });
            Button btn3 = new Button();
            btn3.setText("Logout");
            btn3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Logout Test");
                    logoutSequence(primaryStage);
                }
            });
            
            SetupChildScenes(btn1, btn2, primaryStage);
            
            btn1.setStyle(buttonStyle);
            btn2.setStyle(buttonStyle);
            btn3.setStyle(buttonStyle);
            
            /*Image logo = new Image("File:Media/BookstoreApplicationLogo.png");
            ImageView imageViewer = new ImageView(logo);
            imageViewer.setFitWidth(75);
            imageViewer.setFitWidth(75); */
            
            Text mainTitle = new Text("Owner Terminal");
            mainTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 35));
 
    
            StackPane root = new StackPane();
            root.setStyle("-fx-background-color: #e6e0da;");
            
            /*VBox imageBox = new VBox();
            imageBox.setAlignment(Pos.CENTER_LEFT);
            imageBox.setSpacing(15);
            imageBox.getChildren().add(imageViewer); */
            
            VBox vbox = new VBox();
            vbox.getChildren().add(mainTitle);
            vbox.getChildren().add(btn1);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(20);
            vbox.getChildren().add(btn2);
            root.getChildren().add(vbox);
            vbox.getChildren().add(btn3);
            
            Scene scene = new Scene(root, LoginGUI.defaultWidth, LoginGUI.defaultHeight);

            primaryStage.setTitle("Book Store Application");
            primaryStage.setScene(scene);
            primaryStage.show();
    }
        
    private void SetupChildScenes(Button BooksButton, Button CustomersButton, Stage primaryStage){
        SetupOwnerBooksScene(BooksButton, primaryStage);
        SetupOwnerCustomersScene(CustomersButton, primaryStage);
    }
        
    private void SetupOwnerBooksScene(Button BooksButton, Stage primaryStage){
        // Layout 1
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(15);
        grid.setVgap(15);

        Text title = new Text("Book Management Menu");
        title.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 18));
        GridPane.setConstraints(title, 1, 0);
        grid.getChildren().add(title);
        
        grid.setAlignment(Pos.CENTER);
        
        Owner_Books_Scene = new Scene(grid, defaultWidth, defaultHeight);
    }
    
    private void SetupOwnerCustomersScene(Button CustomersButton, Stage primaryStage){
        // Layout 1
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(15);
        grid.setVgap(15);

        Text title = new Text("Customer Management Menu");
        title.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 18));
        GridPane.setConstraints(title, 1, 0);
        grid.getChildren().add(title);
        
        grid.setAlignment(Pos.CENTER);
        
        Owner_Customers_Scene = new Scene(grid, defaultWidth, defaultHeight);
    }
    
    private void logoutSequence(Stage primaryStage) {
        LoginGUI GUI = new LoginGUI();
        GUI.setLoginPresenter(LM);
        
        GUI.shutdownSequence(primaryStage);
       
    }
}
