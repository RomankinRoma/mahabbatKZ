package kz.reself.dbstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Type(type = "kz.reself.dbstruct.mapper.CustomStringArrayType")
    @Column(name = "roles", columnDefinition = "text[]")
    private String[] roles;
}
