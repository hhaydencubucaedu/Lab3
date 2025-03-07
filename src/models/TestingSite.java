package models; // This must be here to match the package structure!

public class TestingSite {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String website;

    /**
     * Constructor: This creates a new TestingSite object with all the necessary data.
     */
    public TestingSite(String name, String address, String city, String state, String zipCode, String phoneNumber, String website) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    // Getter methods to access private variables

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getWebsite() { return website; }

    /**
     * This method returns a string representation of a TestingSite.
     * It will be useful for printing site details in the console.
     */
    @Override
    public String toString() {
        return name + " | " + address + ", " + city + ", " + state + " " + zipCode;
    }
}
