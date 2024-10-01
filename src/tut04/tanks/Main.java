package tut04.tanks;

import tut01.Directions;
import tut01.Point2D;

public class Main {
    public static void main(String[] args) throws Exception {
        Tanks<TankFunction> tanks = new Tanks<TankFunction>("Tanks", 2, 10, 1, 2, "Tanks description", new Point2D(5, 7), Directions.LEFT);
        tanks.attack();
        tanks.defend();
    }
}
