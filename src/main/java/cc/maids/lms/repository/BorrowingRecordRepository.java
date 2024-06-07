package cc.maids.lms.repository;

import cc.maids.lms.entities.Book;
import cc.maids.lms.entities.BorrowingRecord;
import cc.maids.lms.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

    @Query("from BorrowingRecord br where br.book = :book and br.patron = :patron and returnDate is null")
    public BorrowingRecord getBorrowingBook(Book book, Patron patron);
}
