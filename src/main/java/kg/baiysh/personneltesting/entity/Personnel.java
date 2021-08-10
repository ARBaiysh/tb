package kg.baiysh.personneltesting.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import kg.baiysh.personneltesting.entity.enums.ESafetyGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Personnel{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    private String id;

    private String personnelName;

    @Column(unique = true)
    private int personnelNumber;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @OneToOne() //one-to-one
    @JoinColumn(name="position_id")
    private Position position;

    @Enumerated(value = EnumType.STRING)
    private ESafetyGroup electricalSafetyGroup;

    @OneToOne() //one-to-one
    @JoinColumn(name="user_id")
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

}
