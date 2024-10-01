package tut04.powerups;

import tut01.Point2D;

public class Shovel extends PowerUp {
    public Shovel() {
        super("Shovel", "Turns the brick walls around the fortress to stone.", new Point2D(9,2));
    }

    @Override
    public void activatePowerUp() {
        System.out.println("Shovel activated: " + getEffectPowerUp() + " at position: " + getPosition());
        System.out.println("+500 points");
    }
}
