package br.com.atarashi.goldenRaspberryAwards.controller;

import br.com.atarashi.goldenRaspberryAwards.repository.model.Film;
import br.com.atarashi.goldenRaspberryAwards.service.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class FilmDeleteControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void removerAvaliacaoTest() {

        ResponseEntity<Void> response = this.testRestTemplate.exchange("/films/" + 206, HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}