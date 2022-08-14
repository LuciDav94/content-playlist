package contet.playlist.model;

import java.util.List;

/**
 * This is the actual video file attached to a content or a pre-roll. Each content or pre-roll can have multiple
 * videos attached to it. Each attached video is uniquely tagged with a language, a list of countries.
 *
 * @author lucian.davidescu
 */
public class Video {

    /**
     * Name of the video
     */
    private String name;
    /**
     * Available attributes for video
     */
    private Attributes attributes;

    public String getName() {
        return name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public static class Attributes {

        /**
         * List of countries for video
         */
        private List<String> countries;
        /**
         * Language of the video
         */
        private String language;

        public List<String> getCountries() {
            return countries;
        }

        public String getLanguage() {
            return language;
        }
    }
}
