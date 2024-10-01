package tut04.powerups.classsinpection;

import tut01.powerups.Grenade;
import tut01.powerups.Helmet;
import tut01.powerups.Timer;
import tut04.powerups.PowerUp;
import tut04.powerups.Shovel;
import tut04.powerups.Star;
import tut04.powerups.Tank;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class checkMethods {
    private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("^[a-z][a-zA-Z0-9]*$");
    private static final Pattern VERB_PATTERN = Pattern.compile("^[a-z][a-zA-Z0-9]*$");

    public checkMethods() throws ClassNotFoundException {
    }

    public static boolean isPascalCase(Class<?> clazz) {
        String className = clazz.getSimpleName();
        return className.matches("^[A-Z][a-zA-Z0-9]*$");
    }

    public static boolean isCamelCase(String name) {
        return CAMEL_CASE_PATTERN.matcher(name).matches();
    }

    public static boolean isDescriptiveVerb(String name) {
        return VERB_PATTERN.matcher(name).matches();
    }

    public static void inspectClass(Class<?> clazz) {
        System.out.println("Inspecting class: " + clazz.getSimpleName());

        // Check fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!isCamelCase(fieldName)) {
                System.out.println("Field " + fieldName + " does not follow camelCase naming convention.");
            }
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (!isDescriptiveVerb(methodName)) {
                System.out.println("Method " + methodName + " does not follow descriptive verb naming convention.");
            }
        }
    }


    public static void main(String[] args) {
        // Example usage
        inspectClass(Grenade.class);
        inspectClass(Helmet.class);
        inspectClass(PowerUp.class);
        inspectClass(Shovel.class);
        inspectClass(Star.class);
        inspectClass(Tank.class);
        inspectClass(Timer.class);




    }
}
