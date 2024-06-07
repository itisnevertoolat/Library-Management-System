package cc.maids.lms.dto;

import cc.maids.lms.entities.Book;
import cc.maids.lms.entities.BorrowingRecord;
import cc.maids.lms.entities.Patron;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @NotNull(message = "The Title parameter is required")
    @NotBlank(message = "please provide a title for the Book")
    private String title;
    @NotNull(message = "The author parameter is required")
    @NotBlank(message = "please provide an author for the Book")
    private String author;
    @NotNull(message = "The year parameter is required")
    @NotBlank(message = "please provide a year for the Book")
    private String year;
    @NotNull(message = "The ISBN parameter is required")
    @NotBlank(message = "please provide a ISBN for the Book")
    private String ISBN;
}
