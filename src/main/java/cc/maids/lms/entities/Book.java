package cc.maids.lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String author;
    private String year;
    private String ISBN;

    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Patron patron;
    @OneToMany(mappedBy = "book")
    private List<BorrowingRecord> borrowingRecordList;
}
