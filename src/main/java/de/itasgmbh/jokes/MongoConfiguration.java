package de.itasgmbh.jokes;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientURI;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.net.UnknownHostException;


@Configuration
@EnableReactiveMongoRepositories(basePackages = "de.itasgmbh.jokes.repository")
@EnableMongoRepositories(basePackages = "de.itasgmbh.jokes.repository")
public class MongoConfiguration {

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {
        try {
            return new SimpleReactiveMongoDatabaseFactory(new ConnectionString(mongoUri+"&streamType=netty"));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory());
    }

    @Value("${spring.data.mongodb.uri:mongodb://localhost:27017}")
    private String mongoUri;

    @Bean
    public SimpleMongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClientURI(mongoUri));
    }
}
