import java.io.*;

public class UserInputToFile {
    public static void main(String[] args) {
        String outputFilePath = "user_input.txt"; // Output file path

        try (
            // Reading input from console
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(isr);

            // Writing to file
            FileWriter fw = new FileWriter(outputFilePath);
            BufferedWriter writer = new BufferedWriter(fw)
        ) {
            String inputLine;
            System.out.println("Enter text (type 'exit' to finish):");

            while (true) {
                inputLine = reader.readLine(); // Read from console
                if ("exit".equalsIgnoreCase(inputLine)) {
                    break; // Stop on "exit"
                }
                writer.write(inputLine);       // Write to file
                writer.newLine();              // Add a newline
            }

            System.out.println("User input has been saved to " + outputFilePath);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

















