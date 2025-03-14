package panels;

import data.DataLoader;
import models.Artist;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * This is the main GUI app that launches the Artist Dashboard.
 * It uses multiple panels to show data, stats, charts, and filters.
 */
public class ArtistsApp extends JFrame {

    public ArtistsApp(List<Artist> artists) {
        setTitle("Artists Database - Female Artists"); // Title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700); // Overall size of the app window

        // Load each section of the UI
        TablePanel tablePanel = new TablePanel(artists);          // main table
        DetailPanel detailPanel = new DetailPanel();              // bottom detail viewer
        StatsPanel statsPanel = new StatsPanel();                 // left side stats
        ChartPanelWrapper chartPanel = new ChartPanelWrapper(artists); // right side chart
        FilterPanel filterPanel = new FilterPanel(artists, tablePanel, statsPanel, chartPanel, detailPanel); // filters on top

        // Let the table communicate with the detail view (when a row is clicked)
        tablePanel.addArtistSelectionListener(detailPanel::setArtist);

        // Layout setup
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left side = filters and stats stacked vertically
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(filterPanel, BorderLayout.NORTH);
        leftPanel.add(statsPanel, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(220, getHeight())); // make it skinnier to give more space to center/chart

        // Center = table and detail panel stacked
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JScrollPane(tablePanel), BorderLayout.CENTER);
        centerPanel.add(detailPanel, BorderLayout.SOUTH);

        // Add panels to main layout
        mainPanel.add(leftPanel, BorderLayout.WEST);      // filters + stats
        mainPanel.add(centerPanel, BorderLayout.CENTER);  // table + details
        mainPanel.add(chartPanel, BorderLayout.EAST);     // chart

        // Add everything to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        try {
            // Load artist data from the CSV
            List<Artist> artists = DataLoader.loadArtists("data/artists.csv");

            // Show the app (run on UI thread)
            SwingUtilities.invokeLater(() -> {
                ArtistsApp app = new ArtistsApp(artists);
                app.setVisible(true);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
