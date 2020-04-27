import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CountryService {
    private final List<Country> countries;

    public CountryService(List<Country> countries) {
       this.countries =  Optional.ofNullable(countries)
               .map(ArrayList::new)
               .orElseGet(ArrayList::new);
    }

    public List<String> getCountriesName(){
        return countries.stream()
                .map(Country::getName)
                .collect(toList());
    }

    public String getCapitalOfCountry(String country){
        return countries.stream()
                .filter(c -> c.getName().equals(country))
                .map(c -> c.getCapital())
                .collect(Collectors.joining());

    }

    public long getPopulationOfCountry(String country){
         return countries.stream()
                .filter(s -> s.getName().equals(country))
                .map(Country::getPopulation)
                .findFirst()
                 .get();
         // nu stiu cât de ok e, dar n-am găsit altă metodă să returnez direct long

    }

    public List<String> getCountriesInContient(String continent){
        return countries.stream()
                .filter(s -> s.getContinent().equals(continent))
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    public List<String> getCountryNeighbours(String country){
        return countries.stream()
                .filter(c -> c.getName().equals(country))
                .flatMap(c -> c.getNeighbour().stream())
                .collect(Collectors.toList());

    }

    public List<Country> getCountriesByContinentPopulation(String continent, long population){
        return countries.stream()
                .filter(c -> c.getContinent().equals(continent) && c.getPopulation() < population)
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesByNeighbours(String neighbour1, String neighbour2){
        return countries.stream()
                .filter(c -> c.getNeighbour().contains(neighbour1))
                .filter(Predicate.not(c -> c.getNeighbour().contains(neighbour2)))
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesInContinentSorted(String continent){
            return countries.stream()
                    .filter(c -> c.getContinent().equals(continent))
                    .sorted(Comparator.comparing(Country::getArea))
                    .collect(Collectors.toList());
    }

    public Map<String,Long> getCountryToPopulation(){
        return countries.stream()
                .collect(toMap(
                        Country::getName,
                        Country::getPopulation
                ));
    }

    public Map<String,List<String>> getContinentToCountries(){
        return countries.stream()
                .sorted(Comparator.comparing(Country::getPopulation))
                .collect(toMap(
                        Country::getContinent,
                        c -> List.of(c.getName()),
                        (c1,c2) -> Stream.concat(c1.stream(),c2.stream())
                                         .collect(Collectors.toList())));
    }
}
