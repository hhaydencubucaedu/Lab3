package console; // This class is in the "console" package because it runs in the command line

import data.DataLoader;
import models.Artist;
import java.io.IOException;
import java.util.List;

/**
 * ConsoleApp is a simple program that reads artist data from the CSV file
 * and prints some basic info to the console. It's mainly for testing if our data loads correctly.
 */
public class ConsoleApp {
    public static void main(String[] args) {
        try {
            // Load the list of artists from the CSV file
            List<Artist> artists = DataLoader.loadArtists("data/artists.csv");

            // If the list isn't empty, print the first artist
            if (artists.size() > 0) {
                System.out.println("First Artist: " + artists.get(0));
            }

            // If there are at least 10 artists, print the tenth one
            if (artists.size() >= 10) {
                System.out.println("Tenth Artist: " + artists.get(9)); // Index 9 because lists are 0-based
            }

            // Print the total number of artists in the dataset
            System.out.println("Total Number of Artists: " + artists.size());

        } catch (IOException e) { // If something goes wrong with file reading, we catch the error
            System.out.println("Error loading data: " + e.getMessage()); // Print an error message
        }
    }
}
