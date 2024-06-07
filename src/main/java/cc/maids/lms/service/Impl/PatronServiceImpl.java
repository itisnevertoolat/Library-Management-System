package cc.maids.lms.service.Impl;

import cc.maids.lms.dto.PatronDto;
import cc.maids.lms.entities.Book;
import cc.maids.lms.entities.BorrowingRecord;
import cc.maids.lms.entities.Patron;
import cc.maids.lms.exceptions.ResourcesNotFoundException;
import cc.maids.lms.repository.BookRepository;
import cc.maids.lms.repository.BorrowingRecordRepository;
import cc.maids.lms.repository.PatronRepository;
import cc.maids.lms.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;
    ModelMapper modelMapper = new ModelMapper();



    @Override
    @Cacheable("myCache")
    public ResponseEntity<List<PatronDto>> getPatrons() {

        List<PatronDto> patrons = patronRepository.findAll().stream().map(patron -> modelMapper.map(patron, PatronDto.class)).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patrons);
    }

    @Override
    @Cacheable("myCache")
    public ResponseEntity<PatronDto> getPatron(Integer id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("We can't get a patron with this provided id:  "+id));
        PatronDto patronDto = modelMapper.map(patron, PatronDto.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patronDto);
    }

    @Override
    public void addPatron(PatronDto patron) {
        Patron convertedPatron = modelMapper.map(patron, Patron.class);
        patronRepository.save(convertedPatron);

    }

    @Override
    public ResponseEntity<PatronDto> updatePatron(Integer id, PatronDto patronDto) {
        patronRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("We can't get a patron with this provided id:  "+id));
        Patron updatedPatron = modelMapper.map(patronDto, Patron.class);
        updatedPatron.setId(id);
        patronRepository.save(updatedPatron);
        return new ResponseEntity<>(patronDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deletePatron(Integer id) {
        patronRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("We can't get a patron with this provided id:  "+id));
        patronRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("The patron has been deleted Successfully");
    }

    @Override
    @Transactional
    public ResponseEntity<String> borrowBook(Integer bookId, Integer patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourcesNotFoundException("We can't get a book with this provided id:  "+bookId));
        PatronDto patronDto = getPatron(patronId).getBody();
        Patron patron = modelMapper.map(patronDto, Patron.class);
        book.setId(bookId);
        book.setPatron(patron);
        bookRepository.save(book);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(new Date());
        borrowingRecordRepository.save(borrowingRecord);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patron.getName()+"has borrowed " + book.getTitle()+" book");
    }

    @Override
    @Transactional
    public ResponseEntity<String> returnBook(Integer bookId, Integer patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourcesNotFoundException("We can't get a book with this provided id:  "+bookId));
        PatronDto patronDto = getPatron(patronId).getBody();
        Patron patron = modelMapper.map(patronDto, Patron.class);
        book.setId(bookId);
        book.setPatron(patron);
        bookRepository.save(book);
        BorrowingRecord borrowingRecord =  borrowingRecordRepository.getBorrowingBook(book, patron);
        borrowingRecord.setReturnDate(new Date());
        borrowingRecordRepository.save(borrowingRecord);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patron.getName()+" has returned " + book.getTitle()+" book");
    }
}
