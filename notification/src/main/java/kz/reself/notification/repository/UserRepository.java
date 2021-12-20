package kz.reself.notification.repository;

import kz.reself.dbstruct.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);

    Users findByEmail(String senderEmail);
}
