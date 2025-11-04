package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,@JsonAlias("Year") String ano ,@JsonAlias("totalSeasons") Integer totalTemporadas) {
    @Override
    public String toString() {
        return "DadosSerie{" +
                "titulo: '" + titulo + '\'' +
                ", ano: '" + ano + '\'' +
                ", totalTemporadas: " + totalTemporadas +
                '}';
    }
}
