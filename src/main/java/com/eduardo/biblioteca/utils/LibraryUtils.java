package com.eduardo.biblioteca.utils;

import java.util.List;
import java.util.Scanner;

import com.eduardo.biblioteca.model.Book;
import com.eduardo.biblioteca.service.Library;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LibraryUtils {

   
    public static void addBook(Scanner sc, Library library) {
        String title = ConsoleUtils.readNonEmpty(sc, "Ingresa el titulo del libro: ");
        String author = ConsoleUtils.readNonEmpty(sc, "Ingresa el autor del libro: ");
        String isbn = ConsoleUtils.readNonEmpty(sc, "Ingresa el ISBN del libro: ");

        try {
            boolean added = library.add(new Book(title, author, isbn));
            if (added) {
                System.out.println("✅ Libro Agregado Correctamente!");
                log.info("Libro agregado: isbn={}", isbn);
            } else {
                System.out.println("⚠️ Ya existe un libro con ese ISBN.");
                log.warn("ISBN duplicado: {}", isbn);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("⚠️ " + ex.getMessage());
            log.warn("Validación fallida al agregar libro: {}", ex.getMessage());
        }
    }

    public static void listBooks(Library library) {
        List<Book> books = library.getAll();
        if (books.isEmpty()) {
            System.out.println("Libros no disponibles.");
            return;
        }
        books.forEach(b -> System.out.println("• " + b));
    }

    public static void findByTitle(Scanner sc, Library library) {
        String title = ConsoleUtils.readNonEmpty(sc, "Ingresa el titulo del libro a buscar: ");
        library.findByTitle(title)
                .ifPresentOrElse(
                        b -> System.out.println("Encontrado: " + b),
                        () -> System.out.println("Libro no encontrado.")
                );
    }

    public static void removeByIsbn(Scanner sc, Library library) {
        String isbn = ConsoleUtils.readNonEmpty(sc, "Ingresa el ISBN del libro a eliminar: ");
        boolean removed = library.removeByIsbn(isbn);
        System.out.println(removed ? "✅ libro eliminado." : "⚠️ No se encontró ese ISBN.");
    }

    public static void findByAuthor(Scanner sc, Library library) {
        String author = ConsoleUtils.readNonEmpty(sc, "Ingresa el autor: ");
        List<Book> results = library.findByAuthor(author);

        if (results.isEmpty()) {
            System.out.println("No se han encontrado libros de este autor.");
        } else {
            results.forEach(b -> System.out.println("• " + b));
        }
    } 
    
}
