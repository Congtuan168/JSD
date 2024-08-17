package tut01.environments;

import tut01.Point2D;

public abstract class Environment {
    private String environmentName;
    private String environmentDescription;

    public int getEnvironmentHealth() {
        return environmentHealth;
    }

    public void setEnvironmentHealth(int environmentHealth) {
        this.environmentHealth = environmentHealth;
    }

    private int environmentHealth;
    private Point2D position;

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getEnvironmentDescription() {
        return environmentDescription;
    }

    public void setEnvironmentDescription(String environmentDescription) {
        this.environmentDescription = environmentDescription;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }


    public Environment(String environmentName, String environmentDescription, int environmentHealth, Point2D position) {
        this.environmentName = environmentName;
        this.environmentDescription = environmentDescription;
        this.position = position;
        this.environmentHealth = environmentHealth;

    }

    public void interact() {}

    public boolean isDestroyed(int environmentHealth) {

        return (environmentHealth == 0) ;
    }
}
