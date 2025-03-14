package panels;

import models.Artist;

import javax.swing.*;
import java.awt.*;

/**
 * This panel shows information about the selected artist from the table.
 */
public class DetailPanel extends JPanel {
    private JLabel nameLabel, countryLabel, birthLabel, deathLabel, genderLabel, artworkLabel;

    public DetailPanel() {
        // Make things stack vertically
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Selected Artist Details"));

        // Create and add each label
        nameLabel = new JLabel("Name: ");
        countryLabel = new JLabel("Country: ");
        birthLabel = new JLabel("Birth Year: ");
        deathLabel = new JLabel("Death Year: ");
        genderLabel = new JLabel("Gender: ");
        artworkLabel = new JLabel("Number of Artworks: ");

        add(nameLabel);
        add(countryLabel);
        add(birthLabel);
        add(deathLabel);
        add(genderLabel);
        add(artworkLabel);
    }

    /**
     * Set this panel to show the selected artist's info.
     */
    public void setArtist(Artist artist) {
        nameLabel.setText("Name: " + artist.getName());
        countryLabel.setText("Country: " + artist.getCountry());
        birthLabel.setText("Birth Year: " + artist.getBirthDate());
        deathLabel.setText("Death Year: " +
                (artist.getDeathDate() != null ? artist.getDeathDate() : "Still Alive"));
        genderLabel.setText("Gender: " + artist.getGender());
        artworkLabel.setText("Number of Artworks: " + artist.getNumArtworks());
    }

    /**
     * Clears the panel when nothing is selected or data is reset.
     */
    public void clearDetails() {
        nameLabel.setText("Name: ");
        countryLabel.setText("Country: ");
        birthLabel.setText("Birth Year: ");
        deathLabel.setText("Death Year: ");
        genderLabel.setText("Gender: ");
        artworkLabel.setText("Number of Artworks: ");
    }
}
