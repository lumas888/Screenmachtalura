package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodios;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporadas;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.Converterdados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



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
        System.out.println("Deseja saber mais informações sobre a serie[S/N]? ");
        var maisInformacoes = leitura.nextLine();
        var informacoesMaiusculo = maisInformacoes.toUpperCase();
        if(informacoesMaiusculo.equals("S")){
            ArrayList<DadosTemporadas> listaTemporada = new ArrayList<>();
            for (int j = 1; j <dados.totalTemporadas() ; j++) {
                json = consumoapi.obterDados("https://www.omdbapi.com/?t="+ serie.replace(" ", "+") +"&season="+ j + "&apikey=5a81000e");
                DadosTemporadas dadosTemporadas = converterdados.receberdados(json, DadosTemporadas.class);
                listaTemporada.add(dadosTemporadas);

            }
            //Para cada temporada na lista de temporadas pegar os episodios da temporada
            // e para cada episodio printar o rirulo dele(Maneira alternativa e resumida de fazer).

//            listaTemporada.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

            for (DadosTemporadas temporada : listaTemporada) {
                System.out.println("Temporada " + temporada.num());

                for (DadosEpisodios episodio : temporada.episodios()) {
                    System.out.println("   - " + episodio.numero() + " " +  episodio.titulo());
                }

                System.out.println(); // linha em branco entre temporadas
            }

            System.out.println("🏆 Ranking top 5 melhores episódios:");

            List<DadosEpisodios> top5Episodios = listaTemporada.stream()
                    .flatMap(t -> t.episodios().stream()
                    .map(e -> new DadosEpisodios(
                            e.titulo(),
                            t.num(),
                            e.numero(),
                            e.dataLancamento(),
                            e.avaliacao()

                    ))

                    )
// junta todos os episódios de todas as temporadas
                    .sorted(Comparator.comparing(DadosEpisodios::avaliacaoComoDouble).reversed()) // ordena por nota
                    .limit(5) // pega só os 5 melhores
                    .toList();


                for (DadosEpisodios ep : top5Episodios) {

                    System.out.println("- " + "Temporada: " + ep.temporada() + " "+  ep.titulo() + " (" + ep.avaliacaoComoDouble() + ")");
                }


        } else if (informacoesMaiusculo.equals("N")) {
            System.out.println("Obrigado por usar o screenmatch!");

        } else {
            System.out.println("Resposta Invalida.");
        }

    }
    public void top5Melhoreseps(){

    }
}
