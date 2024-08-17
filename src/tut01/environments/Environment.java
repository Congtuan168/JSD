package tut01.environments;

public abstract class Environment {
    public String environmentName;
    public String environmentDescription;

    public Environment(String environmentName, String environmentDescription) {
        this.environmentName = environmentName;
        this.environmentDescription = environmentDescription;
    }

    public void interact() {}
}
