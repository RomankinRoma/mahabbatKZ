package kz.reself.event.repository;

import kz.reself.dbstruct.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAllByIdIn(List<Long> ids);
}
