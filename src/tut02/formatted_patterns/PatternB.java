package tut02.formatted_patterns;

public class PatternB {
    public static void main(String[] args) {
        for(int i = 6; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
