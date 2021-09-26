package kz.reself.dbstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class UsersDetailInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_detail_id")
    UsersDetail usersDetail;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    Interest interest;
}
