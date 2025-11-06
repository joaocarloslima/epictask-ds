package br.com.etecia.epictask.repository;

import br.com.etecia.epictask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
