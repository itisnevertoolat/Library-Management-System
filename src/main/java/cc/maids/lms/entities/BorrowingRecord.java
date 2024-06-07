package cc.maids.lms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Date borrowDate;
    private Date returnDate;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Patron patron;
}
