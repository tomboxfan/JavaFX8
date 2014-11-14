package chap03;

import java.util.Arrays;
import java.util.List;

public class AggregateOperations {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(23, 84, 74, 85, 54, 60);
        System.out.println("values: " + values.toString());

        int threshold = 54;
        System.out.println("Values greater than " + threshold + " converted to hex:");

        //@formatter:off
        // using aggregate functions filter() and forEach()
        values.stream()
                .filter(val -> val > threshold) // Predicate functional interface
                .sorted()
                .map(dec -> Integer.toHexString(dec).toUpperCase()) // Consumer functional interface
                .forEach(val -> System.out.println(val)); // for each output values.
      //@formatter:on
    }
}
