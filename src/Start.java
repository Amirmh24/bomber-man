import java.io.Serializable;
import java.util.Random;
//a class for build and organize the map before game starts
public class Start implements Serializable {
    Random random = new Random();
    int LEVEL;

    public Start(int width, int height, GameHandler gameHandler, int LEVEL) {
        this.LEVEL = LEVEL;
        gameHandler.getGameMemory().gameMe[1][1] = new Bomberman(gameHandler);;
       for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
                    gameHandler.getGameMemory().gameObjects[i][j] = new BlockFrame();
                } else {
                    if (i % 2 == 1) {
                        gameHandler.getGameMemory().gameObjects[i][j] = new Grass();
                    } else {
                        if ((i + j) % 2 == 0) {
                            gameHandler.getGameMemory().gameObjects[i][j] = new Block();
                        } else {
                            gameHandler.getGameMemory().gameObjects[i][j] = new Grass();
                        }
                    }
                }
            }
        }
        while (!gameHandler.getGameMemory().hasDoor()) {
            int randwidth = random.nextInt(width - 4) + 4;
            int randheight = random.nextInt(height - 4) + 4;
            if (gameHandler.getGameMemory().gameObjects[randwidth][randheight] instanceof Grass) {
                gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new Door();
            }
        }
        for (int i = 0; i < width * height / 10; i++) {
            int randwidth = random.nextInt(width - 2) + 2;
            int randheight = random.nextInt(height - 2) + 2;
            if (gameHandler.getGameMemory().gameObjects[randwidth][randheight] instanceof Grass) {
                gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new Wall();
            } else {
                i--;
            }
        }

        for (int i = 0; i <width * height / 50+LEVEL; i++) {
            int randwidth = random.nextInt(width - 2) + 2;
            int randheight = random.nextInt(height - 2) + 2;
            int powerUpTypeNum=random.nextInt(LEVEL+3);
            if (gameHandler.getGameMemory().gameObjects[randwidth][randheight] instanceof Grass) {
                if (powerUpTypeNum%10==0) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new IncreaseSpeed();
                }if (powerUpTypeNum%10==1) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new IncreaseBomb();
                } if (powerUpTypeNum%10==2) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new IncreaseScore();
                } if (powerUpTypeNum%10==3) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new IncreaseRadius();
                } if (powerUpTypeNum%10==4) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new ControlBomb();
                } if (powerUpTypeNum%10==5) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new GhostMode();
                } if (powerUpTypeNum%10==6) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new DecreaseRadius();
                } if (powerUpTypeNum%10==7) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new DecreaseBomb();
                } if (powerUpTypeNum%10==8) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new DecreaseScore();
                } if (powerUpTypeNum%10==9) {
                    gameHandler.getGameMemory().gameObjects[randwidth][randheight] = new DereaseSpeed();
                }

            } else {
                i--;
            }
        }

        for (int i = 0; i <LEVEL*width/5; i++) {
            int randwidth = random.nextInt(width - 3) + 3;
            int randheight = random.nextInt(height - 3) + 3;
            int enemyTypeNum=random.nextInt(LEVEL);
            if (gameHandler.getGameMemory().gameObjects[randwidth][randheight] instanceof Grass && !(gameHandler.getGameMemory().gameBeings[randwidth][randheight] instanceof Enemy)) {
                if(enemyTypeNum%4==0) {
                    gameHandler.getGameMemory().gameBeings[randwidth][randheight] = new Enemy1(gameHandler, new Location(randwidth, randheight));
                }
                if(enemyTypeNum%4==1) {
                    gameHandler.getGameMemory().gameBeings[randwidth][randheight] = new Enemy2(gameHandler, new Location(randwidth, randheight));
                }
                if(enemyTypeNum%4==2) {
                    gameHandler.getGameMemory().gameBeings[randwidth][randheight] = new Enemy3(gameHandler, new Location(randwidth, randheight));
                }
                if(enemyTypeNum%4==3) {
                    gameHandler.getGameMemory().gameBeings[randwidth][randheight] = new Enemy4(gameHandler, new Location(randwidth, randheight));
                }
            } else {
                i--;
            }
        }
        gameHandler.repaint();
    }
}
