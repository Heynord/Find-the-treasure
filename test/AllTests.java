import entity.Entity;
import entity.NPC_OldMan;
import entity.Player;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.mockito.Mockito.mock;

public class AllTests {

//    @Test
//    public void PlayerDamageTest() {
//        GamePanel gp = new GamePanel();
//        Player player = new Player(gp, mock(KeyHandler.class));
//        player.life = 20;
//        Entity police = new NPC_Enemy();
//        police.damage = 4;
//        player.contactPoliceman(police);
//        Assertions.assertEquals(16, player.life);
//    }
//
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
//
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
//
//    @Test
//    public void ImageTest() {
//        String res = "entities/down1.png";
//        try {
//            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + res)));
//            Assertions.assertTrue(ImageGetter.compareImages(image, ImageGetter.getImage(res)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void openDoorTest() {
//        OBJ_Door d = new OBJ_Door(mock(GamePanel.class));
//        d.image2 = mock(BufferedImage.class);
//        d.coords.add(new TileParsed.Coords(0,0));
//        d.open();
//        Assertions.assertEquals(d.getImage(), d.image2);
//    }
//
//    @Test
//    public void giveMagnetTest() {
//        GamePanel gp = new GamePanel();
//        Player player = new Player(gp, mock(KeyHandler.class));
//        player.radiusToGet = 3;
//        Entity item = mock(Entity.class);
//        item.type = "magnet";
//        player.give(item);
//        Assertions.assertEquals(6, p.radiusToGet);
//    }
//
//    @Test
//    public void processTest1() {
//        int titleState;
//        GamePanel gp = new GamePanel();
//        gp.startGameThread();
//        titleState = gp.titleState;
//        gp.ui.numberChosen = 2;
//        gp.ui.submit();
//        Assertions.assertEquals(gp.titleState, titleState);
//    }
//
//    @Test
//    public void processTest2() {
//        int titleState, playState, pauseState;
//        GamePanel gp = new GamePanel();
//        gp.startGameThread();
//        titleState = gp.gameState;
//        gp.ui.numberChosen = 0;
//        gp.ui.submit();
//        playState = gp.gameState;
//        gp.pausegame();
//        pauseState = gp.gameState;
//        gp.ui.numberChosen = 2;
//        gp.ui.submit();
//
//        Assertions.assertEquals(gp.titleState, titleState);
//        Assertions.assertEquals(gp.playState, playState);
//        Assertions.assertEquals(gp.pauseState, pauseState);
//    }
//
//    @Test
//    public void processTest3() {
//        int titleState, playState, pauseState, dialogueState;
//        GamePanel gp = new GamePanel();
//        gp.startGameThread();
//        titleState = gp.gameState;
//        gp.ui.numberChosen = 1;
//        gp.ui.submit();
//        dialogueState = gp.gameState;
//        gp.ui.numberChosen=0;
//        gp.ui.submit();
//        playState = gp.gameState;
//        gp.pausegame();
//        pauseState = gp.gameState;
//        gp.ui.numberChosen = 2;
//        gp.ui.submit();
//
//        Assertions.assertEquals(gp.titleState, titleState);
//        Assertions.assertEquals(gp.playState, playState);
//        Assertions.assertEquals(gp.pauseState, pauseState);
//        Assertions.assertEquals(gp.dialogueState, dialogueState);
//    }
//
//    @Test
//    public void IntegratedDamageTest() {
//        GamePanel gp = new GamePanel();
//        gp.em = new EntityManager(gp);
//        Entity police = new NPC_Enemy();
//        Player p = new Player(gp, mock(KeyHandler.class));
//        p.x = 10;
//        p.y= 10;
//        p.radiusAttack = 10;
//        police.y = 5;
//        police.x = 5;
//        police.health = 10;
//        p.damage = 5;
//        gp.em.addEntity(police);
//        p.attack();
//
//        Assertions.assertEquals(5, police.health);
//    }
}
