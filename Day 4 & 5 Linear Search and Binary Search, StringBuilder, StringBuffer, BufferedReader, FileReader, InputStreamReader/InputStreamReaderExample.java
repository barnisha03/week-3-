import java.io.*;

public class InputStreamReaderExample {
    public static void main(String[] args) {
        String filePath = "input.txt"; // You can change this to an absolute path if needed

        try (
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader reader = new BufferedReader(isr)
        ) {
            String line;
            System.out.println("Reading content from file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding: UTF-8");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

















