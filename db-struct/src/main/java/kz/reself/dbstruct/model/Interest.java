package kz.reself.dbstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "interest")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private Long name;

    @OneToMany(mappedBy = "interest")
    Set<UsersDetailInterest> userDetailInterests;
}
