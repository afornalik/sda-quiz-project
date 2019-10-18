package sda.quiz.entity.user;


import lombok.Getter;
import lombok.Setter;
import sda.quiz.entity.user.response.QuizDone;

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
    @Email(message = "Please insert proper email address")
    @NotEmpty(message = "Please insert email address")
    private String email;

    @Column(name="password")
   // @Length(min = 5,message = "Minimum password length is 5 characters")
    @NotEmpty(message = "Please insert password")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "Please insert name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<QuizDone> quizDoneList;


}
