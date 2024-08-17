package tut01;

import tut01.environments.BrickWall;
import tut01.environments.Ice;
import tut01.powerups.Grenade;
import tut01.powerups.Timer;
import tut01.tanks.EnemyTanks;
import tut01.tanks.PlayerTanks;

public class Main {
    public static void main(String[] args) throws Exception {
        PlayerTanks yellowTanks = new PlayerTanks("ABC1", 100, 10, 1, 1, "Can easily dead",new Point2D(2,3) , Directions.UP );
        System.out.println(yellowTanks);
        System.out.println("\n");
        System.out.println("----------------------------------------------");
        EnemyTanks basicTanks = new EnemyTanks("Basic Tank", 200, 1, 3, 2, "Generally more dangerous to the headquarters than a player, should be dispatched quickly", new Point2D(10,5) , Directions.DOWN);
        System.out.println(basicTanks);
        System.out.println("\n");
        System.out.println("----------------------------------------------");
        Timer timer = new Timer();
        timer.activatePowerUp();
        System.out.println("\n");
        System.out.println("----------------------------------------------");
        Grenade grenade = new Grenade();
        grenade.activatePowerUp();
        System.out.println("\n");
        System.out.println("----------------------------------------------");
        BrickWall brickWall = new BrickWall();
        brickWall.interact();
        System.out.println("\n");
        System.out.println("----------------------------------------------");
        Ice ice = new Ice();
        ice.interact();
    }
}