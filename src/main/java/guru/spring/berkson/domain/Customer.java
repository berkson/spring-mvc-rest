package guru.spring.berkson.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:39
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meta_id", referencedColumnName = "id")
    private Meta meta;
    private String firstName;
    private String lastname;
    private String customerUrl;

    public Customer(Meta meta, String firstName, String lastname, String customerUrl) {
        this.meta = meta;
        this.firstName = firstName;
        this.lastname = lastname;
        this.customerUrl = customerUrl;
    }
}
