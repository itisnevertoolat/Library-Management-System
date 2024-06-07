package cc.maids.lms.service;

import cc.maids.lms.dto.BookDto;
import cc.maids.lms.entities.Book;
import cc.maids.lms.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {

    public ResponseEntity<List<BookDto>> getBooks();
    public ResponseEntity<BookDto> getBook(Integer id);
    public void addBook(BookDto book);
    public ResponseEntity<BookDto> updateBook(Integer id, BookDto bookDto);
    public ResponseEntity<String> deleteBook(Integer id);
}
