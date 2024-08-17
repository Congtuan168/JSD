package tut01.environments;

import tut01.Point2D;

public class BrickWall extends Environment {
    public BrickWall() {
        super("Brick Wall", "Tanks and bullets cannot pass through this", 4, new Point2D(5, 2));

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
