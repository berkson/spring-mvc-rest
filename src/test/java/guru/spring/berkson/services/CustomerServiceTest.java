package guru.spring.berkson.services;

import guru.spring.berkson.api.exceptions.CustomerNotFoundException;
import guru.spring.berkson.api.v1.mapper.CustomerMapper;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.MetaDTO;
import guru.spring.berkson.domain.Customer;
import guru.spring.berkson.domain.Meta;
import guru.spring.berkson.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 21:39
 */
class CustomerServiceTest {
    private static final String FIRSTNAME = "John";
    private static final Long CUSTOMER_ID = 5L;

    Customer customer;

    CustomerService customerService;

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
        Meta customerMeta = new Meta(52, 12, 5, "/url/teste/p", "/url/teste/n");
        customer = new Customer(customerMeta, FIRSTNAME, "Jacusi", "/shop/customer/234");
        customer.setId(CUSTOMER_ID);
    }

    @Test
    void testGetAllCustomers() {
        //given
        Meta meta = new Meta(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        List<Customer> dtos = Arrays.asList(customer, new Customer(
                meta, "Julius", "Janos", "shop/customer/321"
        ));
        when(customerRepository.findAll()).thenReturn(dtos);
        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertNotNull(customerDTOS);
        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerByFirstname() {
        //given
        when(customerRepository.findByFirstname(FIRSTNAME)).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByFirstname(FIRSTNAME);

        //then
        assertNotNull(customerDTO);
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
    }

    @Test
    void getById() {
        //given
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
    }

    @Test
    void emptyGetById() {
        //given
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(new Customer()));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);

        //then
        assertNotNull(customerDTO);
        assertNull(customerDTO.getFirstname());
        assertNull(customerDTO.getLastname());
    }

    @Test
    void getByIdDoNotExists() {
        //given
        when(customerRepository.findById(any(Long.class))).thenThrow(new NoSuchElementException());

        //then
        assertThrows(NoSuchElementException.class, () -> customerService.getCustomerById(CUSTOMER_ID));

    }

    @Test
    void createNewCustomer() {
        //given
        MetaDTO metaDTO = new MetaDTO(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        customerDTO.setMeta(metaDTO);

        Customer saved = new Customer();
        saved.setFirstname(customerDTO.getFirstname());
        saved.setLastname(customerDTO.getLastname());
        saved.setId(6L);

        when(customerRepository.save(any(Customer.class))).thenReturn(saved);
        //when
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        //then
        assertNotNull(savedDTO);
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/id/6", savedDTO.getCustomerUrl());

    }

    @Test
    void updateNewCustomer() {
        //given
        // Enviado no request
        MetaDTO metaDTO = new MetaDTO(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        CustomerDTO dtoClient = new CustomerDTO();
        dtoClient.setFirstname("Jim");
        dtoClient.setLastname("Kasama");
        dtoClient.setCustomerUrl("/api/v1/customers/id/6");
        dtoClient.setMeta(metaDTO);

        // salvo no banco
        Customer banco = new Customer();
        banco.setMeta(new Meta());
        banco.setFirstname("João");
        banco.setLastname("Guaraci");
        banco.setId(6L);
        banco.getMeta().setNextUrl(dtoClient.getMeta().getNextUrl());
        banco.getMeta().setPreviousUrl(dtoClient.getMeta().getPreviousUrl());
        banco.getMeta().setPage(dtoClient.getMeta().getPage());
        banco.getMeta().setLimite(dtoClient.getMeta().getLimite());
        banco.getMeta().setCount(dtoClient.getMeta().getCount());
        banco.setCustomerUrl("/api/v1/customers/id/6");

        // modificado banco
        Customer modificado = new Customer();
        modificado.setMeta(new Meta());
        modificado.setFirstname(dtoClient.getFirstname());
        modificado.setLastname(dtoClient.getLastname());
        modificado.setId(6L);
        modificado.getMeta().setNextUrl(dtoClient.getMeta().getNextUrl());
        modificado.getMeta().setPreviousUrl(dtoClient.getMeta().getPreviousUrl());
        modificado.getMeta().setPage(dtoClient.getMeta().getPage());
        modificado.getMeta().setLimite(dtoClient.getMeta().getLimite());
        modificado.getMeta().setCount(dtoClient.getMeta().getCount());
        modificado.setCustomerUrl("/api/v1/customers/id/6");

        when(customerRepository.existsById(6L)).thenReturn(true);
        when(customerRepository.getById(6L)).thenReturn(banco);
        when(customerRepository.save(any(Customer.class))).thenReturn(modificado);

        //when
        CustomerDTO savedDTO = customerService.updateCustomer(6L, dtoClient);

        //then
        assertNotNull(savedDTO);
        assertEquals(dtoClient.getFirstname(), savedDTO.getFirstname());
        assertEquals(modificado.getMeta().getPage(), dtoClient.getMeta().getPage());
    }

    @Test
    void updateCustomerEmpty() {
        when(customerRepository.existsById(6L)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.updateCustomer(6L, new CustomerDTO());
        });

    }

    @Test
    void deleteCustomer() {
        Long id = 2L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteCustomerEmpty() {
        when(customerRepository.existsById(5L)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.deleteCustomer(5L);
        });
    }
}