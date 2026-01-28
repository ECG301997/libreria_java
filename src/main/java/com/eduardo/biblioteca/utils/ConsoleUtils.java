package com.eduardo.biblioteca.utils;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleUtils {

    public static String readNonEmpty(Scanner sc, String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty())
                return input.trim();
            System.out.println("⚠️ Campo obligatorio. Intenta de nuevo.");
        }
    }

    public static int readInt(Scanner sc, String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (Exception e) {
                System.out.println("⚠️ Ingresa un número válido.");
            }
        }
    }
}
