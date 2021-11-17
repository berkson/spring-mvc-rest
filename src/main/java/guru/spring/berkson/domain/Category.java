package guru.spring.berkson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.Collator;
import java.util.Locale;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 15:33
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Comparable<Category> {

    public Category(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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
