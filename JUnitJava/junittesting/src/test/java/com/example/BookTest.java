package com.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {
    private Book book;
    @BeforeEach
    public void setUp() {
        book = new Book("The Hobbit", "J.R.R. Tolkien");
    }
    @Test
    void testBorrow() {
        book.borrow();
        assertTrue(book.isBorrowed(), "Book should be marked as borrowed after calling borrow()");
    }

    @Test
    void testIsBorrowed() {
        book.borrow();
        assertThrows(IllegalStateException.class, () -> {
            book.borrow();
        }, "Should throw IllegalStateException when borrowing an already borrowed book");
    }

    @Test
    void testReturnBookSuccessfully() {
        book.borrow();
        book.returnBook();
        assertFalse(book.isBorrowed(), "Book should be marked as not borrowed after being returned.");
    }
    @Test
    public void testReturnUnborrowedBookThrowsException() {
        // Arrange
        assertFalse(book.isBorrowed(), "Pre-condition failed: Book should not be borrowed.");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            book.returnBook();
        }, "Should throw IllegalStateException when returning a book that was not borrowed.");
    }
}
