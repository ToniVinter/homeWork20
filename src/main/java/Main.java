import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CountryReader countryReader = new CountryReader();
        List<Country> countryList = countryReader.readCountries("src\\main\\resources\\countries.txt");
        System.out.println(countryList);
    }
}
