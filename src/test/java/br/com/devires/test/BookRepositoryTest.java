package br.com.devires.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.devires.GcpDatastoreEmulatorApplication;
import br.com.devires.entity.Book;
import br.com.devires.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { GcpDatastoreEmulatorApplication.class })
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void saveBook() {
		Book book = new Book(1L, "Devires Book", "Gilberto Holms");
		book = repository.save(book);
		Assert.assertNotNull(book);
	}

	@Test
	public void findBook() {
		Optional<Book> book = repository.findById(1L);
		Assert.assertTrue(book.isPresent());
		log.info(book.get().toString());
	}

}