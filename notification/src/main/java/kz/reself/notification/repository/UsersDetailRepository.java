package kz.reself.notification.repository;

import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersDetailRepository extends JpaRepository<UsersDetail, Long> {

    Optional<UsersDetail> findById(Long id);

    @Query(value = "SELECT ud.* FROM users_detail ud LEFT JOIN users_interests ui " +
            "ON ud.id = ui.user_id " +
            "WHERE ui.interest_id IN :ids AND ui.user_id != :id " +
            "GROUP BY id, first_name, ud.user_id", nativeQuery = true)
    List<UsersDetail> getRecommendPeople(Long id, List<Long> ids);

    UsersDetail findByUserId(Long userId);
}
