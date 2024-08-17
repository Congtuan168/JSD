package tut01.powerups;

import tut01.Point2D;

public class Tank extends PowerUp {

    public Tank() {
        super("Tank", "The only way to earn an extra life is to score 20000 points", new Point2D(8,3));
    }

    @Override
    public void activatePowerUp() {
        System.out.println("Tank activated: " + getEffectPowerUp() + " at position: " + getPosition());
        System.out.println("+500 points");
    }


}
