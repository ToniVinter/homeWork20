import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.emptyList;

public class CountryReader {

    public List<Country> readCountries(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        List<Country> result = new ArrayList<>();
        while (scanner.hasNextLine()){
            result.add(readCountry(scanner.nextLine()));
        }
        return result;
    }

    private Country readCountry(String line) {
        String[] tokens = line.split("[|]");
        return new Country(
                tokens[0],
                tokens[1],
                Long.parseLong(tokens[2]),
                Long.parseLong(tokens[3]),
                tokens[4],
                tokens.length > 5 ? parseNeighbours(tokens[5]) : Collections.<String>emptyList()
        );
    }

    private List<String> parseNeighbours(String neighbours) {
        Scanner nScanner = new Scanner(neighbours);
        nScanner.useDelimiter("~");
        List<String> result = new ArrayList<>();
        while(nScanner.hasNextLine()){
            result.add(nScanner.next());
        }
        return result;
    }

}
