package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // DEBUG
    public boolean showDebugText = false;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {titleState(code);}

        // PLAY STATE
        else if (gp.gameState == gp.playState) {playState(code);}

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {pauseState(code);}

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {dialogueState(code);}

        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {gameOverState(code);}

        // CONGRATULATION STATE
        else if (gp.gameState == gp.congratulationState) {congratulationState(code);}
    }

    public void titleState(int code) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 1;
                }
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 1) {
                    gp.ui.commandNumber = 0;
                }
            }
            case KeyEvent.VK_ENTER -> {
                switch (gp.ui.commandNumber) {
                    case 0 -> {
                        gp.restart();
                        gp.gameState = gp.playState;
                    }
                    case 1 -> System.exit(0);
                }
            }
        }
    }

    public void playState(int code) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_ESCAPE -> {
                gp.gameState = gp.pauseState;
                gp.stopMusic();
            }
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> enterPressed = true;

            // DEBUG
            case KeyEvent.VK_T -> showDebugText = !showDebugText;
        }
    }

    public void pauseState(int code) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 2;
                }
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 2) {
                    gp.ui.commandNumber = 0;
                }
            }
            case KeyEvent.VK_ESCAPE -> {
                gp.ui.commandNumber = 0;
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            case KeyEvent.VK_ENTER -> {
                switch (gp.ui.commandNumber) {
                    case 0 -> {
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    case 1 -> {
                        gp.ui.commandNumber = 0;
                        gp.gameState = gp.playState;
                        gp.restart();
                        gp.playMusic(0);
                    }
                    case 2 -> {
                        gp.ui.commandNumber = 0;
                        gp.gameState = gp.titleState;
                        gp.playMusic(0);
                    }
                }
            }
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            gp.gameState = gp.playState;
        }
    }

    public void gameOverState(int code) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 1;
                }
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 1) {
                    gp.ui.commandNumber = 0;
                }
            }
            case KeyEvent.VK_ENTER -> {
                switch (gp.ui.commandNumber) {
                    case 0 -> {
                        gp.gameState = gp.playState;
                        gp.stopSE();
                        gp.retry();
                    }
                    case 1 -> {
                        gp.gameState = gp.titleState;
                        gp.stopSE();
                        gp.restart();
                    }
                }
            }
        }
    }

    private void congratulationState(int code) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 1;
                }
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 1) {
                    gp.ui.commandNumber = 0;
                }
            }
            case KeyEvent.VK_ENTER -> {
                switch (gp.ui.commandNumber) {
                    case 0 -> {
                        gp.gameState = gp.playState;
                        gp.restart();
                        gp.stopSE();
                        gp.playMusic(0);
                    }
                    case 1 -> {
                        gp.ui.commandNumber = 0;
                        gp.gameState = gp.titleState;
                        gp.stopSE();
                        gp.playMusic(0);
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = false;
        }
    }
}
