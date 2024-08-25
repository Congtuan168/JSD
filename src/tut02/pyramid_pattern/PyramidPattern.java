package tut02.pyramid_pattern;

public class PyramidPattern {
    public static void main(String[] args) {
        int rows = 8;
        for (int i = 1; i <= rows; i++) {
            // Print leading spaces
            for (int j = i; j < rows; j++) {
                System.out.print("    ");
            }
            // Print ascending numbers
            for (int j = 1; j <= i; j++) {
                System.out.print((int)Math.pow(2, j - 1) + "   ");
            }
            // Print descending numbers
            for (int j = i - 1; j >= 1; j--) {
                System.out.print((int)Math.pow(2, j - 1) + "   ");
            }
            System.out.println();
        }
    }
}

