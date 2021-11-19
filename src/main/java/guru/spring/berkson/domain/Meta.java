package guru.spring.berkson.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:40
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meta")
public class Meta {
    @Id
    @Column(name = "customer_id", nullable = false)
    private Long id;
    private Integer count;
    private Integer limite;
    private Integer page;
    private String previousUrl;
    private String nextUrl;
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Meta(Integer count, Integer limite, Integer page, String previousUrl, String nextUrl) {
        this.count = count;
        this.limite = limite;
        this.page = page;
        this.previousUrl = previousUrl;
        this.nextUrl = nextUrl;
    }
}
