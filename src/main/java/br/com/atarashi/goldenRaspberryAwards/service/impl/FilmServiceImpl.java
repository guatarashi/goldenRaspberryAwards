package br.com.atarashi.goldenRaspberryAwards.service.impl;

import br.com.atarashi.goldenRaspberryAwards.controller.request.FilmRequest;
import br.com.atarashi.goldenRaspberryAwards.controller.response.FilmResponse;
import br.com.atarashi.goldenRaspberryAwards.controller.response.IntervaloPremioResponse;
import br.com.atarashi.goldenRaspberryAwards.facade.FilmFacade;
import br.com.atarashi.goldenRaspberryAwards.mapper.FilmMapper;
import br.com.atarashi.goldenRaspberryAwards.repository.FilmRepository;
import br.com.atarashi.goldenRaspberryAwards.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmFacade filmFacade;

    @Autowired
    private FilmMapper filmMapper;

    public FilmServiceImpl(FilmRepository filmRepository, FilmFacade filmFacade, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmFacade = filmFacade;
        this.filmMapper = filmMapper;
    }

    @Override
    public void insert(FilmRequest filmRequest) {
        filmRepository.insert(filmMapper.filmRequestToFim(filmRequest));
    }

    @Override
    public void delete(int id) {
        filmRepository.delete(id);
    }

    @Override
    public List<FilmResponse> findAll() {
        return filmMapper.filmListToFimResponseList(filmRepository.findAll());
    }

    public FilmResponse findId(int id) {
        return filmMapper.filmToFimResponse(filmRepository.findId(id));
    }

    public IntervaloPremioResponse premiosConsecutivos() {
        return filmFacade.intervaloPremio(filmRepository.findByWinner());
    }
}
