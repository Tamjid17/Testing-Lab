package JUnitTesting;

import org.junit.jupiter.api.Test;

import static JUnitTesting.LibraryUtils.countAvailableBooks;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book1 = new Book("ABC", "XYZ");
    Library lib = new Library();

    Member m = new Member("Bob");

    @Test
    void checkIfBorrowed() {
        book1.borrow();
        assertTrue(book1.isBorrowed());
    }

    @Test
    void checkIfAlreadyBorrowed() {
        assertFalse(book1.isBorrowed());
        throw new IllegalStateException();
    }

    @Test
    void ifBorrowedBookReturned() {
        book1.borrow();
        book1.returnBook();
        assertFalse(book1.isBorrowed());
    }

    @Test
    void bookWasNotBorrowed() {
        book1.returnBook();
        assertFalse(book1.isBorrowed());
    }

    @Test
    void memberLimitCheck() {
        m.borrowBook();
        m.borrowBook();
        m.borrowBook();
        assertEquals(3, m.getBorrowedBooks());
    }

    @Test
    void memberLimitExceedCheck() {
        m.borrowBook();
        m.borrowBook();
        m.borrowBook();
        m.borrowBook();
        assertEquals(4, m.getBorrowedBooks());
    }

    @Test
    void checkIfBookReturned() {
        m.borrowBook();
        assertEquals(1, m.getBorrowedBooks());
        m.returnBook();
        assertEquals(0, m.getBorrowedBooks());
    }

    @Test
    void checkIfBookReturnedWithoutBorrow() {
        m.returnBook();
        assertEquals(0, m.getBorrowedBooks());
    }

    @Test
    void checkIfBookExists() {
        lib.addBook(book1);
        assertEquals(book1, lib.findBook("ABC"));
    }

    @Test
    void checkBookAvailability() {
        assertTrue(lib.isBookAvailable("1984"));
    }

    @Test
    void countNumOfAvailableBooks() {
        assertEquals(0, countAvailableBooks(lib));
    }

}