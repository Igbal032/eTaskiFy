package az.abb.etaskify.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String email;
    private String passWord;
    private String role;
}
