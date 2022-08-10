package model;

import java.util.List;

public class Video {

    private String name;
    private Attributes attributes;

    public Video(String name, Attributes attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public class Attributes {
        private List<String> countries;
        private String language;

        public Attributes(List<String> countries, String language) {
            this.countries = countries;
            this.language = language;
        }

        public List<String> getCountries() {
            return countries;
        }

        public String getLanguage() {
            return language;
        }
    }
}
