package kz.reself.business.repository;

import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByReceiverIdAndApprovementStatus(Long userId, ApprovementStatus status);
    Match findBySenderEmailAndReceiverIdAndApprovementStatus(String email, Long userId, ApprovementStatus status);
}
