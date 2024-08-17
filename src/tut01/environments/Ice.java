package tut01.environments;

public class Ice extends Environment{
    public Ice() {
        super("Tanks", "Tanks can move on it. When you try to stop moving, your tank will slide forward a bit before stopping");
    }
    @Override
    public void interact() {
        System.out.println(this.environmentDescription);
    }
}
