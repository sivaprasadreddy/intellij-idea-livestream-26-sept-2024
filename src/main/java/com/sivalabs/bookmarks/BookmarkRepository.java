package com.sivalabs.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<BookmarkDTO> findAllByOrderByCreatedAtDesc();

    Optional<BookmarkDTO> findBookmarkById(Long id);
}