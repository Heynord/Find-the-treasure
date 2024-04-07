package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 23 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 8 * gp.tileSize;

        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonsters() {
        gp.monsters[0] = new MON_GreenSlime(gp);
        gp.monsters[0].worldX = gp.tileSize * 23;
        gp.monsters[0].worldY = gp.tileSize * 36;

        gp.monsters[1] = new MON_GreenSlime(gp);
        gp.monsters[1].worldX = gp.tileSize * 23;
        gp.monsters[1].worldY = gp.tileSize * 37;

        gp.monsters[2] = new MON_GreenSlime(gp);
        gp.monsters[2].worldX = gp.tileSize * 38;
        gp.monsters[2].worldY = gp.tileSize * 8;

        gp.monsters[3] = new MON_GreenSlime(gp);
        gp.monsters[3].worldX = gp.tileSize * 38;
        gp.monsters[3].worldY = gp.tileSize * 9;

        gp.monsters[4] = new MON_GreenSlime(gp);
        gp.monsters[4].worldX = gp.tileSize * 37;
        gp.monsters[4].worldY = gp.tileSize * 40;

        gp.monsters[5] = new MON_GreenSlime(gp);
        gp.monsters[5].worldX = gp.tileSize * 37;
        gp.monsters[5].worldY = gp.tileSize * 41;
    }
}
