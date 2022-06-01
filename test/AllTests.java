import entity.Entity;
import entity.NPC_OldMan;
import entity.Player;
import main.GamePanel;
import main.KeyHandler;
import monster.MON_GreenSlime;
import object.OBJ_Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Objects;

import static org.mockito.Mockito.mock;

public class AllTests {

    @Test
    public void PlayerDamageTest() {
        GamePanel gp = new GamePanel();
        Player player = new Player(gp, mock(KeyHandler.class));
        player.life = 6;
        player.contactMonster(0);
        Assertions.assertEquals(5, player.life);
    }

//    @Test
//    public void GivePlayerTest() {
//        GamePanel gp = new GamePanel();
//        Player player = new Player(gp, mock(KeyHandler.class));
//        Entity item = mock(Entity.class);
//        item.name = "key";
//        player.give(item);
//        ArrayList<Entity> items = new ArrayList<>();
//        items.add(item);
//        Assertions.assertEquals(items, player.items);
//    }

//    @Test
//    public void GivePlayerTest_fullInventory() {
//        GamePanel gp = new GamePanel();
//        Player player = new Player(gp, mock(KeyHandler.class));
//        Entity item = mock(Entity.class);
//        item.name = "key";
//        ArrayList<Entity> items = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            items.add(item);
//        }
//        player.items = items;
//        player.give(item);
//        Assertions.assertEquals(items, player.items);
//    }

//    @Test
//    public void ImageTest() {
//        String res = "npc/oldman_down_1.png";
//        try {
//            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + res)));
//            Assertions.assertTrue(ImageGetter.compareImages(image, ImageGetter.getImage(res)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void openDoorTest() {
//        OBJ_Door door = new OBJ_Door(mock(GamePanel.class));
//        Assertions.assertEquals(door.setup("/objects/door"), door.down1);
//        door.image2 = mock(BufferedImage.class);
//        door.coords.add(new TileParsed.Coords(0,0));
//        Assertions.assertEquals(door.getImage(), false);
//    }

//    @Test
//    public void giveBootsTest() {
//        GamePanel gp = new GamePanel();
//        Player player = new Player(gp, mock(KeyHandler.class));
//        Entity item = mock(Entity.class);
//        item.name = "Boots";
//        player.give(item);
//        Assertions.assertEquals(6, p.radiusToGet);
//    }

    @Test
    public void processTest1() {
        int titleState;
        GamePanel gp = new GamePanel();

        gp.startGameThread();
        titleState = gp.gameState;
        Assertions.assertEquals(gp.titleState, titleState);
    }

    @Test
    public void processTest2() {
        int titleState, playState, pauseState;
        GamePanel gp = new GamePanel();
        Component keyH = new Component() {};


        gp.startGameThread();
        titleState = gp.gameState;
        gp.ui.commandNumber = 0;
        playState = gp.gameState;
        KeyEvent key = new KeyEvent (
                keyH, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_P,'p');
        keyH.getKeyListeners()[0].keyPressed(key);
        pauseState = gp.gameState;

        Assertions.assertEquals(gp.titleState, titleState);
        Assertions.assertEquals(gp.playState, playState);
        Assertions.assertEquals(gp.pauseState, pauseState);
    }

//    @Test
//    public void processTest3() {
//        int titleState, playState, pauseState, dialogueState;
//        GamePanel gp = new GamePanel();
//        gp.startGameThread();
//        titleState = gp.gameState;
//        gp.ui.commandNumber = 1;
//        KeyEvent.getKeyText(gp.ui.commandNumber);
//        dialogueState = gp.gameState;
//        gp.ui.commandNumber = 0;
//        playState = gp.gameState;
//        pauseState = gp.gameState;
//        gp.ui.commandNumber = 1;
//
//        Assertions.assertEquals(gp.titleState, titleState);
//        Assertions.assertEquals(gp.playState, playState);
//        Assertions.assertEquals(gp.pauseState, pauseState);
//        Assertions.assertEquals(gp.dialogueState, dialogueState);
//    }

//    @Test
//    public void IntegratedDamageTest() {
//        GamePanel gp = new GamePanel();
//        Entity slime = new MON_GreenSlime(gp);
//        ArrayList<Entity> entityList = new ArrayList<>();
//        entityList.add(slime);
//        Player player = new Player(gp, mock(KeyHandler.class));
//        player.worldX = 10;
//        player.worldY = 10;
//        player.radiusAttack = 10;
//        slime.worldX = 5;
//        slime.worldY = 5;
//        slime.life = 10;
//        player.damage = 5;
//        player.attack();
//        Assertions.assertEquals(5, slime.life);
//    }
}
