package tut01.powerups;

import tut01.Directions;
import tut01.Point2D;

public abstract class PowerUp {
    private String effectPowerUp;
    private String namePowerUp;
    private Point2D position;


    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }




    public String getEffectPowerUp() {
        return effectPowerUp;
    }

    public void setEffectPowerUp(String effectPowerUp) {
        this.effectPowerUp = effectPowerUp;
    }

    public String getNamePowerUp() {
        return namePowerUp;
    }

    public void setNamePowerUp(String namePowerUp) {
        this.namePowerUp = namePowerUp;
    }

    public PowerUp (String namePowerUp, String effectPowerUp, Point2D position) {
        this.effectPowerUp = effectPowerUp;
        this.namePowerUp = namePowerUp;
        this.position = position;

    }
    public void activatePowerUp() {

    }

}
