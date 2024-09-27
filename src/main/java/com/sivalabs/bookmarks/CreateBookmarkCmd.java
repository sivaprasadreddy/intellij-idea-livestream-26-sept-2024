package com.sivalabs.bookmarks;

public record CreateBookmarkCmd(String title, String url, Long userId) {
}
