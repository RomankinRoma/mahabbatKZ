package kz.reself.dbstruct.model;

import kz.reself.dbstruct.model.enam.ApprovementStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long userSenderId;

    private Long UserReceiverId;

    @Enumerated(EnumType.STRING)
    private ApprovementStatus isApproved;

}
