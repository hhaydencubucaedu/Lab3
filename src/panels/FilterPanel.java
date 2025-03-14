package panels;

import models.Artist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class FilterPanel extends JPanel {
    private JComboBox<String> countryCombo;
    private JComboBox<String> birthYearCombo;
    private JComboBox<String> genderCombo;

    private List<Artist> originalData;
    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private ChartPanelWrapper chartPanel;
    private DetailPanel detailPanel;

    public FilterPanel(List<Artist> artists, TablePanel tablePanel, StatsPanel statsPanel, ChartPanelWrapper chartPanel, DetailPanel detailPanel) {
        this.originalData = artists;
        this.tablePanel = tablePanel;
        this.statsPanel = statsPanel;
        this.chartPanel = chartPanel;
        this.detailPanel = detailPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createTitledBorder("Filters"));

        // Combo box to pick the country
        countryCombo = new JComboBox<>();
        countryCombo.addItem("All");
        artists.stream().map(Artist::getCountry).distinct().sorted().forEach(countryCombo::addItem);
        add(new JLabel("Country:"));
        add(countryCombo);

        // Combo box to pick the birth year
        birthYearCombo = new JComboBox<>();
        birthYearCombo.addItem("All");
        artists.stream().map(a -> String.valueOf(a.getBirthDate())).distinct().sorted().forEach(birthYearCombo::addItem);
        add(new JLabel("Birth Year:"));
        add(birthYearCombo);

        // Combo box to pick gender
        genderCombo = new JComboBox<>();
        genderCombo.addItem("All");
        artists.stream().map(Artist::getGender).distinct().sorted().forEach(genderCombo::addItem);
        add(new JLabel("Gender:"));
        add(genderCombo);

        // When a filter changes, we wanna re-filter the data
        ActionListener filterListener = e -> applyFilters();
        countryCombo.addActionListener(filterListener);
        birthYearCombo.addActionListener(filterListener);
        genderCombo.addActionListener(filterListener);
    }

    // This applies the selected filters to the list of artists
    private void applyFilters() {
        String selectedCountry = (String) countryCombo.getSelectedItem();
        String selectedYear = (String) birthYearCombo.getSelectedItem();
        String selectedGender = (String) genderCombo.getSelectedItem();

        List<Artist> filtered = originalData.stream()
                .filter(a -> selectedCountry.equals("All") || a.getCountry().equals(selectedCountry))
                .filter(a -> selectedYear.equals("All") || String.valueOf(a.getBirthDate()).equals(selectedYear))
                .filter(a -> selectedGender.equals("All") || a.getGender().equals(selectedGender))
                .collect(Collectors.toList());

        // Push the new list to the table and other panels
        tablePanel.updateTable(filtered);
        statsPanel.updateStats(filtered);
        chartPanel.updateChart(filtered);
        detailPanel.clearDetails(); // clear in case someone selected an artist before filtering
    }
}
