/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

import bookstoreapplication.BookData;
import bookstoreapplication.BookStoreApplication;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import bookstoreapplication.DataStructures.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author deeps
 */
public class CustomerGUI extends ApplicationGUI {

    public LoginManager LM;
    private Scene CustomerCostScene;
    BookStoreApplication BSA;

    public CustomerGUI(LoginManager LM, BookStoreApplication BSA) {
        this.LM = LM;
        this.BSA = BSA;
    }
   
    @Override
    public void accessUI(Stage primaryStage) {
        TableView<BookData> table = new TableView<>();
        table.setEditable(true);

        CustomerData CD = (CustomerData) LM.getCurrentUser();
        Label topParagraph = new Label("Welcome " + CD.getUsername() + ". You have " + CD.getPoints() + " points. Your status is " + CD.getStatus() + ".");
        topParagraph.setMinHeight(40);
        BorderPane.setAlignment(topParagraph, Pos.CENTER);
        table.setItems(FXCollections.observableArrayList(BSA.getBookManager().getBookList()));

        
        TableColumn<BookData, String> col1 = new TableColumn<>("Name of Book");
            col1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getBookName())));
        TableColumn<BookData, Double> col2 = new TableColumn<>("Price of Book");
            col2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(Double.valueOf((cellData.getValue().getPrice()))));

        TableColumn<BookData, Boolean> col3 = new TableColumn<>("Selection Box");
            col3.setCellValueFactory(new PropertyValueFactory("isSelected"));//this is a boolean property variable's name in BookData
            col3.setCellFactory(tc -> new CheckBoxTableCell<>());
        
        double tableWidth = LoginGUI.defaultWidth;
     
//        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
//        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
//        col3.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
//           
        col1.setMinWidth(tableWidth / 3);
        col2.setMinWidth(tableWidth / 3);
        col3.setMinWidth(tableWidth / 3);
        //col3.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        //col3.setCellFactory(CheckBoxTableCell.forTableColumn(col3));

        table.getColumns().addAll(col1, col2, col3);
        
        Button buyBtn = new Button("Buy");
        buyBtn.setOnAction(e -> RegularPurchase(primaryStage, table));
        
        Button redeemBtn = new Button("Redeem Points to Buy");
        redeemBtn.setOnAction(e -> PointPurchase(primaryStage, table));
        
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
        
        //Button backBtn = new Button("Back");

        
        Label totalCostLabel = new Label("Total Cost:");
        Label pointsLabel = new Label("Points:");
        Label statusLabel = new Label("Status:");
        Label titleLabel = new Label("Hello, " + LM.getCurrentUser().getUsername() + ". This is your reciept");        
        
        logoutButton.setTranslateX(375);
        logoutButton.setTranslateY(-150);
        logoutButton.setMinSize(150, 50);
        logoutButton.setFont(Font.font("Arial", 20));
        
        //backBtn.setTranslateX(375);
        //backBtn.setTranslateY(-180);
        //backBtn.setMinSize(150, 50);
        //backBtn.setFont(Font.font("Arial", 20));
        
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
        
        ImageView gifView = new ImageView();
        gifView.setFitWidth(LoginGUI.defaultWidth); // set the width of the GIF image
        gifView.setFitHeight(LoginGUI.defaultHeight); // set the height of the GIF image
        gifView.setTranslateX(0); // set the x position of the GIF image
        gifView.setTranslateY(0); // set the y position of the GIF image
        String gifFile = "Media/confetti.gif";
        Image gifImage = new Image(new File(gifFile).toURI().toString());
        gifView.setImage(gifImage);
        
        ImageView gifView2 = new ImageView();
        gifView2.setFitWidth(LoginGUI.defaultWidth); // set the width of the GIF image
        gifView2.setFitHeight(LoginGUI.defaultHeight); // set the height of the GIF image
        gifView2.setTranslateX(0); // set the x position of the GIF image
        gifView2.setTranslateY(0); // set the y position of the GIF image
        String gifFile2 = "Media/2a91ebdf918337b5254207bf94b212e7.gif";
        Image gifImage2 = new Image(new File(gifFile2).toURI().toString());
        gifView2.setImage(gifImage2);
                
        String musicFile = "Media/167535__jordanielmills__01-winner.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.25);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        
        BorderPane root = new BorderPane();
        
        root.getChildren().add(gifView);
        root.getChildren().add(gifView2);
        
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setCenter(grid);
        root.setAlignment(grid, Pos.CENTER);
        root.setBottom(logoutButton);
        


        
        CustomerCostScene = new Scene(root, defaultWidth, defaultHeight);
    }

    private void RegularPurchase(Stage primaryStage, TableView<BookData> table){
        //CALL THE CART MANAGER CLASS FOR A REGULAR PURCHASE
        //PUT MOST OF THE LOGIC IN THE CART MANAGER CLASS
        
        System.out.println("REGULAR PURCHASE TEST");
        
        CustomerData CD = (CustomerData) LM.getCurrentUser(); 
        for (BookData BD : table.getItems().filtered(BookData::isSelected)) {

            BSA.getCartManager().addselectbook(BD);
            BSA.getBookManager().removeBook(BD); // The method for removing books is not yet implemented in book manager.
         //  BSA.getBookManager().getCM().removeBook(BD);
        }
        CD.addPoints((int)BSA.getCartManager().getTotalPrice()*10);
        //double points = CD.getPoints();
        //String status = CD.getStatus();
        SetupCostScene(primaryStage, BSA.getCartManager().getTotalPrice(), CD.getPoints(), CD.getStatus());//UPDATE THIS AFTER YOU DO THE LOGIC FOR CALCULATING COST< POINTS< STATUS
        for (BookData i : BSA.getBookManager().getBookList()){
            System.out.println(i.getBookName());
        }        
        primaryStage.setScene(CustomerCostScene);
        
    }
    
    private void PointPurchase(Stage primaryStage,  TableView<BookData> table){
        //CALL THE CART MANAGER CLASS FOR A POINT PURCHASE
        //PUT MOST OF THE LOGIC IN THE CART MANAGER CLASS
        
        CustomerData CD = (CustomerData) LM.getCurrentUser(); 
        double points = CD.getPoints();
        String status = CD.getStatus();
        for (BookData BD : table.getItems().filtered(BookData::isSelected)) {

            BSA.getCartManager().addselectbook(BD);
            BSA.getBookManager().removeBook(BD); // The method for removing books is not yet implemented in book manager.
         //  BSA.getBookManager().getCM().removeBook(BD);
        }
        double cost = BSA.getCartManager().getTotalPrice();
        System.out.println(""+cost);
        System.out.println(""+(int)cost*100);
        //int pointsLoss = (int)cost*100;
        int discount = CD.removePoints((int)cost*100);
        double totalCost = 0;
        if (discount == 0){
            totalCost = 0;
        }
        else{
            totalCost = cost - (discount/100);
        }
        SetupCostScene(primaryStage, totalCost, CD.getPoints(), CD.getStatus());//UPDATE THIS AFTER YOU DO THE LOGIC FOR CALCULATING COST< POINTS< STATUS
        primaryStage.setScene(CustomerCostScene);
    }
    
    private void logoutSequence(Stage primaryStage) {
        LoginGUI GUI = new LoginGUI();
        GUI.setLoginPresenter(LM);
        
        GUI.shutdownSequence(primaryStage);
       
    }
}
