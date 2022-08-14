package contet.playlist.model;

import java.util.List;

/**
 * This represents an asset to be played before the content plays (e.g. a video clip that shows
 * "Original Presentation"). A pre-roll may be associated with 0 or more pieces of content. When a customer
 * chooses to play a piece of content, the pre-rolls associated with the content will be played in correct
 * order followed by the content.
 *
 * @author lucian.davidescu
 */
public class PreRoll {

    /**
     * Name of the PreRoll(Identifier)
     */
    private String name;
    /**
     * List of PreRoll's videos
     */
    private List<Video> videos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
