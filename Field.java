import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

// Represents the game field
public class Field extends Pane {
    private int w, h; // Width and height of the field

    ArrayList<Block> blocks = new ArrayList<>(); // List to store all blocks on the field
    int score = 0; // Player's score

    Food f; // Food object
    Snake snake; // Snake object

    // Constructor to initialize the field
    public Field(int width, int height) {
        w = width;
        h = height;

        setMinSize(w * Sayasat_Sabit_230103164.block_size, h * Sayasat_Sabit_230103164.block_size);
        setBackground(new Background(new BackgroundFill(Color.web("#9ACD32"), null, null))); // Set background color
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1)))); // Set border
        addFood(); // Add initial food to the field
    }

    // Add the snake to the field
    public void addSnake(Snake s) {
        snake = s;
        for (Block b : s.blocks) {
            addBlock(b);
        }
    }

    // Update the game state
    public void update() {
        for (Block b : blocks) {
            b.update(); // Update each block's position
        }

        if (isEaten(f)) {
            score++; // Increase the score when the snake eats food
            addFood(); // Add new food
            addNewBlock(); // Increase the length of the snake

            snake.increaseSpeed(); // Increase the snake's speed
        }
    }

    // Check if the snake has collided with itself
    public boolean isDead() {
        for (Block b : blocks) {
            if (b != snake.head) {
                if (b.posX == snake.head.posX && b.posY == snake.head.posY) {
                    return true; // Snake has collided with itself
                }
            }
        }
        return false; // Snake is alive
    }

    // Add a new block to the snake
    public void addNewBlock() {
        Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
        snake.tail = b;

        addBlock(b);
    }

    // Add a block to the field
    private void addBlock(Block b) {
        getChildren().add(b);
        blocks.add(b);
    }

    // Add new food to the field at a random position
    public void addFood() {
        int randomX = (int) (Math.random() * w);
        int randomY = (int) (Math.random() * h);

        Food food = new Food(randomX, randomY);
        getChildren().add(food);
        getChildren().remove(f); // Remove the old food
        f = food;
    }

    // Check if the snake has eaten the food
    public boolean isEaten(Food f) {
        if (f == null) {
            return false; // No food on the field
        }
        return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;
    }

    // Getter for the width of the field
    public int getW() {
        return w;
    }

    // Getter for the height of the field
    public int getH() {
        return h;
    }
}
