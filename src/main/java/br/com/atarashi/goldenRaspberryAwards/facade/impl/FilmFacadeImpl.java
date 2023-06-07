package br.com.atarashi.goldenRaspberryAwards.facade.impl;

import br.com.atarashi.goldenRaspberryAwards.controller.response.IntervaloPremioResponse;
import br.com.atarashi.goldenRaspberryAwards.controller.response.IntervaloResponse;
import br.com.atarashi.goldenRaspberryAwards.facade.FilmFacade;
import br.com.atarashi.goldenRaspberryAwards.repository.model.Film;
import br.com.atarashi.goldenRaspberryAwards.repository.model.ProducerWinner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class FilmFacadeImpl implements FilmFacade {

    public IntervaloPremioResponse intervaloPremio(List<Film> films) {
        IntervaloPremioResponse intervaloPremioResponses = new IntervaloPremioResponse();
        List<ProducerWinner> producerWinners = new ArrayList<>();
        Map<String, IntervaloResponse> intervalMap = new HashMap<>();

        splitProcuresWinners(films, producerWinners);

        List<ProducerWinner> producerWinnersGanhosConsecutivos = new ArrayList<>();

        consecutiveWinningProducers(producerWinners, producerWinnersGanhosConsecutivos);

        calculandoIntervalos(producerWinnersGanhosConsecutivos);

        int intervalMinimo = producerWinnersGanhosConsecutivos
                .stream()
                .min(Comparator.comparing(ProducerWinner::getInterval))
                .get()
                .getInterval();

        int intervalMaximo = producerWinnersGanhosConsecutivos
                .stream()
                .max(Comparator.comparing(ProducerWinner::getInterval))
                .get()
                .getInterval();

        producersWinnerMaximoInterval(intervalMap, producerWinnersGanhosConsecutivos, intervalMaximo);

        producersWinnerMinimoInterval(intervalMap, producerWinnersGanhosConsecutivos, intervalMinimo);

        List<IntervaloResponse> intervaloResponsesMaximo = new ArrayList<>();
        List<IntervaloResponse> intervaloResponsesMinimo = new ArrayList<>();

        intervaloPremioResponses(intervaloPremioResponses, intervalMap, intervaloResponsesMaximo, intervaloResponsesMinimo);

        return intervaloPremioResponses;
    }

    private void intervaloPremioResponses(IntervaloPremioResponse intervaloPremioResponses, Map<String, IntervaloResponse> intervalMap, List<IntervaloResponse> intervaloResponsesMaximo, List<IntervaloResponse> intervaloResponsesMinimo) {
        intervalMap.forEach((s, intervaloResponse) -> {
            if (s.substring(0, s.indexOf("|")).equals("max")) {
                intervaloResponsesMaximo.add(intervaloResponse);
            }
            if (s.substring(0, s.indexOf("|")).equals("min")) {
                intervaloResponsesMinimo.add(intervaloResponse);
            }
        });

        intervaloPremioResponses.setMax(intervaloResponsesMaximo);
        intervaloPremioResponses.setMin(intervaloResponsesMinimo);
    }

    private void producersWinnerMinimoInterval(Map<String, IntervaloResponse> intervalMap, List<ProducerWinner> producerWinnersGanhosConsecutivos, int intervalMinimo) {
        List<ProducerWinner> producersWinnersIntervalMinimo = new ArrayList<>();
        producerWinnersGanhosConsecutivos
                .forEach(producerWinner1 -> {
                    if (producerWinner1.getInterval() == intervalMinimo) {
                        producersWinnersIntervalMinimo.add(producerWinner1);
                    }
                });

        restartingLogicalDelete(producersWinnersIntervalMinimo);

        producersWinnersIntervalMinimo
                .forEach(producerWinner -> {
                    List<ProducerWinner> producerWinnerListMin2 = new ArrayList<>();
                    producersWinnersIntervalMinimo
                            .forEach(producerWinner1 -> {
                                if (producerWinner1.getProducer().equals(producerWinner.getProducer()) && !producerWinner1.isDelete()) {
                                    producerWinnerListMin2.add(producerWinner1);
                                    producerWinner1.setDelete(true);
                                }
                            });

                    if (producerWinnerListMin2.size() > 0) {
                        intervalMap.put("min|"+producerWinnerListMin2.get(0).getProducer(), IntervaloResponse.builder()
                                .producer(producerWinnerListMin2.get(0).getProducer())
                                .interval(String.valueOf(producerWinnerListMin2.get(0).getInterval()))
                                .previousWin(String.valueOf(producerWinnerListMin2.stream().min(Comparator.comparing(ProducerWinner::getYear)).get().getYear()))
                                .followingWin(String.valueOf(producerWinnerListMin2.stream().max(Comparator.comparing(ProducerWinner::getYear)).get().getYear()))
                                .build());
                    }
                });
    }

    private void producersWinnerMaximoInterval(Map<String, IntervaloResponse> intervalMap, List<ProducerWinner> producerWinnersGanhosConsecutivos, int intervalMaximo) {
        List<ProducerWinner> producersWinnersIntervalMaximo = new ArrayList<>();
        producerWinnersGanhosConsecutivos
                .forEach(producerWinner1 -> {
                    if (producerWinner1.getInterval() == intervalMaximo) {
                        producersWinnersIntervalMaximo.add(producerWinner1);
                    }
                });

        restartingLogicalDelete(producersWinnersIntervalMaximo);

        producersWinnersIntervalMaximo
                .forEach(producerWinner -> {
                    List<ProducerWinner> producerWinnerListMax2 = new ArrayList<>();
                    producersWinnersIntervalMaximo
                            .forEach(producerWinner1 -> {
                                if (producerWinner1.getProducer().equals(producerWinner.getProducer()) && !producerWinner1.isDelete()) {
                                    producerWinnerListMax2.add(producerWinner1);
                                    producerWinner1.setDelete(true);
                                }
                            });

                    if (producerWinnerListMax2.size() > 0) {
                        intervalMap.put("max|" + producerWinnerListMax2.get(0).getProducer(), IntervaloResponse.builder()
                                .producer(producerWinnerListMax2.get(0).getProducer())
                                .interval(String.valueOf(producerWinnerListMax2.get(0).getInterval()))
                                .previousWin(String.valueOf(producerWinnerListMax2.stream().min(Comparator.comparing(ProducerWinner::getYear)).get().getYear()))
                                .followingWin(String.valueOf(producerWinnerListMax2.stream().max(Comparator.comparing(ProducerWinner::getYear)).get().getYear()))
                                .build());
                    }
                });
    }

    private void restartingLogicalDelete(List<ProducerWinner> producersWinnersIntervalMaximo) {
        producersWinnersIntervalMaximo
                .forEach(producerWinner -> producerWinner.setDelete(false));
    }

    private void calculandoIntervalos(List<ProducerWinner> producerWinnersGanhosConsecutivos) {
        producerWinnersGanhosConsecutivos
                .forEach(producerWinner ->
                        producerWinnersGanhosConsecutivos
                                .forEach(producerWinner1 -> {
                                    if (producerWinner1.getProducer().equals(producerWinner.getProducer()) && producerWinner1.getYear() != producerWinner.getYear()) {
                                        int interval = producerWinner1.getYear() - producerWinner.getYear();
                                        if (interval < 0) {
                                            interval = interval * -1;
                                        }
                                        producerWinner.setInterval(interval);
                                    }
                                })
                );
    }

    private void consecutiveWinningProducers(List<ProducerWinner> producerWinners, List<ProducerWinner> producerWinnersGanhosConsecutivos) {
        producerWinners
                .forEach(producerWinner -> {
                    List<ProducerWinner> producerWinnersGanhosConsecutivosTemp = new ArrayList<>();
                    producerWinners.forEach(producerWinner1 -> {
                        if (producerWinner1.getProducer().equals(producerWinner.getProducer()) && !producerWinner1.isDelete()) {
                            producerWinnersGanhosConsecutivosTemp.add(producerWinner1);
                            producerWinner1.setDelete(true);
                        }
                    });

                    if (producerWinnersGanhosConsecutivosTemp.size() > 1) {
                        producerWinnersGanhosConsecutivos.addAll(producerWinnersGanhosConsecutivosTemp);
                    }
                });
    }

    private void splitProcuresWinners(List<Film> films, List<ProducerWinner> producerWinners) {
        films.forEach(film -> {
            List<String> producersList = Stream.of(film.getProducers().split(","))
                    .map(String::trim)
                    .toList();

            List<String> producers2List = producersList.stream()
                    .flatMap(producer -> Stream.of(producer.replace(" and ", ",").split(","))
                            .map(String::trim))
                    .toList();

            producers2List.forEach(producer -> producerWinners.add(ProducerWinner.builder()
                    .year(Integer.parseInt(film.getYear()))
                    .producer(producer)
                    .build()));
        });
    }
}
