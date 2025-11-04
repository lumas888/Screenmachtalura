package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodios(@JsonAlias("Title") String titulo,@JsonAlias("Season") Integer Temporada,@JsonAlias("Episode") Integer numero, @JsonAlias("Released") String dataLancameto) {

    @Override
    public String toString() {
        return "DadosEpisodios{" +
                "titulo: '" + titulo + '\'' +
                ", Temporada: " + Temporada +
                ", numero: " + numero +
                ", dataLancameto: '" + dataLancameto + '\'' +
                '}';
    }
}
