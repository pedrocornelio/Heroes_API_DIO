package com.digitalinnovation.livecoding.controller;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovation.livecoding.repository.HeroesRepository;
import com.digitalinnovation.livecoding.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovation.livecoding.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j

public class HeroesController {
    HeroesService heroesService;

    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesService = heroesService;
        this.heroesRepository = heroesRepository;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems() {
        log.info("REQUESTING THE LIST OFF ALL HEROES");
        return heroesService.findAll();

    }


    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Object>> findByIdHero(@PathVariable String id) {
        log.info("REQUESTING THE HERO WITH ID {}", id);
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A NEW HERO WAS CREATED");
        return heroesService.save(heroes);

    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deletebyIDHero(@PathVariable String id) {
        heroesService.deletebyIDHero(id);
        log.info("DELETING THE HERO WITH ID {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}