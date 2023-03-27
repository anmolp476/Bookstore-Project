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
        Label topParagraph = new Label("Welcome " + LM.getCurrentUser().getUsername() + ". You have " + " points. Your status is Y.");
        BorderPane.setAlignment(topParagraph, Pos.CENTER);
     
        BorderPane root = new BorderPane();
        root.setTop(topParagraph);
        Scene scene = new Scene(root, LoginGUI.defaultWidth, LoginGUI.defaultHeight);

        primaryStage.setTitle("Book Store Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Checkboxes
        //List<CheckBox> checkBoxes = createCheckBoxes(Arrays.asList("Attendee", "Organizer", "Speaker", "VIP"));
        //checkBoxCheck(checkBoxes);
    }
}
