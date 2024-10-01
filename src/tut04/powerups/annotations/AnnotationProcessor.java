package tut04.powerups.annotations;

import tut04.powerups.Tank;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void main(String[] args) {
        processAnnotations(Tank.class);
    }

    public static void processAnnotations(Class<?> clazz) {
        System.out.println("Processing annotations for class: " + clazz.getName());

        // Process fields
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Important.class)) {
                System.out.println("Important field: " + field.getName());
            }
        }

        // Process methods
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Important.class)) {
                System.out.println("Important method: " + method.getName());
            }
        }
    }
}

