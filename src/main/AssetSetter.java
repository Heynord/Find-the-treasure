package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        // TODO
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
    }
}
