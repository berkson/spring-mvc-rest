package guru.spring.berkson.api.v1.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 21:06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Customers")
public class CustomersDTO implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Customer")
    @JsonProperty(value = "customers")
    List<CustomerDTO> customers;
}
