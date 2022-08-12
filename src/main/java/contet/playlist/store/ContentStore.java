package contet.playlist.store;

import contet.playlist.model.Content;

import java.util.Map;

public class ContentStore {

    private static ContentStore single_instance = null;

    private ContentStore() {
    }

    public static ContentStore getInstance() {
        if (single_instance == null)
            single_instance = new ContentStore();

        return single_instance;
    }

    private Map<String, Content> contentMap;

    public void setContentMap(Map<String, Content> contentMap) {
        this.contentMap = contentMap;
    }

    public Map<String, Content> getContentMap() {
        return contentMap;
    }
}
