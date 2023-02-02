import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    // nie zmieniaj nic w main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.run(scanner);
    }

    private void run(Scanner scanner) {
        try {
            Map<String, Country> countryMap = readCountriesFileToMap("countries.csv");
            String country = getCountryFromUser(scanner);
            printCountryFromUser(countryMap, country);
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku \"countries.csv\"");
        }
    }

    private String getCountryFromUser(Scanner scanner) {
        System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
        return scanner.nextLine();
    }

    private Map<String, Country> readCountriesFileToMap(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        Map<String, Country> countryMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            String code = split[0];
            String countryName = split[1];
            long population = Long.parseLong(split[2]);
            Country country = new Country(code, countryName, population);
            countryMap.put(code, country);
        }
        return countryMap;
    }

    private void printCountryFromUser(Map<String, Country> countryMap, String countryCode) {
        Country countries = countryMap.get(countryCode);
        if (countries == null) {
            System.out.println("Kod kraju " + countryCode + " nie został odnaleziony");
        } else {
            System.out.println(countries);
        }
    }

}
