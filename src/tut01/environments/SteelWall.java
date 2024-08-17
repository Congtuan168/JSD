package tut01.environments;

import tut01.Point2D;

public class SteelWall extends Environment{
    public SteelWall() {
        super("Steel Wall", "Stop tanks and bullets completely", 1, new Point2D(10, 7));
    }
    @Override
    public void interact() {
        System.out.println("The environment is being shoot!!!");
        System.out.println("The health is " + (getEnvironmentHealth()) + " left");
        if (isDestroyed(getEnvironmentHealth())) {

            System.out.println("This environment is destroyed!!!");
        }
    }
}
