package com.eduardo.biblioteca;

import java.util.List;
import java.util.Scanner;

import com.eduardo.biblioteca.model.Book;
import com.eduardo.biblioteca.service.Library;
import com.eduardo.biblioteca.utils.ConsoleUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Main {
      public static void main(String[] args) {
        Library library = new Library();

        try (Scanner sc = new Scanner(System.in)) {
            int option;
            do {
                printMenu();
                option = ConsoleUtils.readInt(sc, "Opción: ");

                switch (option) {
                    case 1 -> addBook(sc, library);
                    case 2 -> listBooks(library);
                    case 3 -> findByTitle(sc, library);
                    case 4 -> removeByIsbn(sc, library);
                    case 5 -> findByAuthor(sc, library);
                    case 6 -> System.out.println("Adios!");
                    default -> System.out.println("Opción inválida.");
                }
            } while (option != 6);
        } catch (Exception e) {
            log.error("Error inesperado", e);
            System.out.println("Ocurrió un error inesperado.");
        }
    }

    private static void printMenu() {
        System.out.println("\n==== Menú de Biblioteca ====");
        System.out.println("1. Agregar Libro");
        System.out.println("2. Listar Libros");
        System.out.println("3. Buscar Libro por Título");
        System.out.println("4. Eliminar Libro por ISBN");
        System.out.println("5. Buscar Libros por Autor");
        System.out.println("6. Exit");
    }

    private static void addBook(Scanner sc, Library library) {
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

    private static void listBooks(Library library) {
        List<Book> books = library.getAll();
        if (books.isEmpty()) {
            System.out.println("Libros no disponibles.");
            return;
        }
        books.forEach(b -> System.out.println("• " + b));
    }

    private static void findByTitle(Scanner sc, Library library) {
        String title = ConsoleUtils.readNonEmpty(sc, "Ingresa el titulo del libro a buscar: ");
        library.findByTitle(title)
                .ifPresentOrElse(
                        b -> System.out.println("Encontrado: " + b),
                        () -> System.out.println("Libro no encontrado.")
                );
    }

    private static void removeByIsbn(Scanner sc, Library library) {
        String isbn = ConsoleUtils.readNonEmpty(sc, "Ingresa el ISBN del libro a eliminar: ");
        boolean removed = library.removeByIsbn(isbn);
        System.out.println(removed ? "✅ libro eliminado." : "⚠️ No se encontró ese ISBN.");
    }

    private static void findByAuthor(Scanner sc, Library library) {
        String author = ConsoleUtils.readNonEmpty(sc, "Ingresa el autor: ");
        List<Book> results = library.findByAuthor(author);

        if (results.isEmpty()) {
            System.out.println("No se han encontrado libros de este autor.");
        } else {
            results.forEach(b -> System.out.println("• " + b));
        }
    } 
    
}