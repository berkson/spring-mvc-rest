package guru.spring.berkson.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.Collator;


/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 15:33
 */
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Category implements Comparable<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Category category) {
        Collator brCollator = Collator.getInstance();
        brCollator.setStrength(Collator.PRIMARY);
        try {
            return brCollator.compare(this.name, category.name);
        } catch (Exception e) {
            return 0;
        }
    }
}
