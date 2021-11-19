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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer count;
    private Integer limit;
    private Integer page;
    private String previousUrl;
    private String nextUrl;
    @OneToOne(mappedBy = "meta")
    private Customer customer;

    public Meta(Integer count, Integer limit, Integer page, String previousUrl, String nextUrl) {
        this.count = count;
        this.limit = limit;
        this.page = page;
        this.previousUrl = previousUrl;
        this.nextUrl = nextUrl;
    }
}
