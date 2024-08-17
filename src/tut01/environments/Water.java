package tut01.environments;

public class Water extends Environment{
    public Water() {
        super("Water", "Tanks cannot traverse through at all");

    }
    @Override
    public void interact() {
        System.out.println(this.environmentDescription);
    }
}
