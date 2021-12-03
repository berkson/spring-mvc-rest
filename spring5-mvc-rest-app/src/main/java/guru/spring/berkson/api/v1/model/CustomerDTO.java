package guru.spring.berkson.api.v1.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO implements Serializable {

    private MetaDTO meta;
    @ApiModelProperty(value = "Primeiro nome do cliente")
    private String firstname;
    @ApiModelProperty(value = "Último nome do cliente")
    private String lastname;
    @ApiModelProperty(value = "URL do cliente")
    @Getter(AccessLevel.NONE)
    private String customerUrl;

    @JsonProperty(value = "customer_url")
    @JacksonXmlProperty(localName = "customerUrl")
    public String getCustomerUrl() {
        return customerUrl;
    }
}
