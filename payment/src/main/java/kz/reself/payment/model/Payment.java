package kz.reself.payment.model;
import kz.reself.dbstruct.model.Users;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String message;

    private Long amount;

}
