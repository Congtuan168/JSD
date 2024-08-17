package tut01;

import tut01.environments.BrickWall;
import tut01.powerups.Grenade;
import tut01.powerups.Timer;
import tut01.tanks.EnemyTanks;
import tut01.tanks.PlayerTanks;

public class Main {
    public static void main(String[] args) throws Exception {
        PlayerTanks yellowTanks = new PlayerTanks("ABC1", 100, 10, 1, 1, "Can easily dead",new Point2D(2,3) , Directions.UP );
        yellowTanks.ToString();

        EnemyTanks basicTanks = new EnemyTanks("Basic Tank", 200, 1, 3, 2, "Generally more dangerous to the headquarters than a player, should be dispatched quickly", new Point2D(10,5) , Directions.DOWN);
        basicTanks.ToString();

        Timer timer = new Timer();
        timer.activatePowerUp();

        Grenade grenade = new Grenade();
        grenade.activatePowerUp();

        BrickWall brickWall = new BrickWall();
        brickWall.interact();
    }
}