package data; // This class belongs in the "data" package

import models.Artist;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * DataLoader is responsible for reading the CSV file and converting it into Artist objects.
 * Basically, it takes raw text data and transforms it into something we can work with.
 */
public class DataLoader {

    /**
     * Reads the CSV file and converts it into a list of Artist objects.
     * @param filePath The location of the CSV file (like "data/artists.csv").
     * @return A list of Artist objects (one for each row in the CSV file).
     * @throws IOException If the file doesn't exist or something goes wrong while reading it.
     */
    public static List<Artist> loadArtists(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return reader.lines()  // Read all lines in the file
                    .skip(1) // Skip the first row (header row with column names)
                    .map(line -> line.split(",")) // Split each line into an array of values
                    .map(DataLoader::mapToArtist) // Convert that array into an Artist object
                    .collect(Collectors.toList()); // Collect everything into a List
        }
    }

    /**
     * Converts an array of strings (from one row in the CSV) into an Artist object.
     * @param data An array of values from one row in the CSV.
     * @return A new Artist object with the data filled in.
     */
    private static Artist mapToArtist(String[] data) {
        String name = data[0].trim(); // Artist name
        String country = data[1].trim(); // Country of origin
        int birthDate = Integer.parseInt(data[2].trim()); // Birth year

        // Death date is sometimes missing, so we check for that
        Integer deathDate = data[3].trim().isEmpty() ? null : Integer.parseInt(data[3].trim());

        int numArtworks = Integer.parseInt(data[5].trim()); // Number of artworks

        // Return a new Artist object with the parsed data
        return new Artist(name, country, birthDate, deathDate, numArtworks);
    }
}
