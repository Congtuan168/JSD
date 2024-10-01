package tut04.powerups;

import tut01.Point2D;

public class Timer extends PowerUp {
    public Timer() {
        super("Timer", "The timer power-up temporarily freezes time, stopping all enemy tanks movement", new Point2D(7, 10));
    }
    @Override
    public void activatePowerUp() {
        System.out.println("Timer activated: "  + getEffectPowerUp() + " at position: " + getPosition());
        System.out.println("+500 points");
    }
}
