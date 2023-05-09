package std.guedes.eclipselinktest.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Essa classe representa uma entidade do banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="People")
@Table(name="People")
@NamedQueries({
        @NamedQuery(name="Person.findByName", query="SELECT p FROM People p WHERE p.name = :name GROUP BY p.name"),
        @NamedQuery(name="Person.findAllByFirstName", query = "SELECT p FROM People p WHERE p.name LIKE CONCAT(:firstName, '%')"),
        @NamedQuery(name="Person.findByLastName", query="SELECT p FROM People p WHERE p.name LIKE CONCAT('%', :lastName) GROUP BY p.name"),
        @NamedQuery(name="Person.findAll", query="SELECT p FROM People p")
})
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(length=80, nullable=false)
    private String name;

}