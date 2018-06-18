package de.itasgmbh.jokes.repository;

import de.itasgmbh.jokes.model.Joke;
import de.itasgmbh.jokes.rest.JokeRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JokeReactiveRepositoryCustomImpl implements JokeReactiveRepositoryCustom {
    private final Map<String, List<Joke>> jokesByLanguage = new ConcurrentHashMap<>();
    private final ReactiveMongoTemplate reactiveTemplate;
    private final Map<String, Flux<Joke>> jokesByLanguagesFluxes = new ConcurrentHashMap<>();

    @Autowired
    public JokeReactiveRepositoryCustomImpl(ReactiveMongoTemplate reactiveTemplate) {

        this.reactiveTemplate = reactiveTemplate;
    }

    @Override
    public Flux<Joke> findRandom(int quantity, String language) {
        Flux<Joke> fluxJokes;
        if (jokesByLanguage.containsKey(language)) {
            fluxJokes = Flux.fromIterable(jokesByLanguage.get(language));
        } else {
            fluxJokes =
                    jokesByLanguagesFluxes
                            .computeIfAbsent(language, f -> reactiveTemplate.find(new Query()
                                    .addCriteria(Criteria.where("language").regex(language)), Joke.class, Joke.COLLECTION)
                                    .doOnNext(joke -> jokesByLanguage.computeIfAbsent(language, s -> new ArrayList<>()).add(joke)));
        }

       return reactiveTemplate.find(new Query()
                .addCriteria(Criteria.where("language").regex(language)), Joke.class, Joke.COLLECTION).sample(Duration.ofSeconds(quantity));

      //  return fluxJokes.sample(Duration.ofSeconds((int)(Math.random()*quantity)));

    }

    @Override
    public Flux<Joke> findRandom(int quantity) {
        return findRandom(quantity, "de");
    }
}
