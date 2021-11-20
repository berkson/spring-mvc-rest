package guru.spring.berkson.api.v1.mapper;

import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.domain.Customer;
import guru.spring.berkson.domain.Meta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 21:24
 */
class CustomerMapperTest {


    Customer customer;
    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = CustomerMapper.INSTANCE;
        Meta meta = new Meta(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        customer = new Customer(
                meta, "Julius", "Janos", "shop/customer/321"
        );
    }

    @Test
    void customerToCustomerDTO() {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertNotNull(customerDTO);
        assertEquals("Julius", customerDTO.getFirstname());
        assertEquals(31, customerDTO.getMeta().getCount());
    }

    @Test
    void CustomerDTONull() {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(null);

        assertNull(customerDTO);
    }
}