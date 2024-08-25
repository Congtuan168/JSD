package tut02.formatted_patterns;

public class PatternC {
    public static void main(String[] args) {

        byte number = 6;
        for(byte row = 1; row <= number; row++) {
            for (byte whitespace = 1; whitespace <= (byte) (number - row); whitespace++){
                System.out.print("  ");
            }
            for(byte num = 1; num <= row; num++) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
