package gui; // This class is in the "gui" package because it handles the graphical interface

import data.DataLoader;
import models.Artist;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * ArtistsApp is a Java Swing application that displays artist data in a table.
 * This is the graphical version of our program.
 */
public class ArtistsApp extends JFrame { // JFrame is the main window

    /**
     * Constructor: Sets up the GUI and displays artist data in a table.
     * @param artists A list of Artist objects (our dataset).
     */
    public ArtistsApp(List<Artist> artists) {
        setTitle("Artists Database"); // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures app closes when window is closed
        setSize(900, 600); // Window size (900x600 pixels)

        // Column headers for our table
        String[] columnNames = {"Name", "Country", "Birth Year", "Death Year", "Artworks"};

        // Convert the list of artists into a 2D array for the JTable
        Object[][] data = new Object[artists.size()][5];

        // Loop through the list of artists and populate the table
        for (int i = 0; i < artists.size(); i++) {
            Artist artist = artists.get(i);
            data[i][0] = artist.getName();
            data[i][1] = artist.getCountry();
            data[i][2] = artist.getBirthDate();
            data[i][3] = (artist.getDeathDate() != null) ? artist.getDeathDate() : "Still Alive";
            data[i][4] = artist.getNumArtworks();
        }

        // Create a JTable (a spreadsheet-like component) using our data and column names
        JTable table = new JTable(new DefaultTableModel(data, columnNames));

        // Put the table inside a scroll pane so we can scroll if there are many artists
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the window
    }

    /**
     * Main method: Loads the data from the CSV file and starts the GUI.
     */
    public static void main(String[] args) {
        try {
            // Load the artist data from the CSV file
            List<Artist> artists = DataLoader.loadArtists("data/artists.csv");

            // Start the GUI
            SwingUtilities.invokeLater(() -> {
                ArtistsApp app = new ArtistsApp(artists);
                app.setVisible(true); // Make the window visible
            });

        } catch (IOException e) { // If the file isn't found or can't be read, print an error
            e.printStackTrace();
        }
    }
}
