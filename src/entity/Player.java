package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX, screenY;
    public boolean attackCancelled = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        type = "player";

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = gp.worldWidth / 600;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        invincible = false;
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }

    public void restoreLife() {
        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    @Override
    public void update() {
        if (isAttacking) {
            attack();
        }

        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if (keyH.enterPressed && !attackCancelled) {
                gp.playSE(7);
                isAttacking = true;
                spriteCounter = 0;
            }

            attackCancelled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;

            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                }
                else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

        // This needs to be outside of key statement
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.playSE(8);
        }
    }

    public void attack() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNumber = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX, worldY for the attack area
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster's collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            damageMonster(monsterIndex);

            // After checking collision, reset the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNumber = 1;
            spriteCounter = 0;
            isAttacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // TODO
        }
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCancelled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                gp.playSE(6);
                life--;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monsters[i].invincible) {
                gp.playSE(5);
                gp.monsters[i].life--;
                gp.monsters[i].invincible = true;
                gp.monsters[i].damageReaction();

                if (gp.monsters[i].life <= 0) {
                    gp.monsters[i].dying = true;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" -> {
                if (!isAttacking) {
                    if (spriteNumber == 1) image = up1;
                    if (spriteNumber == 2) image = up2;
                }
                if (isAttacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1) image = attackUp1;
                    if (spriteNumber == 2) image = attackUp2;
                }
            }
            case "down" -> {
                if (!isAttacking) {
                    if (spriteNumber == 1) image = down1;
                    if (spriteNumber == 2) image = down2;
                }
                if (isAttacking) {
                    if (spriteNumber == 1) image = attackDown1;
                    if (spriteNumber == 2) image = attackDown2;
                }
            }
            case "left" -> {
                if (!isAttacking) {
                    if (spriteNumber == 1) image = left1;
                    if (spriteNumber == 2) image = left2;
                }
                if (isAttacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1) image = attackLeft1;
                    if (spriteNumber == 2) image = attackLeft2;
                }
            }
            case "right" -> {
                if (!isAttacking) {
                    if (spriteNumber == 1) image = right1;
                    if (spriteNumber == 2) image = right2;
                }
                if (isAttacking) {
                    if (spriteNumber == 1) image = attackRight1;
                    if (spriteNumber == 2) image = attackRight2;
                }
            }
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset Alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
