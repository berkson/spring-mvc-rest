package guru.spring.berkson.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 22:43
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaDTO implements Serializable {
    private Integer count;
    private Integer limite;
    private Integer page;
    @JsonProperty(value = "previous_url")
    @JacksonXmlProperty(localName = "previousUrl")
    private String previousUrl;
    @JsonProperty(value = "next_url")
    @JacksonXmlProperty(localName = "nextUrl")
    private String nextUrl;
}
