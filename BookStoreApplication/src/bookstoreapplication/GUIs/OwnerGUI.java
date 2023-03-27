/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

//import java.awt.Color;
import bookstoreapplication.GUIs.ApplicationGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 *
 * @author deeps
 */
public class OwnerGUI extends ApplicationGUI{
        public void accessUI(Stage primaryStage) {
            Button btn1 = new Button();
            String buttonStyle = "-fx-font-family: Helvetica; -fx-background-color: #fff; -fx-font-size: 16pt;";
            btn1.setText("Books");
            btn1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Books test");
                }
            });
            Button btn2 = new Button();
            btn2.setText("Customers");
            btn2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Customer test");
                }
            });
            Button btn3 = new Button();
            btn3.setText("Logout");
            btn3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Logout Test");
                }
            });
            
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
            
            Scene scene = new Scene(root, 800, 550);

            primaryStage.setTitle("Book Store Application");
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}
