package de.itasgmbh.jokes.repository;

import de.itasgmbh.jokes.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JokeRepositoryCustomImpl implements JokeRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public JokeRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Joke> findRandom(int quantity, String language) {
        List<Joke> jokes = mongoTemplate.find(new Query()
                .addCriteria(Criteria.where("language").regex(language)), Joke.class, Joke.COLLECTION);

        if (jokes.size() <= quantity || jokes.isEmpty()) {
            return jokes;
        } else {
            return IntStream.range(0, quantity).mapToObj(i -> jokes.get((int) (Math.random() * jokes.size())))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<Joke> findRandom(int quantity) {
        return this.findRandom(quantity, ".+");
    }
}
