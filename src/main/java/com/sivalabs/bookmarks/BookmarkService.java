package com.sivalabs.bookmarks;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
    }

    public List<BookmarkDTO> findAll() {
        return bookmarkRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<BookmarkDTO> findBookmarkById(Long id) {
        return bookmarkRepository.findBookmarkById(id);
    }

    public Long createBookmark(CreateBookmarkCmd cmd) {
        var bookmark = new Bookmark();
        bookmark.setTitle(cmd.title());
        bookmark.setUrl(cmd.url());
        bookmark.setUser(userRepository.getReferenceById(cmd.userId()));
        bookmark.setCreatedAt(Instant.now());
        bookmarkRepository.save(bookmark);
        return bookmark.getId();
    }
}
