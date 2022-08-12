package contet.playlist.read;

import contet.playlist.model.Content;
import contet.playlist.model.ContentException;
import contet.playlist.model.Preroll;
import contet.playlist.model.Video;
import contet.playlist.store.ContentStore;

public class ContentService {

    public void computePlaylist(String contentIdentifier, String country) throws ContentException {
        Content content = ContentStore.getInstance().getContentMap().get(contentIdentifier);
        int playlistCounter = 0;
        if (content == null) {
            System.out.println(ContentException.NO_CONTENT_FOUND);
            return;
        }
        if (!content.getPreroll().isEmpty()) {
            for (Preroll prerollFromContent : content.getPreroll()) {
                for (Video videoOfPreroll : prerollFromContent.getVideos()) {
                    Video.Attributes videoOfPrerollAttributes = videoOfPreroll.getAttributes();
                    if (videoOfPrerollAttributes.getCountries().contains(country)) {
                        String language = videoOfPrerollAttributes.getLanguage();
                        for (Video videoOfContent : content.getVideos()) {
                            Video.Attributes videoOfContentAttributes = videoOfContent.getAttributes();
                            if (videoOfContentAttributes.getCountries().contains(country)
                                    && videoOfContentAttributes.getLanguage().equals(language)) {
                                playlistCounter++;
                                System.out.println("Playlist" + playlistCounter);
                                System.out.println("{" + videoOfPreroll.getName() + "," + videoOfContent.getName() + "}");
                            }
                        }
                    }
                }
            }
            if (playlistCounter == 0) {
                System.out.println(ContentException.NO_LEGAL_PLAYLIST_FOUND_PREROLL.replace("{0}", country));
            }
        } else {
            for (Video videoOfContent : content.getVideos()) {
                Video.Attributes videoOfContentAttributes = videoOfContent.getAttributes();
                if (videoOfContentAttributes.getCountries().contains(country)) {
                    playlistCounter++;
                    System.out.println("Playlist" + playlistCounter);
                    System.out.println("{" + videoOfContent.getName() + "}");
                }
            }
            if (playlistCounter == 0) {
                System.out.println(ContentException.NO_COUNTRY_FOUND.replace("{0}", country));
            }
        }

    }
}
