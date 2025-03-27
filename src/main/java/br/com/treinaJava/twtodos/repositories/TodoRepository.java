package br.com.treinaJava.twtodos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaJava.twtodos.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
}
