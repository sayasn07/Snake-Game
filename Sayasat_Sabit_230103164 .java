import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

// Main application class
public class Sayasat_Sabit_230103164 extends Application{

    static int block_size = 20; // Size of each block in the game
    int width = 30, height = 25; // Dimensions of the game field
    int il = 3; // Initial length of the snake
    long before = System.nanoTime(); // For timing
    boolean changed = false; // To prevent rapid direction changes
    int nextUp; // Next direction if there is a pending change
    boolean hasNext = false; // Flag indicating a pending direction change
    private boolean paused = false; // Flag indicating whether the game is paused
    private Stage primaryStage;

    Field f; // Game field

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainMenu();
    }

    // Display the main menu
    private void showMainMenu() {
        MainMenu mainMenu = new MainMenu(this);
        mainMenu.start(primaryStage);
    }

    // Start the game
    public void startGame(Stage ps) {
        try {
            VBox root = new VBox(10);
            root.setPadding(new Insets(10));

            f = new Field(width, height);
            f.addSnake(new Snake(il, f));

            Label score = new Label("Score: 0");
            score.setFont(Font.font("tahoma", 32));

            Label pauseLabel = new Label("PAUSE");
            pauseLabel.setFont(Font.font("tahoma", 32));
            pauseLabel.setTextFill(Color.RED);
            pauseLabel.setVisible(false);

            BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, null, null);
            Background background = new Background(backgroundFill);
            root.setBackground(background);

            // Animation timer for game updates
            AnimationTimer timer = new AnimationTimer() {
                public void handle(long now) {
                    if (!paused) {
                        if (now - before > 1000000000 / f.snake.getSpeed()) {
                            f.update();

                            before = now;
                            score.setText("Score: " + f.score);
                            changed = false;
                            if (hasNext) {
                                setDirection(f.snake, nextUp);
                                hasNext = false;
                            }

                            if (f.isDead()) {
                                stop();

                                // Game over alert
                                Alert al = new Alert(AlertType.INFORMATION);
                                al.setHeaderText("Game Over");
                                al.setContentText("Your Score Is " + f.score);
                                Platform.runLater(al::showAndWait);

                                al.setOnHidden(e -> {
                                    root.getChildren().clear();
                                    f = new Field(width, height);
                                    f.addSnake(new Snake(il, f));
                                    score.setText("Score: 0");
                                    root.getChildren().addAll(f, score);

                                    start();
                                });
                            }
                        }
                    }
                    if (paused) {
                        // Display "PAUSE" label
                        pauseLabel.setVisible(true);
                    } else {
                        // Hide "PAUSE" label
                        pauseLabel.setVisible(false);
                    }
                }
            };
            timer.start();

            root.getChildren().addAll(f, score, pauseLabel);

            Scene scene = new Scene(root);

            // Handle user input
            scene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ESCAPE) {
                    // Exit the game
                    primaryStage.close(); 
                } else if (!paused) {
                    // Handle directional input
                    if (e.getCode().equals(KeyCode.W) && f.snake.getDirection() != Block.DOWN) {
                        setDirection(f.snake, Block.UP);
                    }
                    if (e.getCode().equals(KeyCode.S) && f.snake.getDirection() != Block.UP) {
                        setDirection(f.snake, Block.DOWN);
                    }
                    if (e.getCode().equals(KeyCode.D) && f.snake.getDirection() != Block.LEFT) {
                        setDirection(f.snake, Block.RIGHT);
                    }
                    if (e.getCode().equals(KeyCode.A) && f.snake.getDirection() != Block.RIGHT) {
                        setDirection(f.snake, Block.LEFT);
                    }
                }
                if (e.getCode().equals(KeyCode.SPACE)) {
                    paused = !paused;
                }
            });

            ps.setResizable(false);
            ps.setScene(scene);
            ps.setTitle("Snake Game");

            ps.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    // Set the direction of the snake
    public void setDirection(Snake s, int d) {
        if(!changed) {
            s.setDirection(d);
            changed = true;
        }else{
            hasNext = true;
            nextUp = d;
        }
    }

    // Entry point of the application
    public static void main(String[] args) {
        launch(args);
    }

}
