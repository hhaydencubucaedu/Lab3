package models;

/**
 * This class represents a single artist from the dataset.
 */
public class Artist {
    private String name;
    private String country;
    private int birthDate;
    private Integer deathDate;
    private int numArtworks;
    private String gender; // new gender field

    // Constructor
    public Artist(String name, String country, int birthDate, Integer deathDate, int numArtworks, String gender) {
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.numArtworks = numArtworks;
        this.gender = gender;
    }

    // Getters for all fields (aka we use these to display stuff)
    public String getName() { return name; }
    public String getCountry() { return country; }
    public int getBirthDate() { return birthDate; }
    public Integer getDeathDate() { return deathDate; }
    public int getNumArtworks() { return numArtworks; }
    public String getGender() { return gender; }

    @Override
    public String toString() {
        return name + " (" + country + ")";
    }
}