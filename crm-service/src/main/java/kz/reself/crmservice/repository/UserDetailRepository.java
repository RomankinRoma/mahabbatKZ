package kz.reself.crmservice.repository;

import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UsersDetail, Long> {

}
