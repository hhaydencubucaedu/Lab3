package data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import models.Artist;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * This class loads the artists from the CSV file using OpenCSV.
 * It handles quotes and commas, which is super important.
 */
public class DataLoader {

    // Loads all artist rows from CSV into Artist objects
    public static List<Artist> loadArtists(String filePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVReader csvReader = new CSVReader(reader)
        ) {
            List<String[]> records = csvReader.readAll();

            // Skip header row and map each line to an Artist
            return records.stream()
                    .skip(1)
                    .map(DataLoader::mapToArtist)
                    .collect(Collectors.toList());

        } catch (CsvException e) {
            throw new IOException("Error parsing CSV file", e);
        }
    }

    // Converts one CSV row (array of strings) into an Artist object
    private static Artist mapToArtist(String[] data) {
        if (data.length < 6) {
            return new Artist("Unknown", "Unknown", 0, null, 0, "Unknown");
        }

        String name = data[0].trim();
        String country = data[1].trim();
        int birthDate = data[2].trim().isEmpty() ? 0 : (int) Double.parseDouble(data[2].trim());
        Integer deathDate = data[3].trim().isEmpty() ? null : (int) Double.parseDouble(data[3].trim());
        String gender = data[4].trim();
        int numArtworks = data[5].trim().isEmpty() ? 0 : Integer.parseInt(data[5].trim());

        return new Artist(name, country, birthDate, deathDate, numArtworks, gender);
    }
}