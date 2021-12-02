package guru.spring.berkson.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class CustomerDTO {

    private MetaDTO meta;
    @ApiModelProperty(value = "Primeiro nome do cliente", required = true)
    private String firstname;
    @ApiModelProperty(value = "Último nome do cliente", required = true)
    private String lastname;
    @ApiModelProperty(value = "URL do cliente")
    @JsonProperty("customer_url")
    private String customerUrl;
}
