package panels;

import models.Artist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private List<Artist> artists; // stores current list of artists in table

    public TablePanel(List<Artist> artists) {
        this.artists = artists;
        setLayout(new BorderLayout());

        // Column headers for the table
        String[] columnNames = {"Name", "Country", "Birth Year", "Death Year", "Artworks", "Gender"};

        // Table model supports dynamic updates
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        updateTable(artists); // Load initial data
    }

    /**
     * Updates the table with a new list of artists.
     * Called when filters are applied or reset.
     */
    public void updateTable(List<Artist> artists) {
        this.artists = artists;
        model.setRowCount(0); // Clear old rows

        for (Artist a : artists) {
            model.addRow(new Object[]{
                    a.getName(),
                    a.getCountry(),
                    a.getBirthDate(),
                    a.getDeathDate() != null ? a.getDeathDate() : "Still Alive",
                    a.getNumArtworks(),
                    a.getGender()
            });
        }
    }

    /**
     * Allows other panels to listen when a row is selected.
     * Example use: update the detail panel with selected artist's info.
     */
    public void addArtistSelectionListener(Consumer<Artist> listener) {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < artists.size()) {
                    Artist selectedArtist = artists.get(selectedRow);
                    listener.accept(selectedArtist);
                }
            }
        });
    }

    // Useful if another panel needs direct access to the table
    public JTable getTable() {
        return table;
    }
}
