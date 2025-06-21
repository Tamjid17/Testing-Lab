package com.example;

public class LibraryUtils {
    public int countAvailableBooks(Library library) {
    
            int count = 0;
    
            for (Book book : library.books) {
    
                if (!book.isBorrowed()) {
    
                    count++;
    
                }
    
            }
    
            return count;
    
        }
}
