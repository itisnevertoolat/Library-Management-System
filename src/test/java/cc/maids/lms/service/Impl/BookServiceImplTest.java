package cc.maids.lms.service.Impl;

import cc.maids.lms.dto.BookDto;
import cc.maids.lms.entities.Book;
import cc.maids.lms.entities.Patron;
import cc.maids.lms.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    //declare the dependencies
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void returnBooks(){
        // Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(
                1,
                "Clean Code",
                "Robert Martin",
                "2011",
                "124-467-133",
                new Patron(),
                new ArrayList<>()));
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(
                "Clean Code",
                "Robert Martin",
                "2011",
                "124-467-133"));

        //Mock the calls
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        // When
        List<BookDto> responseDto = bookService.getBooks().getBody();

        // Then

        assertEquals(responseDto.size(), bookDtos.size());
        assertEquals(responseDto.get(0).getTitle(), bookDtos.get(0).getTitle());
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();

    }
    @Test
    public void returnBook() {
        //Given
        Integer bookId = 1;
        Book book = new Book(
                1,
                "Clean Code",
                "Robert Martin",
                "2011",
                "124-467-133",
                new Patron(),
                new ArrayList<>());
        BookDto bookDto = new BookDto(
                "Clean Code",
                "Robert Martin",
                "2011",
                "124-467-133");


        // Mock the calls
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        // When
        BookDto responseDto = bookService.getBook(bookId).getBody();

        // Then
        assertEquals(bookDto.getTitle(), responseDto.getTitle());
        assertEquals(bookDto.getAuthor(), responseDto.getAuthor());
        assertEquals(bookDto.getYear(), responseDto.getYear());
        assertEquals(bookDto.getISBN(), responseDto.getISBN());
        Mockito.verify(bookRepository, Mockito.times(1)).findById(bookId);

    }


}