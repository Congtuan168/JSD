package tut04.tanks;

import tut01.powerups.Tank;

public abstract class TankFunction {
    public abstract void tankMove();
    public abstract void tankShoot(Tank tank);
    public void attack() {
        System.out.println("Aggressively attacking!");
    }
    public void defend() {
        System.out.println("Defending position!");
    }
}
