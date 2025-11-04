package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.Converterdados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoapi = new ConsumoApi();

		var json = consumoapi.obterDados("https://www.omdbapi.com/?t=peaky+blinders&apikey=5a81000e");
		System.out.println(json);

		Converterdados converterdados = new Converterdados();
		DadosSerie dados = converterdados.receberdados(json, DadosSerie.class);
		System.out.println(dados);


	}
}
