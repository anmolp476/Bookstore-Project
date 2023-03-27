/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

//import java.awt.Color;
import bookstoreapplication.GUIs.ApplicationGUI;

import bookstoreapplication.*;
import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.DataStructures.*;
import static bookstoreapplication.GUIs.LoginGUI.defaultHeight;
import static bookstoreapplication.GUIs.LoginGUI.defaultWidth;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;



/**
 *
 * @author deeps
 */
public class OwnerGUI extends ApplicationGUI{
    
    private Scene OwnerMainMenuScene, Owner_Books_Scene, Owner_Customers_Scene;
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

        SetupChildScenes(primaryStage);

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

        OwnerMainMenuScene = new Scene(root, LoginGUI.defaultWidth, LoginGUI.defaultHeight);

        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(OwnerMainMenuScene);
        primaryStage.show();
    }
        
    private void SetupChildScenes(Stage primaryStage){
        SetupOwnerBooksScene(primaryStage);
        SetupOwnerCustomersScene(primaryStage);
    }
        
    private void SetupOwnerBooksScene(Stage primaryStage){
        TableView<BookData> table = new TableView<>();
        table.setEditable(true);

        List<BookData> books = new ArrayList<>();
        //books.add(new Book("Temp", 99.99));
        //books.add(new Book("Temp", 99.99));
        table.setItems(FXCollections.observableArrayList(books));

        OwnerData OD = (OwnerData) LM.getCurrentUser();
        Label topParagraph = new Label("Welcome " + OD.getUsername() + ". This is where you manage books");

        BorderPane.setAlignment(topParagraph, Pos.CENTER);

        TableColumn<BookData, String> col1 = new TableColumn<>("Name of Book");
        TableColumn<BookData, Double> col2 = new TableColumn<>("Price of Book");

        double tableWidth = LoginGUI.defaultWidth;

        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.5));

        table.getColumns().addAll(col1, col2);
        
        
        Label LabelBookName = new Label("Book Name: ");
        TextField bookNameField = new TextField();
        
        Label LabelBookPrice = new Label("Book Price: ");
        TextField bookPriceField = new TextField();

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addBook(primaryStage));
        
        Button deleteBtn = new Button("Delete Selected Books");
        deleteBtn.setOnAction(e -> deleteBooks(primaryStage));
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> returnToOwnerMainMenu(primaryStage));
        
        FlowPane buttons = new FlowPane();
        buttons.setVgap(8);
        buttons.setHgap(4);
        buttons.setPrefWrapLength(100); 
        buttons.getChildren().add(LabelBookName);
        buttons.getChildren().add(bookNameField);
        buttons.getChildren().add(LabelBookPrice);
        buttons.getChildren().add(bookPriceField);
        buttons.getChildren().add(addBtn);
        buttons.getChildren().add(deleteBtn);
        buttons.getChildren().add(backBtn);
        
        buttons.setAlignment(Pos.CENTER);
        

        BorderPane root = new BorderPane();
        root.setTop(topParagraph);
        root.setCenter(table);
        
        root.setBottom(buttons);
        
        Owner_Books_Scene = new Scene(root, defaultWidth, defaultHeight);
        
        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(Owner_Books_Scene);
        primaryStage.show();
    }
    private void addBook(Stage primaryStage){
        //add logic here
    }
    
    private void deleteBooks(Stage primaryStage){
        //add logic here
    }
    
    private void returnToOwnerMainMenu(Stage primaryStage){
        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(OwnerMainMenuScene);
        primaryStage.show();
    }
    
    private void SetupOwnerCustomersScene(Stage primaryStage){
        TableView<CustomerData> table = new TableView<>();
        table.setEditable(true);

        List<Book> CustomerData = new ArrayList<>();
        
        OwnerData OD = (OwnerData) LM.getCurrentUser();
        Label topParagraph = new Label("Welcome " + OD.getUsername() + ". This is where you manage the customers");

        BorderPane.setAlignment(topParagraph, Pos.CENTER);

        TableColumn<Book, String> col1 = new TableColumn<>("Name of Book");
        TableColumn<Book, Double> col2 = new TableColumn<>("Price of Book");


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
