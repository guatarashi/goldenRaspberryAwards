package br.com.atarashi.goldenRaspberryAwards.controller;

import br.com.atarashi.goldenRaspberryAwards.controller.response.IntervaloPremioResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class FilmPremiosConsecutivosControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void premiosConsecutivosMinTest() {
        ResponseEntity<IntervaloPremioResponse> response = this.testRestTemplate
                .exchange("/films/premios-consecutivos", HttpMethod.GET, null, IntervaloPremioResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getMin().get(0).getProducer(), "Joel Silver");
        assertEquals(response.getBody().getMin().get(0).getInterval(), "1");
        assertEquals(response.getBody().getMin().get(0).getPreviousWin(), "1990");
        assertEquals(response.getBody().getMin().get(0).getFollowingWin(), "1991");
    }

    @Test
    public void premiosConsecutivosMaxTest() {
        ResponseEntity<IntervaloPremioResponse> response = this.testRestTemplate
                .exchange("/films/premios-consecutivos", HttpMethod.GET, null, IntervaloPremioResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getMax().get(0).getProducer(), "Matthew Vaughn");
        assertEquals(response.getBody().getMax().get(0).getInterval(), "13");
        assertEquals(response.getBody().getMax().get(0).getPreviousWin(), "2002");
        assertEquals(response.getBody().getMax().get(0).getFollowingWin(), "2015");
    }

}