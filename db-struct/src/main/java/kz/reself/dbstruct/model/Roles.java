package kz.reself.dbstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = Users.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Users> users;
}
