package com.github.swapbook.repositories.specimens;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("swapbook.specimens")
public class SpecimenDBRepository implements SpecimenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    BookService bookService;

    public SpecimenDBRepository() {
    }

    @Override
    public List<Specimen> getSpecimens() {
        return entityManager.createNativeQuery("select * from swapbook.specimens", Specimen.class).getResultList();
    }

    @Override
    public Specimen getSpecimenById(int id) {
        return ((Specimen) entityManager.createNativeQuery("select * from swapbook.specimens WHERE specimen_id=?", Specimen.class)
                .setParameter(1, id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void addToList(Specimen specimen) {
        List<Specimen> list = ((List<Specimen>) entityManager.createNativeQuery("select * from swapbook.specimens WHERE title= ?",Specimen.class)
                .setParameter(1,specimen.getTitle())
                .getResultList());

        List<Book> bookList = bookService.getAllBooks();

        int max =0;
        for (Book book : bookList) {
            if (book.getBook_id() > max) {
                max = book.getBook_id();
            }
        }


        if(list.isEmpty()){
            Book book = new Book();
            book.setTitle(specimen.getTitle());
            book.setAuthor(specimen.getAuthor());
            book.setPhoto_url(specimen.getPhoto_url());
            bookService.addBook(book);
        }
        entityManager.createNativeQuery("INSERT INTO swapbook.specimens VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1, specimen.getSpecimen_id())
                .setParameter(2, (list.isEmpty()?max+1:specimen.getBook_id()))
                .setParameter(3, specimen.getUser_id())
                .setParameter(4, specimen.getTitle())
                .setParameter(5, specimen.getCondition())
                .setParameter(6, specimen.getNumber_pages())
                .setParameter(7, specimen.getAuthor())
                .setParameter(8, specimen.getRelease_date())
                .setParameter(9, specimen.getIssue_number())
                .setParameter(10, specimen.getIsbn())
                .setParameter(11, specimen.getPublishing_house())
                .setParameter(12, specimen.getLoan_period())
                .setParameter(13, specimen.getPhoto_url())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteSpecimenById(int id) {
        entityManager.createNativeQuery("delete from swapbook.specimens WHERE  specimen_id=?", Specimen.class)
                .setParameter(1, id)
                .executeUpdate();
    }

    public int getNextID() {
        int max = 0;
        List<Specimen> specimenList = getSpecimens();

        for (Specimen specimen : specimenList) {
            if (specimen.getSpecimen_id() > max) {
                max = specimen.getSpecimen_id();
            }
        }
        return max + 1;

    }
}
