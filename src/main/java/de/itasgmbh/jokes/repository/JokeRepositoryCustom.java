package de.itasgmbh.jokes.repository;

import de.itasgmbh.jokes.model.Joke;

import java.util.List;

public interface JokeRepositoryCustom {

    List<Joke> findRandom(int quantity, String language);
    List<Joke> findRandom(int quantity);
}
