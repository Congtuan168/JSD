package tut01.environments;

import tut01.Point2D;

public class Water extends Environment{
    public Water() {
        super("Water", "Tanks cannot traverse through at all", 1000, new Point2D(8, 16));

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
