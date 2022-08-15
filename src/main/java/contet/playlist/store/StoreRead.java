package contet.playlist.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import contet.playlist.model.Content;
import contet.playlist.model.PreRoll;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Used to read the content file from resources to load in-memory
 *
 * @author lucian.davidescu
 */
public class StoreRead {

    private static final Logger LOGGER = LogManager.getLogger(StoreRead.class);

    public static final String JSON_FILE = "content.json";

    public static void initContentFile() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_FILE)) {
            ObjectMapper mapper = new ObjectMapper();
            LOGGER.debug("Load content.json in memory");
            StoreFileModel jsonFileModel = mapper.readValue(in, StoreFileModel.class);
            Map<String, PreRoll> preRollMap = new HashMap<>();

            long startTime = System.currentTimeMillis();
            for (PreRoll preroll : jsonFileModel.getPreroll()) {
                preRollMap.put(preroll.getName(), preroll);
                LOGGER.trace("Added preroll: {} to memory", preroll.getName());
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
                LOGGER.trace("Added preroll: {} to memory", content.getName());
                contentMap.put(content.getName(), content);
            }
            LOGGER.info("Load in memory took:" + (System.currentTimeMillis() - startTime) + " millis.");
            ContentStore.getInstance().setContentMap(contentMap);
        } catch (IOException e) {
            LOGGER.error("Content.json file not found:{}", e.getMessage());
            //this case should not happen because our library will come with a populated json file from resources
            throw new RuntimeException("(Missing content json file to populate content of the in-memory store)");
        }
    }


}
