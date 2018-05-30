package ch.supsi.dti.webapp.blogger.data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Embedded
    private Role role;
    private String username;
    private String name;
    private String surname;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<BlogPost> blogPosts;

    public User(){}

    public User(String name, String surname, String username, String password){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = new Role("ROLE_USER");
    }

    public User(String username, String password, Role role) {
        this.password = new BCryptPasswordEncoder().encode(password);
        this.username = username;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return username;
    }
}
