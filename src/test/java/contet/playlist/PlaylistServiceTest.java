package contet.playlist;

import contet.playlist.model.PlayList;
import contet.playlist.read.PlaylistException;
import contet.playlist.read.PlaylistService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistServiceTest {

    PlaylistService playlistService = new PlaylistService();

    @Test
    public void testWrongParameters() {
        testWrongParameters(null, null);
        testWrongParameters("not null", null);
        testWrongParameters(null, "not null");
        testWrongParameters("", "");
        testWrongParameters(" ", " ");
        testWrongParameters("", null);
        testWrongParameters(null, "");
        testWrongParameters(" ", null);
        testWrongParameters(null, " ");
        testWrongParameters("", " ");
        testWrongParameters(" ", "");
    }

    private void testWrongParameters(String param1, String param2) {
        PlaylistException exception = assertThrows(PlaylistException.class, () -> {
            playlistService.computePlaylist(param1, param2);
        });
        assertEquals(exception.getMessage(), "(Wrong parameters)");
    }

    @Test
    public void testNoContent() throws PlaylistException {
        PlaylistException exception = assertThrows(PlaylistException.class, () -> {
            playlistService.computePlaylist("value", "value");
        });
        assertEquals(exception.getMessage(), "(No content found)");

        exception = assertThrows(PlaylistException.class, () -> {
            playlistService.computePlaylist("value", "value");
        });
        assertEquals(exception.getMessage(), "(No content found)");
    }

    @Test
    public void testPlaylistWithoutPreroll() {
        assertDoesNotThrow(() -> {
                    List<PlayList> playListList = playlistService.computePlaylist("MI4", "UK");
                    assertEquals(playListList.size(), 1);
                    assertNull(playListList.get(0).getPrerollVideo());
                    assertEquals(playListList.get(0).getContentVideo(), "V1");
                }
        );

        PlaylistException exception = assertThrows(PlaylistException.class, () -> {
            playlistService.computePlaylist("MI4", "DD");
        });
        assertEquals(exception.getMessage(), "(No legal playlist possible because the Content Video isn't compatible with country DD)");
    }

    @Test
    public void testPlaylist() {
        assertDoesNotThrow(() -> {
                    List<PlayList> playListList = playlistService.computePlaylist("MI3", "US");
                    assertEquals(playListList.size(), 2);
                    assertEquals(playListList.get(0).getPrerollVideo(), "V4");
                    assertEquals(playListList.get(1).getPrerollVideo(), "V6");
                    assertEquals(playListList.get(0).getContentVideo(), "V1");
                    assertEquals(playListList.get(1).getContentVideo(), "V3");
                }
        );

        PlaylistException exception = assertThrows(PlaylistException.class, () -> {
            playlistService.computePlaylist("MI3", "UK");
        });
        assertEquals(exception.getMessage(), "(No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the UK)");
    }
}
