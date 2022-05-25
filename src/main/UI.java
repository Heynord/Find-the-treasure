package main;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font comic_sans_40, comic_sans_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        comic_sans_40 = new Font("Comic Sans MS", Font.PLAIN, 40);
        comic_sans_80B = new Font("Comic Sans MS", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(comic_sans_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
            // TODO
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
