package guru.spring.berkson.bootstrap;

import guru.spring.berkson.api.v1.controllers.CustomerController;
import guru.spring.berkson.domain.Customer;
import guru.spring.berkson.domain.Meta;
import guru.spring.berkson.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 23:18
 */
@Component
public class CustomerBootStrap implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    public CustomerBootStrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.setCustomers();
    }

    private void setCustomers() {
        List<Customer> customers = new ArrayList<>();
        Meta vMeta = new Meta(32, 10, 1, "/shop/products/?page=1&limit=10", "/shop/products/?page=3&limit=10");
        Meta bMeta = new Meta(15, 5, 2, "/shop/products/?page=2&limit=5", "/shop/products/?page=3&limit=10");
        Meta dMeta = new Meta(28, 15, 6, "/shop/products/?page=6&limit=15", "/shop/products/?page=3&limit=10");
        Customer victor = new Customer(
                vMeta,
                "Victor", "Soares", CustomerController.BASE_URL + "/id/");
        Customer berkson = new Customer(
                bMeta,
                "Berkson", "Soares", CustomerController.BASE_URL + "/id/");
        Customer diego = new Customer(
                dMeta,
                "Diego", "Soares", CustomerController.BASE_URL + "/id/");

        customers.add(victor);
        customers.add(berkson);
        customers.add(diego);
        customers.get(0).getMeta().setCustomer(victor);
        customers.get(1).getMeta().setCustomer(berkson);
        customers.get(2).getMeta().setCustomer(diego);
        for (Customer customer: customers){
             Customer customer1 = customerRepository.save(customer);
             customer1.setCustomerUrl(customer1.getCustomerUrl() + customer1.getId());
             customerRepository.save(customer1);
        }
        System.out.println("Salvando Clientes: " + customerRepository.count());
    }
}
