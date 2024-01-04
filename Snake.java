import java.util.ArrayList;
import javafx.scene.paint.Color;

// Represents the snake in the game
public class Snake {
    ArrayList<Block> blocks = new ArrayList<>();

    Block head;
    Block tail;
    private int speed = 8;

    // Constructor to initialize the snake
    public Snake(int il, Field f) {
        int ipx = f.getW() / 2;
        int ipy = f.getH() / 2;

        head = new Block(ipx, ipy, null, f);
        blocks.add(head);

        head.setFill(Color.WHITE.desaturate());

        tail = head;

        for(int i = 1; i<il; i++) {
            Block b = new Block(ipx + i, ipy, tail, f);
            blocks.add(b);
            tail = b;
        }
    }

    // Getter for the current direction of the snake
    public int getDirection() {
        return head.direction;
    }

    // Setter for the direction of the snake
    public void setDirection(int d) {
        head.direction = d;
    }

    // Increase the speed of the snake when it eats food
    public void increaseSpeed() {
        speed++;
    }

    // Getter for the current speed of the snake
    public int getSpeed() {
        return speed;
    }
}
