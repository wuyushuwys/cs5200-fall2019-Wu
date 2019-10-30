package edu.northeastern.cs5200.fall2019.Models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class YouTubeWidget extends Widget {
    @Column(name = "YOUTUBEID")
    private String youTubeId;

    public YouTubeWidget() {
    }

    public YouTubeWidget(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public YouTubeWidget(String type, int width, int height, Topic topic, String youTubeId) {
        super(type, width, height, topic);
        this.youTubeId = youTubeId;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }
}
