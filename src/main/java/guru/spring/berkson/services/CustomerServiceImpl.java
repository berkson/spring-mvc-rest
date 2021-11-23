package guru.spring.berkson.services;

import guru.spring.berkson.api.v1.mapper.CustomerMapper;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.domain.Customer;
import guru.spring.berkson.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 23:06
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByFirstname(String firstname) {
        return customerMapper
                .customerToCustomerDTO(customerRepository.findByFirstname(firstname));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws NoSuchElementException {
        return (CustomerDTO) customerRepository
                .findById(id).map((Function<Customer, Object>) customerMapper::customerToCustomerDTO).get();
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.getMeta().setCustomer(customer);
        Customer saved = customerRepository.save(customer);
        saved.setCustomerUrl("/api/v1/customers/id/" + saved.getId());
        customerRepository.save(saved);
        return customerMapper.customerToCustomerDTO(saved);
    }
}
