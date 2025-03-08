package models; // This file belongs in the "models" package

/**
 * Artist class represents an artist from the dataset.
 * It stores basic details like name, country, birth/death dates, and number of artworks.
 */
public class Artist {
    private String name;
    private String country;
    private int birthDate;
    private Integer deathDate; // Nullable, since some artists are still alive
    private int numArtworks;

    /**
     * Constructor: Creates a new Artist object with all necessary data.
     */
    public Artist(String name, String country, int birthDate, Integer deathDate, int numArtworks) {
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.numArtworks = numArtworks;
    }

    // Getter methods to access private variables

    public String getName() { return name; }
    public String getCountry() { return country; }
    public int getBirthDate() { return birthDate; }
    public Integer getDeathDate() { return deathDate; } // Can be null
    public int getNumArtworks() { return numArtworks; }

    /**
     * Converts the Artist object into a readable string format.
     * If the artist is still alive, it shows "Present" instead of a death year.
     */
    @Override
    public String toString() {
        return name + " (" + country + ") - Born: " + birthDate +
                (deathDate != null ? ", Died: " + deathDate : ", Still Alive") +
                ", Artworks: " + numArtworks;
    }
}
