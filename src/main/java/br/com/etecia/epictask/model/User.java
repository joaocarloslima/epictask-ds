package br.com.etecia.epictask.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
@Data
public class User extends DefaultOAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String avatar;


    public User(){
        super(
                List.of(new SimpleGrantedAuthority("USER")),
                Map.of("name", "anonimo", "email", "unknown", "avatar_url", "https://github.com/joaocarloslima.png"),
                "email"
                );
    }

}
