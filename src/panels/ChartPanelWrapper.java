package panels;

import models.Artist;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

/**
 * ChartPanelWrapper is the panel that shows the bar chart for top artists by number of artworks.
 */
public class ChartPanelWrapper extends JPanel {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;

    public ChartPanelWrapper(List<Artist> artists) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Chart"));
        dataset = new DefaultCategoryDataset();
        chart = createChart(artists);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(580, 400));
        add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart createChart(List<Artist> artists) {
        dataset.clear();

        artists.stream()
                .sorted(Comparator.comparingInt(Artist::getNumArtworks).reversed())
                .limit(10)
                .forEach(artist -> dataset.addValue(artist.getNumArtworks(), "Artworks", artist.getName()));

        return ChartFactory.createBarChart(
                "Top Artists by Number of Artworks",
                "Artist",
                "Artworks",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
    }

    public void updateChart(List<Artist> artists) {
        createChart(artists); // refresh chart data
        repaint();
    }
}
