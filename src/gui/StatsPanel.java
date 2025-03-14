package gui;

import models.Artist;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Okay so this panel is just for showing quick stats about the artists.
 * Like how many there are, how many artworks they made on average, and who was born first.
 */
public class StatsPanel extends JPanel {

    // These are the labels where we'll show the stats
    private JLabel totalArtistsLabel;
    private JLabel avgArtworksLabel;
    private JLabel earliestBirthLabel;

    // Constructor: builds the panel layout and sets up empty stat labels
    public StatsPanel() {
        // We’re stacking the labels vertically
        setLayout(new GridLayout(3, 1));

        // Puts a nice lil box with a label around this panel
        setBorder(BorderFactory.createTitledBorder("Stats"));

        // Set default text for the labels before real data loads
        totalArtistsLabel = new JLabel("Total Artists: 0");
        avgArtworksLabel = new JLabel("Average Artworks: 0");
        earliestBirthLabel = new JLabel("Earliest Birth Year: N/A");

        // Add all the labels to the panel (they'll stack vertically)
        add(totalArtistsLabel);
        add(avgArtworksLabel);
        add(earliestBirthLabel);
    }

    /**
     * This method gets called every time the data changes (like if someone filters the list).
     * It updates the stats labels with new info based on the current list of artists.
     */
    public void updateStats(List<Artist> artists) {
        // If the list is empty or null (aka no data), show default "empty" values
        if (artists == null || artists.isEmpty()) {
            totalArtistsLabel.setText("Total Artists: 0");
            avgArtworksLabel.setText("Average Artworks: 0");
            earliestBirthLabel.setText("Earliest Birth Year: N/A");
            return;
        }

        // Total number of artists in the current list
        int total = artists.size();

        // Average number of artworks per artist (we round to 2 decimal places later)
        double avg = artists.stream()
                .mapToInt(Artist::getNumArtworks)
                .average()
                .orElse(0.0); // If something goes wrong, default to 0.0

        // Finds the artist with the earliest birth year (the oldest)
        int earliest = artists.stream()
                .mapToInt(Artist::getBirthDate)
                .min()
                .orElse(0); // If no dates exist, just say 0 (lol)

        // Update the text on each label to show the new stats
        totalArtistsLabel.setText("Total Artists: " + total);
        avgArtworksLabel.setText(String.format("Average Artworks: %.2f", avg)); // nice decimal formatting
        earliestBirthLabel.setText("Earliest Birth Year: " + earliest);
    }
}
