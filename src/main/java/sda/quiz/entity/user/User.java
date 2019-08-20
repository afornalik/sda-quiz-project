package sda.quiz.entity.user;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import sda.quiz.entity.user.response.QuizTaken;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "Proszę wprowadzić poprawny Email")
    @NotEmpty(message = "Proszę wprowadzić Email")
    private String email;

    @Column(name="password")
   // @Length(min = 5,message = "Hasło musi mieć minimum 5 znaków")
    @NotEmpty(message = "Proszę wprowadzić hasło")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "Proszę wprowadzić imię")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<QuizTaken> quizTakenList;


}
