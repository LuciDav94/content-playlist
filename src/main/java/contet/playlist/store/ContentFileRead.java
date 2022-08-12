package contet.playlist.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import contet.playlist.model.Content;
import contet.playlist.model.Preroll;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentFileRead {

    public static final String JSON_FILE = "content.json";

    public void readJsonFile() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_FILE)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonFileModel jsonFileModel = mapper.readValue(in, JsonFileModel.class);
            Map<String, Preroll> preRollMap = new HashMap<>();
            for (Preroll preroll : jsonFileModel.getPreroll()) {
                preRollMap.put(preroll.getName(), preroll);
            }
            Map<String, Content> contentMap = new HashMap<>();
            for (Content content : jsonFileModel.getContent()) {
                List<Preroll> preRollList = content.getPreroll();
                for (Preroll preroll : preRollList) {
                    preroll.setVideos(preRollMap.get(preroll.getName()).getVideos());
                }
                content.setPreroll(preRollList);
                contentMap.put(content.getName(), content);
            }
            ContentStore.getInstance().setContentMap(contentMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
