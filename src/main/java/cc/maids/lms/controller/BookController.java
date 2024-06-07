package cc.maids.lms.controller;

import cc.maids.lms.dto.BookDto;
import cc.maids.lms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return bookService.getBooks();
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getOne(@PathVariable Integer id){
        return bookService.getBook(id);
    }
    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody @Valid BookDto book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        bookService.addBook(book);
        return new ResponseEntity<>("The book has been added successfully", HttpStatus.OK);
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @RequestBody BookDto bookDto){
        return bookService.updateBook(id, bookDto);
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }

}
