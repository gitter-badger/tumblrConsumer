package io.github.tyb.common.test.pojo.types;

//import com.tumblr.jumblr.types.Photo.PhotoType;

import java.util.List;

/**
 * This class represents a post of type "photo"
 * @author jc
 */
public class PhotoPost extends Post {

    private String caption;
    private Integer width, height;

    private String link;
    // TODO: Do not leak the photos member variable to world
    private List<Photo> photos;

    protected List<Photo> pendingPhotos;
    //protected PhotoType postType = null;

    /**
     * Get the Photo collection for this post
     * @return the photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * Return if this is a photoset or not
     * @return boolean
     */
    public boolean isPhotoset() {
        return photos != null && photos.size() > 1;
    }

    /**
     * Return the caption for this post
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Return the photo width
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Return the photo height
     * @return height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Set the caption for this post
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }


    /**
     * Set the link URL for this post
     * @param linkUrl the link URL
     */
    public void setLinkUrl(String linkUrl) {
        this.link = linkUrl;
    }

    @Override
    public PostType getType() {
        return PostType.PHOTO;
    }


}
