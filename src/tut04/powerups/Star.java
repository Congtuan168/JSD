package tut04.powerups;

import tut01.Point2D;

public class Star extends PowerUp {

    public Star() {
        super("Star", "Increases your offensive power by one tier", new Point2D(6, 8));
    }

    @Override
    public void activatePowerUp() {
        System.out.println("Star activated: "  + getEffectPowerUp() + " at position: " + getPosition());
        System.out.println("+500 points");
    }

}
