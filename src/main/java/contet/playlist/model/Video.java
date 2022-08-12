package contet.playlist.model;

import java.util.List;

public class Video {

    private String name;
    private Attributes attributes;

    public String getName() {
        return name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public static class Attributes {
        private List<String> countries;
        private String language;

        public List<String> getCountries() {
            return countries;
        }

        public String getLanguage() {
            return language;
        }
    }
}
