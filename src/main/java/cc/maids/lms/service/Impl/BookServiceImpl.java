package cc.maids.lms.service.Impl;

import cc.maids.lms.dto.BookDto;
import cc.maids.lms.entities.Book;
import cc.maids.lms.exceptions.ResourcesNotFoundException;
import cc.maids.lms.repository.BookRepository;
import cc.maids.lms.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    ModelMapper modelMapper = new ModelMapper();
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    @Cacheable("myCache")
    public ResponseEntity<List<BookDto>> getBooks() {
        long startTime = System.nanoTime(); // Capture start time
        List<BookDto> books = bookRepository.findAll().stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        long endTime = System.nanoTime(); // Capture end time
        long elapsedTime = endTime - startTime;
        System.out.println("the time to finish the method " + elapsedTime / 1000000);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(books);
    }

    @Override
    @Cacheable("myCache")
    public ResponseEntity<BookDto> getBook(Integer id) {
            Book book = bookRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("We can't get a book with this provided id:  "+id));
            BookDto bookDto = modelMapper.map(book, BookDto.class);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookDto);
    }

    @Override
    public void addBook(BookDto book) {
        Book book1 = modelMapper.map(book, Book.class);
        bookRepository.save(book1);
    }
    @Override
    public ResponseEntity<BookDto> updateBook(Integer id, BookDto bookDto){
        bookRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("We can't get a book with this provided id:  "+id));
        Book updatedBook = modelMapper.map(bookDto, Book.class);
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> deleteBook(Integer id) {
        bookRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("We can't get a book with this provided id:  "+id));
        bookRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("The book has been deleted Successfully");
    }
}
