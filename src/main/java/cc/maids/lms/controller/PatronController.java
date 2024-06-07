package cc.maids.lms.controller;

import cc.maids.lms.dto.BookDto;
import cc.maids.lms.dto.PatronDto;
import cc.maids.lms.service.BookService;
import cc.maids.lms.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }
    @GetMapping("/patrons")
    public ResponseEntity<List<PatronDto>> getAllBooks(){
        return patronService.getPatrons();
    }
    @GetMapping("/patrons/{id}")
    public ResponseEntity<PatronDto> getOne(@PathVariable Integer id){
        return patronService.getPatron(id);
    }
    @PostMapping("/patrons")
    public ResponseEntity<String> addBook(@RequestBody @Valid PatronDto patron, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity
                    .badRequest()
                            .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        patronService.addPatron(patron);
        return new ResponseEntity<>(patron.getName()+ " patron has been added successfully", HttpStatus.OK);
    }
    @PutMapping("/patrons/{id}")
    public ResponseEntity<PatronDto> updateBook(@PathVariable Integer id, @RequestBody PatronDto patronDto){
        return patronService.updatePatron(id, patronDto);
    }
    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        return patronService.deletePatron(id);
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId){
        return patronService.borrowBook(bookId, patronId);
    }

    @PutMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId){
        return patronService.returnBook(bookId, patronId);
    }

}
