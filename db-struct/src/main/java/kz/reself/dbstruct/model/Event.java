package kz.reself.dbstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private String desc;

    @Column
    private Date eventDate;

    @JsonIgnore
    @ManyToMany(targetEntity = Users.class, mappedBy = "event", cascade = CascadeType.ALL)
    private List<Users> users;

}
