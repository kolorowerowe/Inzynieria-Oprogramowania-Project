package com.github.swapbook.repositories.books;

import com.github.swapbook.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository("swapbook.books")
public class BookDBRepository implements BookRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public BookDBRepository() {
    }

    @Override
    public List<Book> getBooks() {
        return entityManager.createNativeQuery("select * from swapbook.books", Book.class).getResultList();

    }

    @Override
    public Book getBookById(int id) {
        return ((Book) entityManager.createNativeQuery("select * from swapbook.books WHERE book_id=?", Book.class)
                .setParameter(1, id)
                .getSingleResult());
    }

    @Override
    public BookRepository searchBooksByRegex(String title) {
        return null;
    }

    @Override
    public BookRepository searchBooksByAuthor(String regex) {
        return null;
    }

    @Override
    public boolean setContainsName(Set<Book> set, String name) {
        return false;
    }

    @Override
    public Book getBookByTitle(String title) {
        return ((Book) entityManager.createNativeQuery("select * from swapbook.books WHERE title=?", Book.class)
                .setParameter(1, title)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void deleteBookById(int id) {
        entityManager.createNativeQuery("delete from swapbook.books WHERE  book_id=?", Book.class)
                .setParameter(1, id)
                .executeUpdate();
    }


    @Override
    @Transactional
    public void addBookToList(Book book) {
        entityManager.createNativeQuery("INSERT INTO swapbook.books VALUES (?,?,?,?)")
                .setParameter(1, book.getBook_id())
                .setParameter(2, book.getTitle())
                .setParameter(3, book.getAuthor())
                .setParameter(4, book.getPhoto_url())
                .executeUpdate();
    }

}
