package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporadas;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.Converterdados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exibirmenu {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoapi = new ConsumoApi();
    private Converterdados converterdados = new Converterdados();

    public void exibemenu() throws IOException, InterruptedException {
        System.out.println("*************************************");
        System.out.println("Digite uma serie para a busca: ");
        String  serie = leitura.nextLine();
        String enderecos = "https://www.omdbapi.com/?t="+ serie + "&apikey=5a81000e";

        var json = consumoapi.obterDados(enderecos.replace(" ", "+"));
        var dados = converterdados.receberdados(json, DadosSerie.class);
        System.out.println(dados);
        System.out.println("*************************************");
        System.out.println("Deseja saber mais alguma informaçao sobre a serie[S/N]? ");
        var maisInformacoes = leitura.nextLine();
        if(maisInformacoes.equals("S")){
            ArrayList<DadosTemporadas> listadeepisodios = new ArrayList<>();
            for (int j = 1; j <dados.totalTemporadas() ; j++) {
                json = consumoapi.obterDados("https://www.omdbapi.com/?t="+ serie.replace(" ", "+") +"&season="+ j + "&apikey=5a81000e");
                DadosTemporadas dadosTemporadas = converterdados.receberdados(json, DadosTemporadas.class);
                listadeepisodios.add(dadosTemporadas);

            }

            for (DadosTemporadas temporada : listadeepisodios) {
                System.out.println("Temporada " + temporada.numero());

                for (DadosEpisodios episodio : temporada.episodios()) {
                    System.out.println("   - " + episodio.numero() + episodio.titulo());
                }

                System.out.println(); // linha em branco entre temporadas
            }
        } else if (maisInformacoes.equals("N")) {
            System.out.println("Obrigado por usar o screenmatch!");

        }else{
            System.out.println("Resposta Invalida.");
        }

    }
}
