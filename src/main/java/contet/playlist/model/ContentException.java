package contet.playlist.model;

public class ContentException extends Exception {

    public static final String NO_CONTENT_FOUND = "(No content found)";
    public static final String NO_LEGAL_PLAYLIST_FOUND_PREROLL = "(No legal playlist possible because the Pre-Roll Video " +
            "isn't compatible with language of Content Video for the {0})";
    public static final String NO_COUNTRY_FOUND = "(No legal playlist possible because the Content Video " +
            "isn't compatible with country {0})";

    public ContentException(String message) {
        super(message);
    }
}
