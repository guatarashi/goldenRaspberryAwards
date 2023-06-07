package br.com.atarashi.goldenRaspberryAwards.controller;

import br.com.atarashi.goldenRaspberryAwards.controller.request.FilmRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class FilmInsertControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    FilmRequest filmRequest;

    @BeforeAll
    public void iniciar() {
        this.filmRequest = FilmRequest.builder()
                .year("2023")
                .title("Poeira em alto mar")
                .studios("curvaDeRio")
                .producers("Mariah")
                .winner("")
                .build();
    }

    @Test
    public void insertFilmTest() {

        HttpEntity<FilmRequest> httpEntity = new HttpEntity<>(filmRequest);

        ResponseEntity<Void> response = this.testRestTemplate.exchange("/films", HttpMethod.POST, httpEntity, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}