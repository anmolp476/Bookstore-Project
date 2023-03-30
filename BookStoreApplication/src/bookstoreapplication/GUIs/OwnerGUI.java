/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

//import java.awt.Color;
import bookstoreapplication.GUIs.ApplicationGUI;
import bookstoreapplication.GUIs.LoginGUI;
import javafx.beans.property.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import bookstoreapplication.*;
import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.DataStructures.*;
import static bookstoreapplication.GUIs.LoginGUI.defaultHeight;
import static bookstoreapplication.GUIs.LoginGUI.defaultWidth;
import java.awt.print.Book;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 *
 * @author deeps
 */
public class OwnerGUI extends ApplicationGUI {

    private Scene OwnerMainMenuScene, Owner_Books_Scene, Owner_Customers_Scene;
    LoginManager LM;
    BookStoreApplication BSA;

    public OwnerGUI(LoginManager LM, BookStoreApplication BSA) {
        this.LM = LM;
        this.BSA = BSA;
    }

    public void accessUI(Stage primaryStage) {

        Button btn1 = new Button();
        String buttonStyle = "-fx-font-family: Helvetica; -fx-background-color: #fff; -fx-font-size: 16pt;";
        btn1.setText("Books");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening Books Menu");
                primaryStage.setScene(Owner_Books_Scene);
            }
        });
        Button btn2 = new Button();
        btn2.setText("Customers");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening Customer Menu");
                primaryStage.setScene(Owner_Customers_Scene);
            }
        });
        Button btn3 = new Button();
        btn3.setText("Logout");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logging Out From Owner Main Menu");
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

    private void SetupChildScenes(Stage primaryStage) {
        SetupOwnerBooksScene(primaryStage);
        SetupOwnerCustomersScene(primaryStage);
    }

    private void SetupOwnerBooksScene(Stage primaryStage) {
        TableView<BookData> table = new TableView<>();
        table.setEditable(true);

        table.setItems(FXCollections.observableArrayList(BSA.getBookManager().getBookList()));

        OwnerData OD = (OwnerData) LM.getCurrentUser();
        Label topParagraph = new Label("Welcome " + OD.getUsername() + ". This is where you manage books");
        topParagraph.setMinHeight(40);

        BorderPane.setAlignment(topParagraph, Pos.CENTER);
        DecimalFormat f = new DecimalFormat("##.00");

        TableColumn<BookData, String> col1 = new TableColumn<>("Name of Book");
        col1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getBookName())));
         TableColumn<BookData, String> col4 = new TableColumn<>("Author of Book");
        col4.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getAuthor())));
        TableColumn<BookData, Double> col2 = new TableColumn<>("Price of Book");
        col2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(Double.parseDouble(f.format(Double.valueOf((cellData.getValue().getPrice()))))));
        
        //TableColumn<BookData, Boolean> TESTCOL = new TableColumn<>("Selected");
        //TESTCOL.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().isSelected().getValue())));
        TableColumn<BookData, Boolean> col3 = new TableColumn<>("Selection");
        col3.setCellValueFactory(new PropertyValueFactory("isSelected"));//this is a boolean property variable's name in BookData
        col3.setCellFactory(tc -> new CheckBoxTableCell<>());

        double tableWidth = LoginGUI.defaultWidth;

//        col1.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
//        col2.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
//        col3.prefWidthProperty().bind(table.widthProperty().multiply(0.34));
        col1.setMinWidth(tableWidth / 4);
        col2.setMinWidth(tableWidth / 4);
        col3.setMinWidth(tableWidth / 4);
        col4.setMinWidth(tableWidth / 4);
        

        table.getColumns().addAll(col1, col4,col2, col3);

        Label LabelBookName = new Label("Book Name: ");
        TextField bookNameField = new TextField();
        Label LabelBookAuthor = new Label("Author Name: ");
        TextField bookAuthorField = new TextField();
        Label LabelBookPrice = new Label("Book Price: ");
        TextField bookPriceField = new TextField();

        Button addBtn = new Button("Add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("add test");
                //customers.add(new CustomerData("Test","code",0));
                try{
                    Float priceVal = Float.parseFloat(bookPriceField.getText());
                    BSA.getBookManager().addBook(bookNameField.getText(), bookAuthorField.getText(), priceVal);

                }
                catch (Exception e){
                    SetupOwnerBooksScene(primaryStage);

                }
                //users.add(new CustomerData(usernameField.getText(), passwordField.getText(),0));

                //for (UserEntity i : users){
                //if (i instanceof CustomerData){
                //System.out.println(i.getUsername());
                //}
                //}
                SetupOwnerBooksScene(primaryStage);
            }
        });
        
        Button deleteBtn = new Button("Delete Selected Books");
        deleteBtn.setOnAction(e -> deleteBooks(primaryStage, table, col3));

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> returnToOwnerMainMenu(primaryStage));

        FlowPane buttons = new FlowPane();
        buttons.setVgap(8);
        buttons.setHgap(4);
        buttons.setPrefWrapLength(100);
        buttons.setMinHeight(40);
        buttons.getChildren().add(LabelBookName);
        buttons.getChildren().add(bookNameField);
        buttons.getChildren().add(LabelBookAuthor);
        buttons.getChildren().add(bookAuthorField);
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

    private void addBook(Stage primaryStage) {

        //add logic here
    }

    private void deleteBooks(Stage primaryStage, TableView<BookData> table, TableColumn<BookData, Boolean> col3) {
        //add logic here

        for (BookData BD : table.getItems().filtered(BookData::isSelected)) {//after checkboxes are set up properly this is how to read data from the selected objects

            System.out.println("this is the price of the book being deleted: " +  BD.getPrice());
        }

        BSA.getBookManager().removeBooks(table.getItems().filtered(BookData::isSelected));

        SetupOwnerBooksScene(primaryStage);
    }

    private void returnToOwnerMainMenu(Stage primaryStage) {
        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(OwnerMainMenuScene);
        primaryStage.show();
    }

    private void SetupOwnerCustomersScene(Stage primaryStage) {
        TableView<CustomerData> table = new TableView<>();
        table.setEditable(true);

        List<CustomerData> customers = new ArrayList<CustomerData>();

        for (UserEntity i : BSA.getAccountManager().getUserList()) {
            if (i instanceof CustomerData) {
                customers.add((CustomerData) i);
            }
        }

        table.setItems(FXCollections.observableArrayList(customers));

        OwnerData OD = (OwnerData) LM.getCurrentUser();
        Label topParagraph = new Label("Welcome " + OD.getUsername() + ". This is where you manage the customers");
        topParagraph.setMinHeight(40);
        BorderPane.setAlignment(topParagraph, Pos.CENTER);

        TableColumn<CustomerData, String> col1 = new TableColumn<>("Username");
        col1.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getUsername())));
        TableColumn<CustomerData, String> col2 = new TableColumn<>("Password");
        col2.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getPassword())));
        TableColumn<CustomerData, Integer> col3 = new TableColumn<>("Points");
        col3.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getPoints())));

        TableColumn<CustomerData, Boolean> col4 = new TableColumn<>("Selection");
        col4.setCellValueFactory(new PropertyValueFactory<>("isSelected"));
        col4.setCellFactory(tc -> new CheckBoxTableCell<>());

        double tableWidth = LoginGUI.defaultWidth;

        col1.setMinWidth(tableWidth / 4);
        col2.setMinWidth(tableWidth / 4);
        col3.setMinWidth(tableWidth / 4);
        col4.setMinWidth(tableWidth / 4);

        table.getColumns().addAll(col1, col2, col3, col4);

        Label LabelUsername = new Label("Username: ");
        TextField usernameField = new TextField();

        Label LabelPassword = new Label("Password: ");
        TextField passwordField = new TextField();

        Button addBtn = new Button("Add");
        //addBtn.setOnAction(e -> addCustomer(primaryStage,usernameField.toString(), passwordField.toString(), users));
        //addBtn.setOnAction(e -> addCustomer(primaryStage,"test", "code", users));
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("add test");
                //customers.add(new CustomerData("Test","code",0));

                //users.add(new CustomerData(usernameField.getText(), passwordField.getText(),0));
                BSA.getAccountManager().addCustomer(usernameField.getText(), passwordField.getText());

                //for (UserEntity i : users){
                //if (i instanceof CustomerData){
                //System.out.println(i.getUsername());
                //}
                //}
                SetupOwnerCustomersScene(primaryStage);
            }
        });

        //addBtn.setOnAction(users.add(new CustomerData(usernameField.toString(), passwordField.toString(), 0)));
        Button deleteBtn = new Button("Delete Customer Data");
        deleteBtn.setOnAction(e -> deleteCustomer(primaryStage, table));

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> returnToOwnerMainMenu(primaryStage));

        FlowPane buttons = new FlowPane();
        buttons.setVgap(8);
        buttons.setHgap(4);
        buttons.setPrefWrapLength(100);
        buttons.setMinHeight(40);
        buttons.getChildren().add(LabelUsername);
        buttons.getChildren().add(usernameField);
        buttons.getChildren().add(LabelPassword);
        buttons.getChildren().add(passwordField);
        buttons.getChildren().add(addBtn);
        buttons.getChildren().add(deleteBtn);
        buttons.getChildren().add(backBtn);

        buttons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topParagraph);
        root.setCenter(table);
        root.setBottom(buttons);

        Owner_Customers_Scene = new Scene(root, defaultWidth, defaultHeight);

        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(Owner_Customers_Scene);
        primaryStage.show();

    }

    //private void addCustomer(Stage primaryStage, String s, String b, List<UserEntity> users) {
    //add logic here you have a ref to the BSA, ADD functions to BSA if you need to access the accountmanager or call BSA.getAccountManager() DONT add a reference to the account manager to OwnerGUI
    //users.add(new CustomerData(s,b,0));
    //}
    private void deleteCustomer(Stage primaryStage, TableView<CustomerData> table) {
        //add logic here you have a ref to the BSA, ADD functions to BSA if you need to access the accountmanager or call BSA.getAccountManager() DONT add a reference to the account manager to OwnerGUI
        for (CustomerData CD : table.getItems().filtered(CustomerData::isSelected)) {

            BSA.getAccountManager().removeCustomer(CD.getUsername());
        }

        SetupOwnerCustomersScene(primaryStage);
    }

    private void logoutSequence(Stage primaryStage) {
        LoginGUI GUI = new LoginGUI();
        GUI.setLoginPresenter(LM);

        GUI.logOutSequence(primaryStage);

    }
}
