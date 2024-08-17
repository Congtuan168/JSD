package tut01.environments;

public class SteelWall extends Environment{
    public SteelWall() {
        super("Steel Wall", "Stop tanks and bullets completely");

    }
    @Override
    public void interact() {
        System.out.println(this.environmentDescription);
    }
}
