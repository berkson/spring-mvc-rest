package guru.spring.berkson.repository;

import guru.spring.berkson.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Berkson Ximenes
 * Date: 17/11/2021
 * Time: 15:41
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
