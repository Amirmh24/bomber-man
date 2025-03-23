import java.io.Serializable;

public class GameMemory implements Serializable{
    //    these seprated arrays is for making possible to put 2or more objects(with different types) in one cell(for example a bomb and bomberman can be in a joint cell)

    //    gameObject contains Wall,Block,BlockFrame,BehindWallobjects(Door,PowerUps),Grass,bomb
    GameObject[][] gameObjects;
    //    gameBeings contains Enemies type1,2,3,4
    GameObject[][] gameBeings;
    //    gameObject contains bomberman
    GameObject[][] gameMe;
    //    gameObject contains Fire
    GameObject[][] gameFire;
//  powerUp parameters should be save in game memory
    private PowerUpParameters powerUpParameters;
//    length of arrays
    private int gameObjectsWidth;
    private int gameObjectsHeight;

    private GameFrame gameFrame;
    private boolean gameOverByTime;
    int score;

    public GameMemory(GameFrame gameFrame,int score,PowerUpParameters powerUpParameters) {
        this.gameFrame=gameFrame;
        this.score=score;
        this.powerUpParameters=powerUpParameters;
        gameObjectsWidth=gameFrame.width;
        gameObjectsHeight=gameFrame.height;
        gameObjects = new GameObject[this.getGameObjectsWidth()][this.getGameObjectsHeight()];
        gameBeings = new GameObject[this.getGameObjectsWidth()][this.getGameObjectsHeight()];
        gameMe = new GameObject[this.getGameObjectsWidth()][this.getGameObjectsHeight()];
        gameFire=new GameObject[this.getGameObjectsWidth()][this.getGameObjectsHeight()];
    }
// The game map needs only one door
     boolean hasDoor() {
        for (int i = 0; i < getGameObjectsWidth(); i++) {
            for (int j = 0; j < getGameObjectsHeight(); j++) {
                if (gameObjects[i][j] instanceof Door) {
                    return true;
                }
            }
        }
        return false;
    }
//if all enemies have been killed we can go next level
    public boolean canGoNextLevel() {
        for (int i = 0; i < getGameObjectsWidth(); i++) {
            for (int j = 0; j < getGameObjectsHeight(); j++) {
                if (gameBeings[i][j] instanceof Enemy) {
                    return false;
                }
            }
        }
        return true;
    }
//to stop timer and all movements of bomberman and enemies when game finished when you losed
    public void stopGame() {
        for (int i = 0; i < getGameObjectsWidth(); i++) {
            for (int j = 0; j < getGameObjectsHeight(); j++) {
                if (gameBeings[i][j] instanceof Enemy) {
                    ((Enemy) (gameBeings[i][j])).setAlive(false);
                }
            }
        }
        gameOverByTime =true;
        gameFrame.gameTime.getTimer().stop();
        gameFrame.infoPanel.repaint();
    }
//to get the bomberman in solo mode
    public Bomberman getBomberman() {
        for (int i = 0; i < getGameObjectsWidth(); i++) {
            for (int j = 0; j < getGameObjectsHeight(); j++) {
                if (gameMe[i][j] instanceof Bomberman) {
                    return (Bomberman)(gameMe[i][j]);
                }
            }
        }
        return null;
    }

    public boolean isGameOverByTime() {
        return gameOverByTime;
    }

    public void setGameOverByTime(boolean gameOverByTime) {
        this.gameOverByTime = gameOverByTime;
    }

    public synchronized GameObject[][] getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(GameObject[][] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public GameObject[][] getGameBeings() {
        return gameBeings;
    }

    public void setGameBeings(GameObject[][] gameBeings) {
        this.gameBeings = gameBeings;
    }

    public int getGameObjectsWidth() {
        return gameObjectsWidth;
    }

    public void setGameObjectsWidth(int gameObjectsWidth) {
        this.gameObjectsWidth = gameObjectsWidth;
    }

    public int getGameObjectsHeight() {
        return gameObjectsHeight;
    }

    public void setGameObjectsHeight(int gameObjectsHeight) {
        this.gameObjectsHeight = gameObjectsHeight;
    }
    public GameObject[][] getGameMe() {
        return gameMe;
    }

    public void setGameMe(GameObject[][] gameMe) {
        this.gameMe = gameMe;
    }

    public PowerUpParameters getPowerUpParameters() {
        return powerUpParameters;
    }

    public void setPowerUpParameters(PowerUpParameters powerUpParameters) {
        this.powerUpParameters = powerUpParameters;
    }
}
