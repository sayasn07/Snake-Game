import javafx.scene.shape.Rectangle;

// Represents a block in the game
public class Block extends Rectangle {
    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

    int posX, posY, oldPosX, oldPosY;

    Block previous;

    int direction = LEFT;

    int maxX, maxY;

    // Constructor to initialize a block
    public Block(int x, int y, Block p, Field f) {
        super(Sayasat_Sabit_230103164.block_size, Sayasat_Sabit_230103164.block_size);
        posX = x;
        posY = y;
        setTranslateX(posX * Sayasat_Sabit_230103164.block_size);
        setTranslateY(posY * Sayasat_Sabit_230103164.block_size);
        previous = p;
        maxX = f.getW();
        maxY = f.getH();
    }

    // Update the position of the block
    public void update() {
        oldPosX = posX;
        oldPosY = posY;

        if(previous == null) {
            switch(direction) {
                case UP :
                    moveUp();
                    break;
                case RIGHT :
                    moveRight();
                    break;
                case DOWN :
                    moveDown();
                    break;
                case LEFT :
                    moveLeft();
                    break;
            }
        }else{
            posX = previous.oldPosX;
            posY = previous.oldPosY;
        }
        if (posX < 0) {
            posX = maxX - 1;
        } else if (posX >= maxX) {
            posX = 0;
        }

        if (posY < 0) {
            posY = maxY - 1;
        } else if (posY >= maxY) {
            posY = 0;
        }
        updatePosition();
    }

    // Move the block up
    public void moveUp() {
        posY--;
        if(posY < 0) {
            posY = maxY - 1;
        }
    }

    // Move the block down
    public void moveDown() {
        posY++;
        if(posY >= maxY) {
            posY = 0;
        }
    }

    // Move the block left
    public void moveLeft() {
        posX--;
        if(posY < 0) {
            posX = maxX - 1;
        }
    }

    // Move the block right
    public void moveRight() {
        posX++;
        if(posX >= maxX) {
            posX = 0;
        }
    }

    // Update the position of the block on the screen
    public void updatePosition() {
        setTranslateX(posX * Sayasat_Sabit_230103164.block_size);
        setTranslateY(posY * Sayasat_Sabit_230103164.block_size);
    }
}
