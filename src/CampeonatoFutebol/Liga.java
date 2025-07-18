
package CampeonatoFutebol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Liga {

    private final CampeonatoFutebol.Equipe[] equipes; // Matriz para equipes (número fixo)
    private final ArrayList<Jogo> jogos; // ArrayList para jogos (número variável)

    private int semanasCongelantesConsecutivas;
    private double temperaturaMaisQuente;
    private double somaTemperaturasJogos;
    private int contadorSemanasComJogos;

    private final Random random;

    // Constante para definir a temperatura de congelamento
    private static final double TEMP_CONGELANTE = 0.0;

    public Liga(String[] nomesEquipes) {
        this.equipes = new Equipe[nomesEquipes.length];
        for (int i = 0; i < nomesEquipes.length; i++) {
            this.equipes[i] = new Equipe(nomesEquipes[i]);
        }

        this.jogos = new ArrayList<>();
        this.semanasCongelantesConsecutivas = 0;
        this.temperaturaMaisQuente = Double.MIN_VALUE;
        this.somaTemperaturasJogos = 0;
        this.contadorSemanasComJogos = 0;
        this.random = new Random();
    }
    //temperatura baixissima
    public void jogarSemana(double temperatura) {
        if (temperatura <= TEMP_CONGELANTE) {
            semanasCongelantesConsecutivas++;
            System.out.println("Temperatura congelante (" + temperatura + "°C). Nenhum jogo nesta semana.");
        } else {
            // Se a temperatura não é congelante, reseta o contador
            semanasCongelantesConsecutivas = 0;

            // Atualiza estatísticas de temperatura
            if (temperatura > temperaturaMaisQuente) {
                temperaturaMaisQuente = temperatura;
            }
            somaTemperaturasJogos += temperatura;
            contadorSemanasComJogos++;

            System.out.println("\n--- Semana com jogos! Temperatura: " + temperatura + "°C ---");
            agendarEJogarPartidas(temperatura);
        }
    }

    private void agendarEJogarPartidas(double temperatura) {
        List<Equipe> equipesDisponiveis = new ArrayList<>(Arrays.asList(equipes));
        Collections.shuffle(equipesDisponiveis); // Embaralha para criar confrontos aleatórios

        // Jogo 1: Equipe 0 vs Equipe 1
        simularPartida(equipesDisponiveis.get(0), equipesDisponiveis.get(1), temperatura);

        // Jogo 2: Equipe 2 vs Equipe 3
        simularPartida(equipesDisponiveis.get(2), equipesDisponiveis.get(3), temperatura);
    }

    /**
     * Simula uma única partida entre duas equipes.
     * Gera placares aleatórios influenciados pela temperatura e atualiza as estatísticas.
     */
    private void simularPartida(Equipe eq1, Equipe eq2, double temperatura) {
        // Lógica para gerar placares: mais quente, maior o potencial de gols.
        // O número máximo de gols é (temperatura / 5) + 1, com um teto de 10 para ser razoável.
        int maxGols = Math.min(10, (int) (temperatura / 5.0) + 2);

        int placar1 = random.nextInt(maxGols);
        int placar2 = random.nextInt(maxGols);

        // Atualiza estatísticas das equipes
        if (placar1 > placar2) {
            eq1.registrarVitoria(placar1, placar2);
            eq2.registrarDerrota(placar2, placar1);
        } else if (placar2 > placar1) {
            eq2.registrarVitoria(placar2, placar1);
            eq1.registrarDerrota(placar1, placar2);
        } else {
            eq1.registrarEmpate(placar1, placar2);
            eq2.registrarEmpate(placar2, placar1);
        }

        // Cria e armazena o registro do jogo
        Jogo novoJogo = new Jogo(eq1, eq2, placar1, placar2, temperatura);
        this.jogos.add(novoJogo);

        System.out.println(novoJogo);
    }

    /**
     * Verifica se a temporada terminou (3 semanas congelantes consecutivas).
     * @return true se a temporada terminou, false caso contrário.
     */
    public boolean temporadaEncerrada() {
        return semanasCongelantesConsecutivas >= 3;
    }

    /**
     * Imprime todas as estatísticas finais da temporada.
     */
    public void imprimirEstatisticasFinais() {
        System.out.println("\n=======================================================");
        System.out.println("O INVERNO CHEGOU! A TEMPORADA ESTÁ ENCERRADA.");
        System.out.println("=======================================================\n");

        // 1. Estatísticas das Equipes
        System.out.println("--- ESTATÍSTICAS FINAIS DAS EQUIPES ---");
        for (Equipe equipe : equipes) {
            System.out.println(equipe);
        }

        System.out.println("\n--- HISTÓRICO DE JOGOS DA TEMPORADA ---");
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo foi realizado nesta temporada.");
        } else {
            for (Jogo jogo : jogos) {
                System.out.println(jogo);
            }
        }

        // 3. Estatísticas de Temperatura
        System.out.println("\n--- ESTATÍSTICAS DE TEMPERATURA ---");
        if (contadorSemanasComJogos > 0) {
            double mediaTemperatura = somaTemperaturasJogos / contadorSemanasComJogos;
            System.out.printf("Temperatura mais quente da temporada: %.1f°C\n", temperaturaMaisQuente);
            System.out.printf("Temperatura média nos dias de jogo: %.1f°C\n", mediaTemperatura);
        } else {
            System.out.println("Nenhum jogo foi jogado, portanto não há estatísticas de temperatura.");
        }
        System.out.println("\n=======================================================");
    }
}
