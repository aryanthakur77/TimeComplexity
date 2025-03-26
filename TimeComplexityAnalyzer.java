

import java.io.*;

public class TimeComplexityAnalyzer {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\thaku\\Desktop\\ada_project.txt";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder code = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            code.append(line).append("\n");
        }
        reader.close();
        analyzeCode(code.toString());
    }

    private static void analyzeCode(String code) {
        String[] lines = code.split("\n");
        int loopCount = 0, logLoopCount = 0, recursionCount = 0;
        String methodName = "";

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("public") || line.startsWith("private") || line.startsWith("protected") || line.startsWith("static")) {
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].contains("(") && i > 0) {
                        methodName = words[i - 1];
                        System.out.println("Analyzing Method: " + methodName);
                        break;
                    }
                }
            }
            if (line.startsWith("for") || line.startsWith("while")) {
                loopCount++;
                if (line.contains("*=2") || line.contains("/=2")) {
                    logLoopCount++;
                }
            }
            if (!methodName.isEmpty() && line.contains(methodName + "(")) {
                recursionCount++;
            }
        }

        if (recursionCount > 0) {
            System.out.println("Time Complexity: O(" + recursionType(recursionCount) + ")");
        } else if (loopCount > 1) {
            System.out.println("Time Complexity: O(n^" + loopCount + ")");
        } else if (logLoopCount > 0) {
            System.out.println("Time Complexity: O(log n)");
        } else if (loopCount == 1) {
            System.out.println("Time Complexity: O(n)");
        } else {
            System.out.println("Time Complexity: O(1)");
        }
    }

    private static String recursionType(int count) {
        if (count == 1) return "n";
        if (count == 2) return "2^n";
        return "n^" + count;
    }
}
