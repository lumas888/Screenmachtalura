package br.com.alura.screenmatch.service;

public interface IConverterDados {
    <T> T receberdados(String json, Class<T> classe);
}
