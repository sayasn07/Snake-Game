import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Random;

// Represents the food in the game
public class Food extends Circle {
    int posX, posY;

    // Getter for the X coordinate of the food
    public int getPosX() {
        return posX;
    }

    // Getter for the Y coordinate of the food
    public int getPosY() {
        return posY;
    }

    // Constructor to initialize the food
    public Food(int x, int y) {
        super(Sayasat_Sabit_230103164.block_size / 2, Color.TRANSPARENT); // Set the size and color of the food
        posX = x;
        posY = y;
        setTranslateX((posX + 0.5) * Sayasat_Sabit_230103164.block_size); // Set the center coordinates of the food
        setTranslateY((posY + 0.5) * Sayasat_Sabit_230103164.block_size);

        setFill(generateRandomColor()); // Set a random color for the food
    }

    private static final Color[] ALLOWED_COLORS = {
            Color.RED, Color.ORANGE, Color.VIOLET, Color.BLUE,
            Color.CYAN, Color.YELLOW, Color.PINK, Color.MAROON
    };

    // Generate a random color for the food
    private Color generateRandomColor() {
        Random random = new Random();
        return ALLOWED_COLORS[random.nextInt(ALLOWED_COLORS.length)];
    }
}
