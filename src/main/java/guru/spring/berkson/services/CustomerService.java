package guru.spring.berkson.services;

import guru.spring.berkson.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 23:05
 */
public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByFirstName(String firstname);
}
