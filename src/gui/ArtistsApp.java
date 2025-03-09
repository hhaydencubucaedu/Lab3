package gui;

import data.DataLoader;
import models.Artist;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * This class builds the GUI to show the artist data in a table.
 */
public class ArtistsApp extends JFrame {

    public ArtistsApp(List<Artist> artists) {
        // Title bar text
        setTitle("Artists Database - Female Artists");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600); // Wide enough to see everything

        // Table headers
        String[] columnNames = {"Name", "Country", "Birth Year", "Death Year", "Gender", "Artworks"};

        // Fill in the table with artist data
        Object[][] data = new Object[artists.size()][6];
        for (int i = 0; i < artists.size(); i++) {
            Artist a = artists.get(i);
            data[i][0] = a.getName();
            data[i][1] = a.getCountry();
            data[i][2] = a.getBirthDate();
            data[i][3] = (a.getDeathDate() != null) ? a.getDeathDate() : "Still Alive";
            data[i][4] = a.getGender();
            data[i][5] = a.getNumArtworks();
        }

        // Set up the table
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER); // slap it in the middle
    }

    public static void main(String[] args) {
        try {
            // Load the CSV data
            List<Artist> artists = DataLoader.loadArtists("data/artists.csv");

            // Launch the GUI
            SwingUtilities.invokeLater(() -> {
                ArtistsApp app = new ArtistsApp(artists);
                app.setVisible(true);
            });
        } catch (IOException e) {
            e.printStackTrace(); // just in case
        }
    }
}