package tut04.powerups;

import tut01.Point2D;

public class Grenade extends PowerUp {
    public Grenade() {
        super("Grenade", "Destroys every enemy currently on the screen", new Point2D(1,2));
    }

    @Override
    public void activatePowerUp() {
        System.out.println("Grenade activated: " + getEffectPowerUp() + " at position: " + getPosition());
        System.out.println("+500 points");
    }
}
