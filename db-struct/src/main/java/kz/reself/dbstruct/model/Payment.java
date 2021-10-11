package kz.reself.dbstruct.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private Users user;

    private Long userId;

    private String message;

    private Long amount;

}
