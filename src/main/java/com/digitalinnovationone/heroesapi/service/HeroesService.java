package com.digitalinnovation.livecoding.service;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovation.livecoding.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class HeroesService {
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    //PESQUISA PARA RETORNAR TODOS OS SUPERHEROIS
    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    //PESQUISA PARA RETORNAR O SUPERHEROI POR ID
    public  Mono<Heroes> findByIdHero(String id){
        return  Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    //PESQUISA PARA SALVAR O SUPERHEROI
    public Mono<Heroes> save(Heroes heroes){
        return  Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    //PESQUISA PARA DELETAR O SUPERHEROI POR ID
    public Mono<Boolean> deletebyIDHero(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);

    }

}

