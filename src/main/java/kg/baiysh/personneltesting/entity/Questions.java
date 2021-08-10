package kg.baiysh.personneltesting.entity;


import kg.baiysh.personneltesting.entity.enums.ESpecies;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Questions {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    private String id;

    private String questionText;

    private List<Query> query;

    private int level;

    @Enumerated(value = EnumType.STRING)
    private ESpecies species;

}
