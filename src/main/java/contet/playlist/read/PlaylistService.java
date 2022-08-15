package contet.playlist.read;

import contet.playlist.model.Content;
import contet.playlist.model.PlayList;
import contet.playlist.model.PreRoll;
import contet.playlist.model.Video;
import contet.playlist.store.ContentStore;
import contet.playlist.store.StoreRead;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOGGER = LogManager.getLogger(PlaylistService.class);

    static {
        // I've let this commented but I've put the explanation into readme.md
//        CompletableFuture.runAsync(StoreRead::initContentFile);
        StoreRead.initContentFile();
    }

    /**
     * Used to return a list of {@code List<Playlist>} based on the specified content identifier and country
     *
     * @param contentIdentifier - the name of the content
     * @param country           - the country of the video
     * @return a {@code List<Playlist>} containing the match
     * @throws PlaylistException - if there is no match or parameters are wrong
     */
    public List<PlayList> computePlaylist(String contentIdentifier, String country) throws PlaylistException {
        LOGGER.debug("Return playlist based on following parameters:identifier:{} and country:{}",
                contentIdentifier, country);
        if (contentIdentifier == null || contentIdentifier.isEmpty() || contentIdentifier.isBlank()
                || country == null || country.isEmpty() || country.isBlank()) {
            LOGGER.warn("Wrong parameters");
            PlaylistException.throwWrongParameters();
        }
        Content content = ContentStore.getInstance().getContentMap().get(contentIdentifier);
        if (content == null) {
            LOGGER.warn("No content found");
            PlaylistException.throwNoContentFound();
        }
        List<PlayList> playLists = new ArrayList<>();
        List<PreRoll> preRollList = content.getPreroll();
        if (preRollList != null && !preRollList.isEmpty()) {
            LOGGER.debug("Content has {} prerolls", preRollList.size());
            for (PreRoll preRollFromContent : preRollList) {
                for (Video videoOfPreRoll : preRollFromContent.getVideos()) {
                    Video.Attributes videoOfPreRollAttributes = videoOfPreRoll.getAttributes();
                    if (videoOfPreRollAttributes.getCountries().contains(country)) {
                        String language = videoOfPreRollAttributes.getLanguage();
                        for (Video videoOfContent : content.getVideos()) {
                            Video.Attributes videoOfContentAttributes = videoOfContent.getAttributes();
                            if (videoOfContentAttributes.getCountries().contains(country)
                                    && videoOfContentAttributes.getLanguage().equals(language)) {
                                PlayList playList = new PlayList(videoOfPreRoll.getName(), videoOfContent.getName());
                                playLists.add(playList);
                                LOGGER.info("Found playlist:{}", playList.toString());
                            }
                        }
                    }
                }
            }
            if (playLists.isEmpty()) {
                LOGGER.warn("No legal playlist found for preroll");
                PlaylistException.throwNoLegalPlaylistFoundPreRoll(country);
            }
        } else {
            // I assume that there can exists playlist even without a preroll because in the definition it says:
            //A piece of content may have 0 or more ordered Pre-Rolls
            for (Video videoOfContent : content.getVideos()) {
                Video.Attributes videoOfContentAttributes = videoOfContent.getAttributes();
                if (videoOfContentAttributes.getCountries().contains(country)) {
                    PlayList playList = new PlayList(null, videoOfContent.getName());
                    playLists.add(playList);
                    LOGGER.info("Found playlist:{}", playList.toString());
                }
            }
            if (playLists.isEmpty()) {
                LOGGER.warn("No country found in the content");
                PlaylistException.throwNoCountryFound(country);
            }
        }
        LOGGER.info("Return {} playlists", playLists.size());
        return playLists;
    }
}
