package contet.playlist.model;

import java.util.List;

/**
 * This entity represents a piece of content (an asset that has been licensed by a Media company)
 * that is either a movie or an episode of a TV show. A piece of content may have 0 or more ordered Pre-Rolls
 * associated with it.
 *
 * @author lucian.davidescu
 */
public class Content {

    /**
     * Name of the content
     */
    private String name;
    /**
     * List of prerolls
     */
    private List<PreRoll> preroll;
    /**
     * List of videos
     */
    private List<Video> videos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PreRoll> getPreroll() {
        return preroll;
    }

    public void setPreroll(List<PreRoll> preroll) {
        this.preroll = preroll;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
