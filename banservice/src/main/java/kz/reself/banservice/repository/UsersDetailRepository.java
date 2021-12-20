package kz.reself.banservice.repository;

import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersDetailRepository extends JpaRepository<UsersDetail, Long> {

    Optional<UsersDetail> findByUserId(Long id);

}
