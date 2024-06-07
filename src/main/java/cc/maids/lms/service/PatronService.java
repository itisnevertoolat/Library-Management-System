package cc.maids.lms.service;

import cc.maids.lms.dto.BookDto;
import cc.maids.lms.dto.PatronDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatronService {

    public ResponseEntity<List<PatronDto>> getPatrons();
    public ResponseEntity<PatronDto> getPatron(Integer id);
    public void addPatron(PatronDto patron);
    public ResponseEntity<PatronDto> updatePatron(Integer id, PatronDto patronDto);
    public ResponseEntity<String> deletePatron(Integer id);
    public ResponseEntity<String> borrowBook(Integer bookId, Integer patronId);
    public ResponseEntity<String> returnBook(Integer bookId, Integer patronId);

}
