package contet.playlist.read;

import contet.playlist.model.Content;
import contet.playlist.model.PlayList;
import contet.playlist.model.PreRoll;
import contet.playlist.model.Video;
import contet.playlist.store.ContentStore;
import contet.playlist.store.StoreRead;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class which handles playlist generation
 * <p>
 * Playlist: A legal sequence of pre-rolls and content videos in correct order as instructions to a player.
 * The included videos for content and all defined pre-rolls will all have matching attributes (country and language)
 * to form a legal play sequence as defined when capturing the content to pre-roll relationship.
 *
 * @author lucian.davidescu
 */
public class PlaylistService {

    static {
        StoreRead.initContentFile();
    }

    public List<PlayList> computePlaylist(String contentIdentifier, String country) throws PlaylistException {
        if (contentIdentifier == null || contentIdentifier.isEmpty() || contentIdentifier.isBlank()
                || country == null || country.isEmpty() || country.isBlank()) {
            PlaylistException.throwWrongParameters();
        }
        Content content = ContentStore.getInstance().getContentMap().get(contentIdentifier);
        if (content == null) {
            PlaylistException.throwNoContentFound();
        }
        List<PlayList> playLists = new ArrayList<>();
        if (content.getPreroll() != null && !content.getPreroll().isEmpty()) {
            for (PreRoll preRollFromContent : content.getPreroll()) {
                for (Video videoOfPreRoll : preRollFromContent.getVideos()) {
                    Video.Attributes videoOfPreRollAttributes = videoOfPreRoll.getAttributes();
                    if (videoOfPreRollAttributes.getCountries().contains(country)) {
                        String language = videoOfPreRollAttributes.getLanguage();
                        for (Video videoOfContent : content.getVideos()) {
                            Video.Attributes videoOfContentAttributes = videoOfContent.getAttributes();
                            if (videoOfContentAttributes.getCountries().contains(country)
                                    && videoOfContentAttributes.getLanguage().equals(language)) {
                                playLists.add(new PlayList(videoOfPreRoll.getName(), videoOfContent.getName()));
                            }
                        }
                    }
                }
            }
            if (playLists.isEmpty()) {
                PlaylistException.throwNoLegalPlaylistFoundPreRoll(country);
            }
        } else {
            // I assume that there can exists playlist even without a preroll because in the definition it says:
            //A piece of content may have 0 or more ordered Pre-Rolls
            for (Video videoOfContent : content.getVideos()) {
                Video.Attributes videoOfContentAttributes = videoOfContent.getAttributes();
                if (videoOfContentAttributes.getCountries().contains(country)) {
                    playLists.add(new PlayList(null, videoOfContent.getName()));
                }
            }
            if (playLists.isEmpty()) {
                PlaylistException.throwNoCountryFound(country);
            }
        }
        return playLists;
    }
}