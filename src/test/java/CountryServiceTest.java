import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {
    CountryService service;

    @BeforeEach
    public void seutp() {
        service = new CountryService(List.of(
                new Country("Romania", "Bucharest", 190_000_000, 250_000, "Europe", List.of("Hungary","Bulgary")),
                new Country("Hungary", "Budapest", 9_773_000, 93_000, "Europe", List.of())
        ));
    }

    @Test
    @DisplayName("the country names are displayed")
    void countryNames() {
        List<String> result = service.getCountriesName();
        assertThat(result).containsExactly("Romania", "Hungary");
    }

    @Test
    @DisplayName("the capital of a country is displayed")
    void getCapitalOfCountry(){
        var result = service.getCapitalOfCountry("Romania");
        assertThat(result).isEqualTo("Bucharest");
    }

    @Test
    @DisplayName("return population of a given country")
    void getPopulationOfCountry(){
        var result = service.getPopulationOfCountry("Romania");
        assertThat(result).isEqualTo(190000000);
    }

    @Test
    @DisplayName("Countries from a continent")
    void getCountriesInContient(){
        List<String> result = service.getCountriesInContient("Europe");
        assertThat(result).containsExactly("Romania", "Hungary");
    }

    @Test
    @DisplayName("Neighbours from a country")
    void getCountryNeighbours(){
        List<String> result = service.getCountryNeighbours("Romania");
        assertThat(result).containsExactly("Hungary", "Bulgary");
    }

    @Test
    @DisplayName("Contries By Continent and Population")
    void getCountriesByContinentPopulation(){
        List<String> result = service.getCountriesByContinentPopulation("Europe",180_000_000);
        assertThat(result).containsExactly("Romania");
    }

    @Test
    @DisplayName("get Countries By Neighbours")
    void getCountriesByNeighbours(){
        List<String> result = service.getCountriesByNeighbours("Hungary","Germany");
        assertThat(result).containsExactly("Romania");
    }

    @Test
    @DisplayName("get Countries In Continent Sorted by Area")
    void getCountriesInContinentSorted(){
        List<String> result = service.getCountriesInContinentSorted("Europe");
        assertThat(result).containsExactly("Hungary", "Romania");
    }

    @Test
    @DisplayName("get a Map from Country to Population")
    void getCountryToPopulation(){
        Map<String,Long> result = service.getCountryToPopulation();
        Map<String,Long> countries = new HashMap<>();
        countries.put("Romania",190_000_000L);
        countries.put("Hungary",9_773_000L);
        assertThat(result).isEqualTo(countries);
    }
    @Test
    @DisplayName("get Continent To Countries Map")
    void getContinentToCountries(){
        Map<String,List<String>> result = service.getContinentToCountries();
        Map<String,List<String>> countries = new HashMap<>();
        countries.put("Europe",List.of("Hungary","Romania"));
        assertThat(result).isEqualTo(countries);
    }

}