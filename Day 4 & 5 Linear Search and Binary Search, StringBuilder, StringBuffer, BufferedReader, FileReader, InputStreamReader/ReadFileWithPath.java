import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFileWithPath {
    public static void main(String[] args) {
        // Print current working directory
        String cwd = System.getProperty("user.dir");
        System.out.println("Current working directory: " + cwd);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full path of the file to read: ");
        String filePath = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("File content:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        scanner.close();
    }
}













