package guru.spring.berkson.repositories;

import guru.spring.berkson.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:53
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String firstname);
}
