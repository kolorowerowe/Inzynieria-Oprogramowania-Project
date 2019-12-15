package com.github.swapbook;

import com.github.swapbook.model.Book;
import com.github.swapbook.repositories.book.BookRepository;
import com.github.swapbook.repositories.book.FakeBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SwapBookApplication {

	private static final Logger log = LoggerFactory.getLogger(SwapBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SwapBookApplication.class, args);

		log.info("SwapBookApplication has just started");

//		String asd="0123456789";
//		StringBuilder b = new StringBuilder(asd);
//
//		CharSequence x  = b.subSequence(3,7);
//		System.out.println(x);


	}


}
