package tut01.environments;

public class Trees extends Environment{
    public Trees() {
        super("Trees", "Obfuscates tanks and bullets under it when moving through");

    }
    @Override
    public void interact() {
        System.out.println(this.environmentDescription);
    }
}
