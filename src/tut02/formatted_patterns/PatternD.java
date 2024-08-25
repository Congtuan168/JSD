package tut02.formatted_patterns;

public class PatternD {
    public static void main(String[] args) {
        for(int i = 1; i <= 6; i++) {
            for (byte whiteSpace = 1; whiteSpace < (byte) (i); whiteSpace++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= 6-i+1; j++) {

                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
