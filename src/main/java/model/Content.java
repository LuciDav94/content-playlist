package model;

import java.util.List;

public class Content {

    private String name;
    public List<Preroll> prerollList;
    private List<Video> videos;

    public Content(String name, List<Preroll> prerollList, List<Video> videos) {
        this.name = name;
        this.prerollList = prerollList;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Preroll> getPrerollList() {
        return prerollList;
    }

    public void setPrerollList(List<Preroll> prerollList) {
        this.prerollList = prerollList;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
