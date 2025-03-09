package data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import models.Artist;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * DataLoader reads artist data from a CSV file and turns it into usable Artist objects.
 * This version uses OpenCSV to handle commas and quotes properly.
 */
public class DataLoader {

    /**
     * Loads artists from the provided CSV file using OpenCSV.
     * @param filePath The path to the CSV file (e.g. "data/artists.csv")
     * @return A list of Artist objects
     * @throws IOException If the file can't be read or parsed
     */
    public static List<Artist> loadArtists(String filePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            List<String[]> records = csvReader.readAll();

            // Skip the header row and convert the rest to Artist objects
            return records.stream()
                    .skip(1)
                    .map(DataLoader::mapToArtist)
                    .collect(Collectors.toList());

        } catch (CsvException e) {
            throw new IOException("Error parsing CSV file", e);
        }
    }

    /**
     * Converts one row of CSV data (a String array) into an Artist object.
     * Handles missing data and converts numbers safely.
     */
    private static Artist mapToArtist(String[] data) {
        if (data.length < 6) {
            return new Artist("Unknown", "Unknown", 0, null, 0);
        }

        String name = data[0].trim();    // artist
        String country = data[1].trim(); // country
        int birthDate = data[2].trim().isEmpty() ? 0 : (int) Double.parseDouble(data[2].trim());
        Integer deathDate = data[3].trim().isEmpty() ? null : (int) Double.parseDouble(data[3].trim());
        // data[4] = gender (not used)
        int numArtworks = data[5].trim().isEmpty() ? 0 : Integer.parseInt(data[5].trim());

        return new Artist(name, country, birthDate, deathDate, numArtworks);
    }
}