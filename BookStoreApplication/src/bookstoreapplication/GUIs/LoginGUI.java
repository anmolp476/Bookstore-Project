/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

import bookstoreapplication.LoginManager;
import bookstoreapplication.Viewable;
import java.awt.geom.AffineTransform;
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
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Victor
 */
public class LoginGUI implements Viewable {

    private LoginManager loginManager;

    private Scene scene, scene1, scene2;
    public static final int defaultWidth = 900;
    public static final int defaultHeight = 600;
    final int spacing = 15;

    public void setLoginPresenter(LoginManager presenter) {
        loginManager = presenter;
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
        accessSignUpGUI(primaryStage, signUpButton, scene);

        // Main Layout
        VBox layout = createLayout(buttons);
        //layout.getChildren().add(mediaView);
        scene = new Scene(layout, defaultWidth, defaultHeight);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {//THIS CHANGES BEHAVIOR OF X BUTTON
            public void handle(WindowEvent we) {
                logOutSequence(primaryStage);
            }
        });

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

    /**
     * Creates a scene where the user can create an account on the program
     *
     * @param primaryStage The Stage the GUI uses
     * @param button The button that will send the Stage to the scene when
     * activated
     * @param goBackScene The return scene
     * @return returns the sign up scene
     */
    public Scene accessSignUpGUI(Stage primaryStage, Button button, Scene goBackScene) {
        button.setOnAction(e -> primaryStage.setScene(scene2));

        // Layout 1
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(spacing);
        grid.setVgap(spacing);

        // Labels
        List<Label> labelList = createLabelList(
                Arrays.asList("username", "password"), 2, "Enter your new ");
        Label confirmLabel = new Label();

        // TextFields
        TextField nameInput = createTextField("username", 0, 2);
        TextField passInput = createTextField("password", 0, 4);

        // Buttons
        Button confirmButton = signUpConfirm(nameInput, passInput, confirmLabel);
        grid.getChildren().add(confirmLabel);
        Button returnButton = signUpButtons(confirmLabel, confirmButton, Arrays.asList(nameInput, passInput), primaryStage, goBackScene);

        // Layout 2
        signUpLayout(grid, labelList, Arrays.asList(confirmButton, returnButton), Arrays.asList(nameInput, passInput));

        scene2 = new Scene(grid, defaultWidth, defaultHeight);
        return scene2;
    }

    private Button signUpButtons(Label confirmLabel, Button confirmButton, List<TextField> textFields, Stage primaryStage, Scene goBackScene) {
        GridPane.setConstraints(confirmButton, 0, 8, 1, 1, HPos.LEFT, VPos.CENTER);
        Button returnButton = new Button("Cancel");
        returnButton.setOnAction(e -> {
            for (TextField textField : textFields) {
                System.out.println("clearning text fields");
                textField.clear();
            }
            System.out.println("Going back to start menu");
            confirmLabel.setText("");
            primaryStage.setScene(scene);

        });
        GridPane.setConstraints(returnButton, 0, 8, 1, 1, HPos.RIGHT, VPos.CENTER);
        return returnButton;
    }

    private Button signUpConfirm(TextField nameInput, TextField passInput, Label confirmLabel) {
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            if (loginManager.signUp(nameInput.getText(), passInput.getText())) {
                confirmLabel.setText("Signed up successfully!");
            } else {
                confirmLabel.setText(
                        "Either the username already exists or there in an invalid input. Please try again.");
            }
            GridPane.setConstraints(confirmLabel, 0, 9);
        });
        return confirmButton;
    }

    private void signUpLayout(GridPane grid, List<Label> labelList, List<Button> buttonList, List<TextField> textFields) {
        Text title = new Text("Sign Up");
        title.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 18));
        grid.getChildren().add(title);
        grid.getChildren().addAll(labelList);
        grid.getChildren().addAll(buttonList);
        grid.getChildren().addAll(textFields);
        grid.setAlignment(Pos.CENTER);
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
            if (loginManager.login(nameInput.getText(), passInput.getText())) {
                nameSystem.setText("Log in Successful!");
                System.out.println("login sucessful");
                loginManager.createApplicationGUI(nameInput.getText(), primaryStage, this).accessUI(primaryStage);
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
        button3.setOnAction(e -> logOutSequence(primaryStage, true));
        return button3;
    }

    public void logOutSequence(Stage primaryStage) {
        logOutSequence(primaryStage, false);
    }

    public void logOutSequence(Stage primaryStage, boolean ShutDown) {

        ImageView gifView = new ImageView();
        ImageView gifView2 = new ImageView();

        String musicFile = "Media/shutdown.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.25);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setStopTime(Duration.seconds(2));
        if (!ShutDown) {
            mediaPlayer.setOnEndOfMedia(() -> accessUI(primaryStage));
        } else {
            gifView.setFitWidth(LoginGUI.defaultWidth / 2); // set the width of the GIF image
            gifView.setFitHeight(LoginGUI.defaultHeight /2); // set the height of the GIF image
            gifView.setPreserveRatio(true);
            gifView.setTranslateX(-gifView.getFitWidth() / 1.5); // set the x position of the GIF image
            gifView.setTranslateY(150); // set the y position of the GIF image
            String gifFile = "Media/KeFqCfBzmPCEuovZDfHBJ.gif";
            Image gifImage = new Image(new File(gifFile).toURI().toString());
            gifView.setImage(gifImage);

            gifView2.setFitWidth(LoginGUI.defaultWidth / 2); // set the width of the GIF image
            gifView2.setFitHeight(LoginGUI.defaultHeight / 2); // set the height of the GIF image
            gifView2.setPreserveRatio(true);
            gifView2.setTranslateX(gifView.getFitWidth()/2-25); // set the x position of the GIF image
            gifView2.setTranslateY(150); // set the y position of the GIF image
            gifView2.setImage(gifImage);
            AffineTransform tx = new AffineTransform(-1, 0, 0, 1, gifView2.getImage().getWidth(), 0);
            // Convert AffineTransform to javafx.scene.transform.Affine
            javafx.scene.transform.Affine flip = new javafx.scene.transform.Affine(
                    tx.getScaleX(), tx.getShearY(), tx.getTranslateX(),
                    tx.getShearX(), tx.getScaleY(), tx.getTranslateY());
            gifView2.getTransforms().add(flip);

            mediaPlayer.setOnEndOfMedia(() -> System.exit(0));
        }
        MediaView mediaView = new MediaView(mediaPlayer);
        System.out.println("Shutting down ...");

        Text endCredits = new Text("Brought to you by, Group_3");
        endCredits.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 36));

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: white;");
        layout.getChildren().add(gifView);
        layout.getChildren().add(gifView2);

        layout.getChildren().add(endCredits);
        StackPane.setAlignment(endCredits, Pos.CENTER);
        scene = new Scene(layout, defaultWidth, defaultHeight);
        primaryStage.setScene(scene);

        loginManager.shutdownSequence(); //IMPLEMENT THIS LATER <<CALS SAVING FUNCTION

        primaryStage.show();

    }

}
