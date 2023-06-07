package br.com.atarashi.goldenRaspberryAwards.controller;

import br.com.atarashi.goldenRaspberryAwards.controller.request.FilmRequest;
import br.com.atarashi.goldenRaspberryAwards.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("films")
@RequiredArgsConstructor
public class FilmInsertController {

    private final FilmService filmService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody FilmRequest filmRequest) {
        filmService.insert(filmRequest);
    }
}
