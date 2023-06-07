package br.com.atarashi.goldenRaspberryAwards.service;

import br.com.atarashi.goldenRaspberryAwards.controller.request.FilmRequest;
import br.com.atarashi.goldenRaspberryAwards.controller.response.FilmResponse;
import br.com.atarashi.goldenRaspberryAwards.controller.response.IntervaloPremioResponse;

import java.util.List;

public interface FilmService {

    void insert(FilmRequest filmRequest);
    void delete(int id);
    List<FilmResponse> findAll();
    FilmResponse findId(int id);
    IntervaloPremioResponse premiosConsecutivos();
}
