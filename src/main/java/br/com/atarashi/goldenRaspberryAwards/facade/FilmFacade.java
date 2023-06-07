package br.com.atarashi.goldenRaspberryAwards.facade;

import br.com.atarashi.goldenRaspberryAwards.controller.response.IntervaloPremioResponse;
import br.com.atarashi.goldenRaspberryAwards.repository.model.Film;

import java.util.List;

public interface FilmFacade {

    IntervaloPremioResponse intervaloPremio(List<Film> films);
}
