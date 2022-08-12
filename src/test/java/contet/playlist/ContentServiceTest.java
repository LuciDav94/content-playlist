package contet.playlist;

import contet.playlist.model.ContentException;
import contet.playlist.read.ContentService;
import contet.playlist.store.ContentFileRead;
import org.junit.jupiter.api.Test;

public class ContentServiceTest {

    @Test
    public void test2() throws ContentException {
        ContentFileRead contentRead = new ContentFileRead();
        contentRead.readJsonFile();

        ContentService contentService = new ContentService();
        contentService.computePlaylist("MI3", "US");
        contentService.computePlaylist("MI4", "US");
        contentService.computePlaylist("MI3", "DD");
    }
}
