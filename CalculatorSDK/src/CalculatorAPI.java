import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * A simple SDK for interacting with the Calculator API.
 */
public class CalculatorAPI {
    private String baseUrl;

    /**
     * Initializes the CalculatorAPI with the base URL.
     *
     * @param baseUrl The base URL of the Calculator API.
     */
    public CalculatorAPI(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String makeRequest(String operation, double a, double b) throws IOException {
        String endpointUrl = baseUrl + "/" + operation + "?a=" + a + "&b=" + b;
        URL url = new URL(endpointUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new IOException("Error: Unable to perform the operation. HTTP Response Code: " + responseCode);
        }

        Scanner sc = new Scanner(url.openStream());
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        sc.close();

        return result.toString();
    }

    /**
     * Adds two numbers using the Calculator API.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The sum of a and b.
     * @throws IOException If an I/O error occurs.
     */
    public double add(double a, double b) throws IOException {
        String response = makeRequest("add", a, b);
        return Double.parseDouble(response);
    }

    /**
     * Subtracts the second number from the first using the Calculator API.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The difference of a and b.
     * @throws IOException If an I/O error occurs.
     */
    public double subtract(double a, double b) throws IOException {
        String response = makeRequest("subtract", a, b);
        return Double.parseDouble(response);
    }

    public double multiply(double a, double b) throws IOException {
        String response = makeRequest("multiply", a, b);
        return Double.parseDouble(response);
    }

    public double divide(double a, double b) throws IOException {
        String response = makeRequest("divide", a, b);
        if (b == 0) {
            throw new ArithmeticException("Error: Division by zero is not allowed.");
        }
        return Double.parseDouble(response);
    }
}