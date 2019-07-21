package io.github.tyb.common.pojo.types;

import java.util.List;
import java.util.Map;

/**
 * This class represents an individual Tumbelog
 * @author jc
 */
public class Blog{

    private String name;
    private String title;
    private String description;
    private int posts, likes, followers;
    private Long updated;
    private boolean ask, ask_anon;

    /**
     * Get the description of this blog
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Can we ask questions on this blog?
     * @return boolean
     */
    public boolean canAsk() {
        return this.ask;
    }

    /**
     * Can we ask questions on this blog anonymously?
     * @return boolean
     */
    public boolean canAskAnonymously() {
        return this.ask_anon;
    }

    /**
     * Get the number of posts for this blog
     * @return int the number of posts
     */
    public Integer getPostCount() {
        return this.posts;
    }

    /**
     * Get the number of likes for this blog
     * @return int the number of likes
     */
    public Integer getLikeCount() {
        return this.likes;
    }

    /**
     * Get the time of the most recent post (in seconds since epoch)
     * @return Long of time
     */
    public Long getUpdated() {
        return updated;
    }

    /**
     * Get the title of this blog
     * @return The title of the blog
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the name of this blog
     * @return The name of the blog
     */
    public String getName() {
        return name;
    }



    public Integer getFollowersCount() {
        return this.followers;
    }



    /**
     * Set the name of this blog
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
