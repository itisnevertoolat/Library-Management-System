package cc.maids.lms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "patron")
    private List<Book> bookList;
    @OneToMany(mappedBy = "patron")
    private List<BorrowingRecord> borrowingRecordList;


}
