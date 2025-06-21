package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberTest {
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("Tamjid");
    }

    @Test
    void testBorrowedBookLimit() {
        member.borrowBook();
        assertEquals(member.getBorrowedBooks(), 1);
        member.borrowBook();
        assertEquals(member.getBorrowedBooks(), 2);
        member.borrowBook();
        assertEquals(member.getBorrowedBooks(), 3);
    }

    @Test
    void testBorrowedBooksLimitCrossing() {
        member.borrowBook();
        member.borrowBook();
        member.borrowBook();
        assertThrows(IllegalStateException.class, () -> {
            member.borrowBook();
        }, "Should throw IllegalStateException when borrowing more than 3 books");
    }

    @Test
    void testGetName() {
        member.borrowBook();
        assertEquals(member.getBorrowedBooks(), 1);
        member.returnBook();
        assertEquals(member.getBorrowedBooks(), 0);
    }

    @Test
    void testReturnBook() {
        assertThrows(IllegalStateException.class, () -> {
            member.returnBook();
        }, "Should throw IllegalStateException when returning book without borrowing");
    }
}
