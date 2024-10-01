package tut04.powerups;


import tut01.Point2D;
import tut04.environments.annotations.Important;

public class Helmet extends PowerUp {
    public Helmet() {
        super("Helmet", "Gives a temporary force field that shields from enemy shots", new Point2D(3, 5));
    }
    @Important
    @Override
    public void activatePowerUp() {

        System.out.println("Helmet activated: "  + getEffectPowerUp() + " at position: " + getPosition());
        System.out.println("+500 points");
    }
}

