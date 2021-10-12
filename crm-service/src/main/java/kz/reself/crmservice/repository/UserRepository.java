package kz.reself.crmservice.repository;

import kz.reself.dbstruct.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    List<Users> findAllByGender(Integer gender);
}
