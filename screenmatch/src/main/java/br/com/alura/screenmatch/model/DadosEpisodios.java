package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodios(@JsonAlias("Title") String titulo,@JsonAlias("Season") Integer temporada,@JsonAlias("Episode") Integer numero, @JsonAlias("Released") String dataLancamento, @JsonAlias("imdbRating") String avaliacao){
    public double avaliacaoComoDouble() {
        if (avaliacao == null || avaliacao.equalsIgnoreCase("N/A")) {
            return 0.0; // ou return -1.0 se quiser jogar pro final de vez
        }
        return Double.parseDouble(avaliacao);
    }
    @Override
    public String toString() {
        return "DadosEpisodios{" +
                "titulo: '" + titulo + '\'' +
                ", Temporada: " + temporada +
                ", numero: " + numero +
                ", dataLancameto: '" + dataLancamento + '\'' +
                '}';
    }
}
