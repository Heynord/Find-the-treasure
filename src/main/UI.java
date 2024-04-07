package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage keyImage;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public boolean messageOn = false;
    public String message = "";
    double playTime;
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNumber = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity key = new OBJ_Key(gp);
        keyImage = key.down1;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayerKeys();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPlayerKeys();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        // CONGRATULATION STATE
        if (gp.gameState == gp.congratulationState) {
            drawCongratulationScreen();
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPlayerKeys() {
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.drawString("x " + gp.player.hasKey, 80, 130);
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize * 2, gp.tileSize, gp.tileSize, null);

        // TIME
        playTime += (double) 1/60;
//        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

        // MESSAGE
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30f));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    public void drawTitleScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Find the treasure";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;

        // SHADOW
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // CHARACTER IMAGE
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Menu";
        // Shadow
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        // Continue
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Continue";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Restart
        text = "Restart";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Back to the Title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNumber == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    private void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCongratulationScreen() {
        g2.setFont(maruMonica);
        g2.setColor(Color.YELLOW);
        g2.setFont(g2.getFont().deriveFont(60f));

        String text;
        int textLength;
        int x;
        int y;

        text = "Congratulations!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 4);
        g2.drawString(text, x, y);

        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30f));

        text = "You found the treasure!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        text = "Your time is: " + dFormat.format(playTime);
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 2);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));

        // Restart
        text = "Restart";
        x = getXForCenteredText(text);
        y += 185;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Back to the Title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // Shadow
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNumber == 0) {
            g2.drawString(">", x - 40, y);
        }

        // Back to the Title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNumber == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
