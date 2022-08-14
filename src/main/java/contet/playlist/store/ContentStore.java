package contet.playlist.store;

import contet.playlist.model.Content;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class which handles the in-memory of the content store
 *
 * @author lucian.davidescu
 */
public class ContentStore {

    private static ContentStore single_instance = null;

    private ContentStore() {
    }

    /**
     * Used to return the instance of the store of contents
     */
    public static ContentStore getInstance() {
        if (single_instance == null)
            single_instance = new ContentStore();

        return single_instance;
    }

    private Map<String, Content> contentMap = new HashMap<>();

    public void setContentMap(Map<String, Content> contentMap) {
        this.contentMap = contentMap;
    }

    public Map<String, Content> getContentMap() {
        return contentMap;
    }
}
