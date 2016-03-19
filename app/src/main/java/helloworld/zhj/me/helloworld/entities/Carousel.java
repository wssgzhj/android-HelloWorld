package helloworld.zhj.me.helloworld.entities;

import java.io.Serializable;

/**
 * Created by zhj-plusplus on 3/13/16.
 */
public class Carousel implements Serializable {

    private static final long serialVersionUID = 5418272380724924555L;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
