package com.sivalabs.bookmarks;

import java.time.Instant;

/**
 * Projection for {@link Bookmark}
 */
public interface BookmarkDTO {
    Long getId();

    String getTitle();

    String getUrl();

    Instant getCreatedAt();

    UserDTO getUser();

    /**
     * Projection for {@link User}
     */
    interface UserDTO {
        Long getId();

        String getName();
    }
}