package contet.playlist.model;

/**
 * Playlist: A legal sequence of pre-rolls and content videos in correct order as instructions to a player.
 * The included videos for content and all defined pre-rolls will all have matching attributes (country and language)
 * to form a legal play sequence as defined when capturing the content to pre-roll relationship.
 */
public class PlayList {

    /**
     * Video name from preroll
     */
    private final String prerollVideo;
    /**
     * Video name from content
     */
    private final String contentVideo;

    public PlayList(String prerollVideo, String content) {
        this.prerollVideo = prerollVideo;
        this.contentVideo = content;
    }

    public String getPrerollVideo() {
        return prerollVideo;
    }

    public String getContentVideo() {
        return contentVideo;
    }

    @Override
    public String toString() {
        if (prerollVideo == null) {
            return "{" + contentVideo + "}";
        }
        return "{" + prerollVideo + ", " + contentVideo + "}";
    }
}
