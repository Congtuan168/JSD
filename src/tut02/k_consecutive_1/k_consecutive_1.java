package tut02.k_consecutive_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class k_consecutive_1 {
    private static List<String> generateBinaryStrings(int N, int K) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), N, K, 0, 0);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder sb, int N, int K, int start, int count) {
        if (sb.length() == N) {
            if (count == K) {
                result.add(sb.toString());
            }
            return;
        }

        for (int i = start; i < N; i++) {
            sb.append('1');
            backtrack(result, sb, N, K, i + 1, count + 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.append('0');
            backtrack(result, sb, N, K, i + 1, count);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    private static void writeToFile(List<String> binaryStrings, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(binaryStrings.size() + "\n");
            for (String binaryString : binaryStrings) {
                writer.write(String.join(" ", binaryString.split("")) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String strings[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        System.out.print("Enter K: ");
        int K = scanner.nextInt();
        scanner.close();

        List<String> binaryStrings = generateBinaryStrings(N, K);
        writeToFile(binaryStrings, "src/tut02/k_consecutive_1/result.out");
    }
}
