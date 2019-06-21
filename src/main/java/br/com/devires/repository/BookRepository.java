package br.com.devires.repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import br.com.devires.entity.Book;

@Repository
public interface BookRepository extends DatastoreRepository<Book, Long> {

}