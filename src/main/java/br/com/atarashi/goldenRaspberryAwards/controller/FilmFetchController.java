package br.com.atarashi.goldenRaspberryAwards.controller;

import br.com.atarashi.goldenRaspberryAwards.controller.response.FilmResponse;
import br.com.atarashi.goldenRaspberryAwards.repository.model.Film;
import br.com.atarashi.goldenRaspberryAwards.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("films")
@RequiredArgsConstructor
public class FilmFetchController {

    private final FilmService filmService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FilmResponse> fetch() {
        return filmService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmResponse fetch(@PathVariable int id) {
        return filmService.findId(id);
    }
}
