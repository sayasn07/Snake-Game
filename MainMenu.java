import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

// Main menu of the Snake Game
public class MainMenu extends Application {

    private Sayasat_Sabit_230103164 mainUI;
    private Stage stage;

    // Constructor to link with the main UI
    public MainMenu(Sayasat_Sabit_230103164 mainUI) {
        this.mainUI = mainUI;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Create play and exit buttons
        Button playButton = createStyledButton("Play", "#4CAF50");
        Button exitButton = createStyledButton("Exit", "#f44336");

        // Set actions for the buttons
        playButton.setOnAction(e -> startGame());
        exitButton.setOnAction(e -> Platform.exit());

        // Create a vertical box for the menu
        VBox menuBox = new VBox(20, playButton, exitButton);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));

        // Create the scene with the menu
        Scene scene = new Scene(menuBox, 595, 200);
        stage.setTitle("Snake Game");
        stage.setScene(scene);

        // Add animation to the buttons
        addAnimation(playButton);
        addAnimation(exitButton);

        stage.show();
    }

    // Create a styled button with specified text and background color
    private Button createStyledButton(String text, String backgroundColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-size: 20px;");

        // Add drop shadow effect on mouse hover
        DropShadow shadow = new DropShadow(10, Color.GRAY);
        button.setOnMouseEntered(e -> button.setEffect(shadow));
        button.setOnMouseExited(e -> button.setEffect(null));

        return button;
    }

    // Start the game when the play button is clicked
    private void startGame() {
        mainUI.startGame(stage);
    }

    // Add translation animation to a button
    private void addAnimation(Button button) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), button);
        transition.setFromY(-50);
        transition.setToY(0);
        transition.play();
    }

    // Main method to launch the application
    public static void main(String args[]) {
        launch(args);
    }
}
