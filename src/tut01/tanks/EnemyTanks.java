package tut01.tanks;

import tut01.Directions;
import tut01.Point2D;

public class EnemyTanks extends Tanks{

    public EnemyTanks (String tankName, int tankPoints, int tankHealth, int tankMovement, int tankBullet, String tankDescription, Point2D position, Directions directions) throws Exception {
        super(tankName, tankPoints, tankHealth, tankMovement, tankBullet, tankDescription, position, directions);
    }

}
