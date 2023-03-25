/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Victor
 */
public class LoginGUI implements Viewable {

    private LoginPresenter loginPresenter;

    private Scene scene, scene1, scene2;
    final int defaultWidth = 600;
    final int defaultHeight = 400;
    final int spacing = 15;

    public void setLoginPresenter(LoginPresenter presenter) {
        loginPresenter = presenter;
    }

    public void accessUI(Stage primaryStage) {
        // Opening Sound
        //String musicFile = "phase2/sound1.mp3";
        //Media sound = new Media(new File(musicFile).toURI().toString());
        //MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.setVolume(0.25);
        //mediaPlayer.setAutoPlay(true);
        //MediaView mediaView = new MediaView(mediaPlayer);
        primaryStage.setTitle("BookstoreApplication - Window");

        // Buttons
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Button cancelButton = new Button("Cancel");
        Button exitButton = createExitButton(primaryStage);

        // Button list
        List<Button> buttons = new ArrayList<>(Arrays.asList(loginButton, signUpButton, exitButton));
        for (Button button : buttons) {
            button.setMaxSize(200, 30);
            button.setMinSize(200, 30);
        }

        // Login
        accessLoginGUI(primaryStage, loginButton, cancelButton);
        
        // Sign Up (implement the signup button)
        //accessSignUpGUI(primaryStage, signUpButton, scene);
        
        // Main Layout
        VBox layout = createLayout(buttons);
        //layout.getChildren().add(mediaView);
        scene = new Scene(layout, defaultWidth, defaultHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void accessLoginGUI(Stage primaryStage, Button loginButton, Button returnButton) {
        loginButton.setOnAction(e -> primaryStage.setScene(scene1));

        // Layout 1
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(spacing);
        grid.setVgap(spacing);

        // Labels
        List<Label> labelList = createLabelList(Arrays.asList("username", "password"), 1, "Enter your ");
        Label loginSystem = new Label("Please type in your username and password");
        GridPane.setConstraints(loginSystem, 0, 5, 4, 1, HPos.CENTER, VPos.CENTER);

        // TextFields
        TextField nameInput = createTextField("username", 1, 1);

        // PasswordFields
        PasswordField passInput = createPasswordField();

        // Layout 2
        Text title = new Text("Login");
        title.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 18));
        GridPane.setConstraints(title, 1, 0);
        grid.getChildren().add(title);
        grid.getChildren().addAll(labelList);
        grid.getChildren().addAll(returnButton, loginSystem, nameInput, passInput,
                loginHelper(nameInput, passInput, loginSystem, primaryStage, returnButton));
        grid.setAlignment(Pos.CENTER);
        scene1 = new Scene(grid, defaultWidth, defaultHeight);
    }

    private Button loginHelper(TextField nameInput, PasswordField passInput, Label loginSystem, Stage primaryStage,
            Button returnButton) {
        Button nameConfirm = loginConfirm(nameInput, passInput, loginSystem, primaryStage);
        returnButton.setOnAction(e -> {
            nameInput.clear();
            passInput.clear();
            loginSystem.setText("Please type in your username and password");
            primaryStage.setScene(scene);
        });
        GridPane.setConstraints(returnButton, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        return nameConfirm;
    }

    private Button loginConfirm(TextField nameInput, PasswordField passInput, Label nameSystem, Stage primaryStage) {
        Button nameConfirm = new Button("Confirm");
        nameConfirm.setOnAction(e -> {
            if (loginPresenter.login(nameInput.getText(), passInput.getText())) {
                nameSystem.setText("Log in Successful!");
                loginPresenter.createApplicationGUI(
                        nameInput.getText(), primaryStage, this).accessUI(primaryStage);
            } else {
                nameSystem.setText("Incorrect Username or Password. Please Try again.");
            }
        });
        GridPane.setConstraints(nameConfirm, 1, 3);
        return nameConfirm;
    }

    private List<Label> createLabelList(List<String> asList, int increment, String offset) {
        List<Label> labelList = new ArrayList<>();
        int i = 1;
        int j = 0;
        while (j < asList.size()) {
            labelList.add(createLabel(offset + asList.get(j), i));
            i = i + increment;
            j++;
        }
        return labelList;
    }

    private Label createLabel(String input, int rowIndex) {
        Label label = new Label(input);
        GridPane.setConstraints(label, 0, rowIndex);
        return label;
    }

    private PasswordField createPasswordField() {
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 2);
        return passInput;
    }

    private TextField createTextField(String input, int columnIndex, int rowIndex) {
        TextField textField = new TextField();
        textField.setPromptText(input);
        GridPane.setConstraints(textField, columnIndex, rowIndex);
        return textField;
    }

    private VBox createLayout(List<Button> buttons) {
        VBox layout = new VBox(spacing);
        Text title = new Text("Bookstore Application - Login / Sign Up");
        title.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 18));
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(0, 0, 0, 0));
        layout.getChildren().add(title);
        layout.getChildren().addAll(buttons);
        return layout;
    }

    private Button createExitButton(Stage primaryStage) {
        Button button3 = new Button("Quit");
        button3.setOnAction(e -> shutdownSequence(primaryStage));
        return button3;
    }

    private void shutdownSequence(Stage primaryStage) {
        String musicFile = "Media/shutdown.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.25);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(primaryStage::close);
        MediaView mediaView = new MediaView(mediaPlayer);
        System.out.println("Shutting down ...");

        Text endCredits = new Text("Brought to you by, Group_3");
        endCredits.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 36));

        StackPane layout = new StackPane();
        //layout.getChildren().add(mediaView);
        layout.getChildren().add(endCredits);
        StackPane.setAlignment(endCredits, Pos.CENTER);
        scene = new Scene(layout, defaultWidth, defaultHeight);
        primaryStage.setScene(scene);

        loginPresenter.shutdownSequence(); //IMPLEMENT THIS LATER
        
        primaryStage.show();
        
        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 
        2000 
);
        //
    }

}
