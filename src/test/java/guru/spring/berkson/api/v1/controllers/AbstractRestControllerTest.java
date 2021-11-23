package guru.spring.berkson.api.v1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Berkson Ximenes
 * Date: 22/11/2021
 * Time: 22:23
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
