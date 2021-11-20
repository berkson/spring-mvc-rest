package guru.spring.berkson.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 21:06
 */
@Getter
@Setter
@AllArgsConstructor
public class CustomersDTO {
    List<CustomerDTO> customers;
}
