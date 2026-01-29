package com.eduardo.biblioteca;

import java.util.Scanner;
import com.eduardo.biblioteca.service.Library;
import com.eduardo.biblioteca.utils.ConsoleUtils;
import com.eduardo.biblioteca.utils.LibraryUtils;
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
                    case 1 -> LibraryUtils.addBook(sc, library);
                    case 2 -> LibraryUtils.listBooks(library);
                    case 3 -> LibraryUtils.findByTitle(sc, library);
                    case 4 -> LibraryUtils.removeByIsbn(sc, library);
                    case 5 -> LibraryUtils.findByAuthor(sc, library);
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

}