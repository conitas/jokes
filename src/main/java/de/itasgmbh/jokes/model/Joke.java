package de.itasgmbh.jokes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = Joke.COLLECTION)
public class Joke {
    public static  final  String COLLECTION = "jokes";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke1 = (Joke) o;
        return Objects.equals(id, joke1.id) &&
                Objects.equals(joke, joke1.joke) &&
                Objects.equals(language, joke1.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, joke, language);
    }

    public Joke(String joke, String language) {
        this(joke);
        this.language = language;
    }

    public Joke(String joke) {
        this.joke = joke;
    }

    public Joke() {
    }

    @Id
    public String id;
    public String joke;
    public String language = "en";

    @Override
    public String toString() {
        return "Joke{" +
                "id='" + id + '\'' +
                ", joke='" + joke + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
