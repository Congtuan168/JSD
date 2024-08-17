package tut01.tanks;

import tut01.Directions;
import tut01.Point2D;
import tut01.powerups.Tank;

public abstract class Tanks extends TankFunction {
    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Directions getDirections() {
        return directions;
    }

    public void setDirections(Directions directions) {
        this.directions = directions;
    }

    private Point2D position;
    private Directions directions;
    private String tankName;
    private int tankPoints;
    private int tankHealth;
    private int tankMovement;
    private int tankBullet;
    private String tankDescription;

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        tankName = tankName;
    }

    public int getTankPoints() {
        return tankPoints;
    }

    public void setTankPoints(int tankPoints) {
        tankPoints = tankPoints;
    }

    public int getTankHealth() {
        return tankHealth;
    }

    public void setTankHealth(int tankHealth) {
        tankHealth = tankHealth;
    }

    public int getTankMovement() {
        return tankMovement;
    }

    public void setTankMovement(int tankMovement) {
        tankMovement = tankMovement;
    }

    public int getTankBullet() {
        return tankBullet;
    }

    public void setTankBullet(int tankBullet) {
        tankBullet = tankBullet;
    }

    public String getTankDescription() {
        return tankDescription;
    }

    public void setTankDescription(String tankDescription) {
        tankDescription = tankDescription;
    }

    private boolean validName(String tankName) {
        return tankName.length() > 0;
    }
    private boolean validTankHealth(int tankHealth) {
        return (tankHealth >= 0);
    }
    private boolean validTankPoints(int tankPoints) {
        return tankPoints >= 0;
    }
    private boolean validTankMovement(int tankMovement) {
        return (tankMovement >= 0);
    }


    //Constructor for the Tanks
    public Tanks(String tankName, int tankPoints, int tankHealth, int tankMovement, int tankBullet, String tankDescription, Point2D position, Directions directions) throws Exception {
        if (!validName(tankName)) {
            throw new Exception("Tank Name is invalid");
        }
        if (!validTankHealth(tankHealth)) {
            throw new Exception("Tank Health is invalid");
        }
        if (!validTankMovement(tankMovement)) {
            throw new Exception("Tank Movement is invalid");
        }
        if (!validTankPoints(tankPoints)) {
            throw new Exception("Tank Point is invalid");
        }

        this.tankName = tankName;
        this.tankPoints = tankPoints;
        this.tankBullet = tankBullet;
        this.tankMovement = tankMovement;
        this.tankDescription = tankDescription;
        this.tankHealth = tankHealth;
        this.directions = directions;
        this.position = position;
    }


    //ToString method to print out the Tanks information
    public void ToString() {
    }

    //action: Move()
    @Override
    public void tankMove() {
        //nhấn phím di chuyển point2d +1
    }

    @Override
    public void tankShoot(Tank tank) {
        //nếu bị bắn thì tankHeath -1
    }
}


