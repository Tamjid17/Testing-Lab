package com.example;
import java.util.ArrayList;
import java.util.List;
public class Library {
    public List<Book> books;
    
    public List<Member> members;


    public Library() {

        books = new ArrayList<>();

        members = new ArrayList<>();

    }


    public void addBook(Book book) { books.add(book); }


    public void registerMember(Member member) { members.add(member); }


    public Book findBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) return book;

        }

        return null;

    }


    public boolean isBookAvailable(String title) {

        Book book = findBook(title);

        return book != null && !book.isBorrowed();

    }
}
