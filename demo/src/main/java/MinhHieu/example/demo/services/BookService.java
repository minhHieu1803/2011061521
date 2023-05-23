package MinhHieu.example.demo.services;
import java.util.List;
import MinhHieu.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IBookRepository;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public void addBook(Book book){
        bookRepository.save(book);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }
}
