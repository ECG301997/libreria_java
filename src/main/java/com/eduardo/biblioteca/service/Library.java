package com.eduardo.biblioteca.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.eduardo.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@NoArgsConstructor
public class Library {

    private final List<Book> books = new ArrayList<>();

    public List<Book> getAll() {
        return Collections.unmodifiableList(books);
    }

    public boolean add(Book book) {
        validateBook(book);

        if (existsIsbn(book.getIsbn())) {
            return false; // no agrega duplicados
        }
        books.add(book);
        return true;
    }

    public Optional<Book> findByTitle(String title) {
        if (isBlank(title)) return Optional.empty();
        return books.stream()
                .filter(b -> title.equalsIgnoreCase(b.getTitle()))
                .findFirst();
    }

    public List<Book> findByAuthor(String author) {
        if (isBlank(author)) return List.of();
        return books.stream()
                .filter(b -> author.equalsIgnoreCase(b.getAuthor()))
                .collect(Collectors.toList());
    }

    public Optional<Book> findByIsbn(String isbn) {
        if (isBlank(isbn)) return Optional.empty();
        return books.stream()
                .filter(b -> isbn.equalsIgnoreCase(b.getIsbn()))
                .findFirst();
    }

    public boolean removeByIsbn(String isbn) {
        if (isBlank(isbn)) return false;
        return books.removeIf(b -> isbn.equalsIgnoreCase(b.getIsbn()));
    }

    public boolean existsIsbn(String isbn) {
        if (isBlank(isbn)) return false;
        return books.stream().anyMatch(b -> isbn.equalsIgnoreCase(b.getIsbn()));
    }

    private void validateBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Libro no puede ser null");
        if (isBlank(book.getTitle())) throw new IllegalArgumentException("Titulo requerido");
        if (isBlank(book.getAuthor())) throw new IllegalArgumentException("Autor requerido");
        if (isBlank(book.getIsbn())) throw new IllegalArgumentException("ISBN requerido");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
