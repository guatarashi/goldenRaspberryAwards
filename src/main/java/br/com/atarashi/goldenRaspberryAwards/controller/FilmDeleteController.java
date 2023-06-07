package br.com.atarashi.goldenRaspberryAwards.controller;

import br.com.atarashi.goldenRaspberryAwards.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("films")
@RequiredArgsConstructor
public class FilmDeleteController {

    private final FilmService filmService;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        filmService.delete(id);
    }
}
