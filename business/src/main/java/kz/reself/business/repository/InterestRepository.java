package kz.reself.business.repository;

import kz.reself.dbstruct.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findById(Long id);
    Interest findByName(String name);
}
