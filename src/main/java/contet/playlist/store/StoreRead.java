package contet.playlist.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import contet.playlist.model.Content;
import contet.playlist.model.PreRoll;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to read the content file from resources to load in-memory
 *
 * @author lucian.davidescu
 */
public class StoreRead {

    public static final String JSON_FILE = "content.json";

    public static void initContentFile() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_FILE)) {
            ObjectMapper mapper = new ObjectMapper();
            StoreFileModel jsonFileModel = mapper.readValue(in, StoreFileModel.class);
            Map<String, PreRoll> preRollMap = new HashMap<>();
            for (PreRoll preroll : jsonFileModel.getPreroll()) {
                preRollMap.put(preroll.getName(), preroll);
            }
            Map<String, Content> contentMap = new HashMap<>();
            for (Content content : jsonFileModel.getContent()) {
                List<PreRoll> preRollList = content.getPreroll();
                if (preRollList != null) {
                    for (PreRoll preroll : preRollList) {
                        preroll.setVideos(preRollMap.get(preroll.getName()).getVideos());
                    }
                    content.setPreroll(preRollList);
                }
                contentMap.put(content.getName(), content);
            }
            ContentStore.getInstance().setContentMap(contentMap);
        } catch (IOException e) {
            //this case should not happen because our library will come with a populated json file from resources
            throw new RuntimeException("(Missing content json file to populate content of the in-memory store)");
        }
    }


}
