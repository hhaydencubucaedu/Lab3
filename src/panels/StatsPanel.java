package panels;

import models.Artist;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JLabel totalLabel;
    private JLabel avgLabel;
    private JLabel earliestLabel;

    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Stats"));

        // Labels are where we show info
        totalLabel = new JLabel("Total Artists: ");
        avgLabel = new JLabel("Average Artworks: ");
        earliestLabel = new JLabel("Earliest Birth Year: ");

        add(totalLabel);
        add(avgLabel);
        add(earliestLabel);
    }

    // Call this whenever filtered data changes
    public void updateStats(List<Artist> artists) {
        int total = artists.size();
        double avg = artists.stream().mapToInt(Artist::getNumArtworks).average().orElse(0.0);
        int earliest = artists.stream().mapToInt(Artist::getBirthDate).min().orElse(0);

        totalLabel.setText("Total Artists: " + total);
        avgLabel.setText("Average Artworks: " + String.format("%.2f", avg));
        earliestLabel.setText("Earliest Birth Year: " + earliest);
    }
}
