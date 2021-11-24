package guru.spring.berkson.api.v1.controllers;

import guru.spring.berkson.api.exceptions.CustomerNotFoundException;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.MetaDTO;
import guru.spring.berkson.repositories.CustomerRepository;
import guru.spring.berkson.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.spring.berkson.api.v1.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 20:20
 */
class CustomerControllerTest {
    private static final Long CUSTOMER_ID = 6L;
    private static final String FIRSTNAME = "Zane";
    private static final String LASTNAME = "Soares";
    private static final String URL = "/shop/customer/123";
    private static final String PREVIOUSURL = "/shop/products/?page=2&limit=5";
    private static final String NEXTURL = "/shop/products/?page=3&limit=10";
    CustomerDTO customerDTO;

    @Mock
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerController customerController;

    @InjectMocks
    CustomerNotFoundAdvice customerNotFoundAdvice;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(customerNotFoundAdvice).build();
        MetaDTO customerMeta = new MetaDTO(52, 12, 5, PREVIOUSURL, NEXTURL);
        customerDTO = new CustomerDTO(customerMeta, FIRSTNAME, LASTNAME, URL);
    }

    @Test
    void getAllCustomers() throws Exception {
        //given
        MetaDTO metaDTO = new MetaDTO(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        List<CustomerDTO> dtos = Arrays.asList(customerDTO, new CustomerDTO(
                metaDTO, "Julius", "Janos", "shop/customer/321"
        ));

        when(customerService.getAllCustomers()).thenReturn(dtos);
        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertNotNull(customerDTOS);
        mockMvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    void getCustomerByFirstName() throws Exception {
        //given
        when(customerService.getCustomerByFirstname(FIRSTNAME)).thenReturn(customerDTO);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByFirstname(FIRSTNAME);

        //then
        assertNotNull(customerDTO);
        mockMvc.perform(get("/api/v1/customers/" + FIRSTNAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));
    }

    @Test
    void getCustomerById() throws Exception {
        //given
        when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(customerDTO);

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);

        //then
        assertNotNull(customerDTO);
        mockMvc.perform(get("/api/v1/customers/id/" + CUSTOMER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));
    }

    @Test
    void getCustomerByIdEmpty() throws Exception {
        //given
        when(customerService.getCustomerById(CUSTOMER_ID))
                .thenThrow(new CustomerNotFoundException(CUSTOMER_ID));

        //when
        assertThrows(CustomerNotFoundException.class, () -> {
            CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);
        });

        //then
        mockMvc.perform(get("/api/v1/customers/id/" + CUSTOMER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result
                        .getResolvedException() instanceof CustomerNotFoundException))
                .andExpect(result -> assertEquals("Customer id: " + CUSTOMER_ID + ", not found!",
                        result.getResolvedException().getMessage()));
    }

    @Test
    void createNewCustomer() throws Exception {
        //given
        MetaDTO metaDTO = new MetaDTO(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Flintstone");
        customer.setMeta(metaDTO);
        customer.setCustomerUrl("/api/v1/customers/id/");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomerUrl("/api/v1/customers/id/1");

        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);
        //when/then
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(returnDTO)))
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/id/1")));
    }

    @Test
    void updateCustomer() throws Exception {
        //given
        MetaDTO metaDTO = new MetaDTO(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Flintstone");
        customer.setMeta(metaDTO);
        customer.setCustomerUrl("/api/v1/customers/id");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomerUrl("/api/v1/customers/id/2");
        returnDTO.setMeta(metaDTO);

        when(customerService.updateCustomer(any(Long.class), any(CustomerDTO.class)))
                .thenReturn(returnDTO);
        //when/then
        mockMvc.perform(put("/api/v1/customers/id/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(returnDTO)))
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/id/2")));
    }

    @Test
    void updateCustomerException() throws Exception {
        //given
        CustomerDTO newDTO = new CustomerDTO(new MetaDTO(), "Jose",
                "Ortega", "/api/v1/customer/id/2");

        when(customerService.updateCustomer(any(Long.class), any(CustomerDTO.class)))
                .thenThrow(new CustomerNotFoundException(2L));

        //when
        assertThrows(CustomerNotFoundException.class, () -> {
            CustomerDTO customerDTO = customerService.updateCustomer(2L, newDTO);
        });

        //then
        mockMvc.perform(put("/api/v1/customers/id/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newDTO)))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result
                        .getResolvedException() instanceof CustomerNotFoundException));
    }
}