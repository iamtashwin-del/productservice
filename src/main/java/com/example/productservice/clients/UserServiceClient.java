package com.example.productservice.clients;

import com.example.productservice.Dtos.UserDto;
import com.example.productservice.InvalidTokenException;
import com.example.productservice.ProductNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    private RestTemplate restTemplate;

    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) throws InvalidTokenException {

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", token);

                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<UserDto> response =
                        restTemplate.exchange(
                                "http://localhost:7070/user/validate",
                                HttpMethod.GET,
                                entity,
                                UserDto.class
                        );

                return response.getBody();
            }

            catch (HttpClientErrorException.Unauthorized e) {
                throw new InvalidTokenException("Invalid token");
            }

            catch (HttpClientErrorException.BadRequest e) {
                throw new InvalidTokenException("Invalid token");
            }
    }
}
