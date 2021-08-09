package kg.baiysh.personneltesting.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import kg.baiysh.personneltesting.dto.utils.DTOEntity;
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

    private String positionId;

    @Enumerated(value = EnumType.STRING)
    private ESafetyGroup electricalSafetyGroup;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

}
