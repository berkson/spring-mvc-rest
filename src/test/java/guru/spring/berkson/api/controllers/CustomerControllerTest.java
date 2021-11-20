package guru.spring.berkson.api.controllers;

import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.MetaDTO;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 20:20
 */
class CustomerControllerTest {
    private static final String FIRSTNAME = "Zane";
    private static final String LASTNAME = "Soares";
    private static final String URL = "/shop/customer/123";
    private static final String PREVIOUSURL = "/shop/products/?page=2&limit=5";
    private static final String NEXTURL = "/shop/products/?page=3&limit=10";
    CustomerDTO customerDTO;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
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
}