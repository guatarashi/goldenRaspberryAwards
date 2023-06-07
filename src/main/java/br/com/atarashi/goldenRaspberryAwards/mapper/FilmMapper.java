package br.com.atarashi.goldenRaspberryAwards.mapper;

import br.com.atarashi.goldenRaspberryAwards.controller.request.FilmRequest;
import br.com.atarashi.goldenRaspberryAwards.controller.response.FilmResponse;
import br.com.atarashi.goldenRaspberryAwards.repository.model.Film;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface FilmMapper {

    FilmResponse filmToFimResponse(Film film);
    List<FilmResponse> filmListToFimResponseList(List<Film> film);
    Film filmRequestToFim(FilmRequest filmRequest);

}
