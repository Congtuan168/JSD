package tut04.environments.classsinpection;

import java.util.regex.Pattern;

public class TankInspector {


    //Generate pattern regrex to check
    private static final Pattern PASCAL_CASE_PATTERN = Pattern.compile("^[A-Z][a-zA-Z0-9]*$");


    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(checkClassConvention(TankInspector.class));

    }

    //Check pattern regrex = classname ? true : false
    public static boolean checkClassConvention(Class<?> clazzName) throws ClassNotFoundException {
        String className = clazzName.getSimpleName();
        return  PASCAL_CASE_PATTERN.matcher(className).matches();
    }
}
