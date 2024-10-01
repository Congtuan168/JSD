package tut04.environments;

import tut01.Point2D;

public class Ice extends Environment {
    public Ice() {
        super("Ice", "Tanks can move on it. When you try to stop moving, your tank will slide forward a bit before stopping", 0, new Point2D(20, 6));
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
