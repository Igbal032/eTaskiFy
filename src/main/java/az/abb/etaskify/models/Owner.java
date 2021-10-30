package az.abb.etaskify.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String userName;
    private String email;
    @OneToOne
    private Organization organization;
}
