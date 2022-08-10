package model;

public class Playlist {

    private String name;
    private String country;

    public Playlist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
