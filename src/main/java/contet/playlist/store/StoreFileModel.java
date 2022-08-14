package contet.playlist.store;

import contet.playlist.model.Content;
import contet.playlist.model.PreRoll;

import java.util.List;

/**
 * Model class for the JSON file which contains contents info
 *
 * @author lucian.davidescu
 */
public class StoreFileModel {

    private List<Content> content;
    private List<PreRoll> preroll;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public List<PreRoll> getPreroll() {
        return preroll;
    }

    public void setPreroll(List<PreRoll> preroll) {
        this.preroll = preroll;
    }
}
