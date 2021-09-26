package kz.reself.crmservice.repository;

import kz.reself.dbstruct.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
