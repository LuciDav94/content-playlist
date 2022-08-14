package contet.playlist.read;

/**
 * Possible error messages for playlist service
 *
 * @author lucian.davidescu
 */
public class PlaylistException extends Exception {

    private static final String REPLACE_PARAMETER = "{0}";
    private static final String NO_CONTENT_FOUND = "(No content found)";
    private static final String NO_LEGAL_PLAYLIST_FOUND_PREROLL = "(No legal playlist possible because the Pre-Roll Video " +
            "isn't compatible with language of Content Video for the {0})";
    private static final String NO_COUNTRY_FOUND = "(No legal playlist possible because the Content Video " +
            "isn't compatible with country {0})";
    private static final String WRONG_PARAMETERS = "(Wrong parameters)";

    public PlaylistException(String message) {
        super(message);
    }

    public static void throwNoContentFound() throws PlaylistException {
        throw new PlaylistException(NO_CONTENT_FOUND);
    }

    public static void throwNoCountryFound(String country) throws PlaylistException {
        throw new PlaylistException(NO_COUNTRY_FOUND.replace(REPLACE_PARAMETER, country));
    }

    public static void throwNoLegalPlaylistFoundPreRoll(String country) throws PlaylistException {
        throw new PlaylistException(NO_LEGAL_PLAYLIST_FOUND_PREROLL.replace(REPLACE_PARAMETER, country));
    }

    public static void throwWrongParameters() throws PlaylistException{
        throw new PlaylistException(WRONG_PARAMETERS);
    }
}
