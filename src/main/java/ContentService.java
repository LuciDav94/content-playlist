import model.Content;
import model.Preroll;
import model.Video;

import java.util.Map;

public class ContentService {

    private Map<String, Content> contentMap;
    private Map<String, Preroll> prerollMap;

    public void returnPlaylist(String contentIdentifier, String language) {
        Content content = contentMap.get(contentIdentifier);
        for (Preroll preroll : content.getPrerollList()) {
            Preroll preroll1 = prerollMap.get(preroll.getName());
            for (Video video : preroll1.getVideos()) {
                if (video.getAttributes().getCountries().contains(language)) return;
            }
        }
    }
}
