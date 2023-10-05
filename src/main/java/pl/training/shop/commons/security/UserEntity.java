package pl.training.shop.commons.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @GeneratedValue
    @Id
    private Long id;
    private String email;
    private String name;
    private String password;
    private boolean enabled;
    private String role;

}
