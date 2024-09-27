package com.sivalabs.bookmarks;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    List<BookmarkDTO> findAll() {
        return bookmarkService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookmarkDTO> findBookmarkById(@PathVariable Long id) {
        var bookmark = bookmarkService.findBookmarkById(id);
        return bookmark.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Void> createBookmark(@RequestBody @Valid CreateBookmarkPayload payload) {
        CreateBookmarkCmd cmd = new CreateBookmarkCmd(payload.title(), payload.url(), 1L);
        Long id = bookmarkService.createBookmark(cmd);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(id);
        return ResponseEntity.created(uri).build();
    }

    record CreateBookmarkPayload(
            @NotEmpty String title,
            @NotEmpty String url) {}
}
