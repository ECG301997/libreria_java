package com.eduardo.biblioteca.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "isbn")
public class Book {
    private String title;
    private String author;
    private String isbn;

}
