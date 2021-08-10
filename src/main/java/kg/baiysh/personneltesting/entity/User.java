package kg.baiysh.personneltesting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.baiysh.personneltesting.entity.enums.ERole;
import kg.baiysh.personneltesting.entity.enums.EStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    @JsonIgnore
    private String id;

    @Column(unique = true, updatable = false)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(length = 3000)
    @JsonIgnore
    private String password;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<ERole> roles = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private EStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    @JsonIgnore
    private LocalDateTime createdDate;

    @Transient
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;


    @PrePersist
    @JsonIgnore
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public User(String id, String username, String email, String password, EStatus status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(EStatus.ACTIVE);
    }
}
