package contet.playlist.store;

import contet.playlist.model.Content;
import contet.playlist.model.Preroll;

import java.util.List;

public class JsonFileModel {

    private List<Content> content;
    private List<Preroll> preroll;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public List<Preroll> getPreroll() {
        return preroll;
    }

    public void setPreroll(List<Preroll> preroll) {
        this.preroll = preroll;
    }
}
