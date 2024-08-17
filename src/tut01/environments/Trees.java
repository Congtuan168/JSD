package tut01.environments;

import tut01.Point2D;

public class Trees extends Environment{
    public Trees() {
        super("Trees", "Obfuscates tanks and bullets under it when moving through", 10, new Point2D(6, 15));

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
