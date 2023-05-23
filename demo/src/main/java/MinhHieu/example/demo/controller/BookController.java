package MinhHieu.example.demo.controller;

import MinhHieu.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Iterator;
import MinhHieu.example.demo.entity.Book;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired List<Book> books;
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        model.addAttribute("title", "Book List");
        return "book/list";
    }


    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());

        model.addAttribute("title", "Add Book");
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
       books.add(book);
        return "redirect:/books";
    }


    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model){
        Book editbook = null;
        for (Book book : books){
            if(book.getId().equals(id)){
                editbook = book;
            }
        }

        if(editbook != null){
            model.addAttribute("book",editbook);
            return "book/edit";
        }else { return "not-found";}
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updateBook){
        for( int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getId() == updateBook.getId()){
                books.set(i,updateBook);
                break;
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getId() == id) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/books";
    }

    @Autowired
    private BookService bookService;

    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", "Book List");
        return "book/list";
    }
}
