package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    private Library library;
    Book book;
    LibraryUtils libraryUtils;
    @BeforeEach
    void setUp() {
        library = new Library();
    }
    @Test
    void testFindingABook() {
        book = new Book("Big brothers watching", "George Orwell");
        library.addBook(book);
        assertEquals(library.findBook("Big brothers watching").getTitle(), book.getTitle());
    }

    @Test
    void testBookAvailability() {
        book = new Book("Alchemy", "Paulo Coelho");
        library.addBook(book);
        assertTrue(library.isBookAvailable("Alchemy"));
    }

    @Test
    void testAvailableBookCount() {
        libraryUtils = new LibraryUtils();
        assertEquals(libraryUtils.countAvailableBooks(library), 0);
    }
}
