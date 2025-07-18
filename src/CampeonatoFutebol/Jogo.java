
package CampeonatoFutebol;

public class Jogo {

    private static int proximoId = 1; // Contador estático para IDs únicos

    private final int id;
    private final Equipe equipe1;
    private final Equipe equipe2;
    private final int placarEquipe1;
    private final int placarEquipe2;
    private final double temperatura;

    public Jogo(Equipe equipe1, Equipe equipe2, int placarEquipe1, int placarEquipe2, double temperatura) {
        this.id = proximoId++;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.placarEquipe1 = placarEquipe1;
        this.placarEquipe2 = placarEquipe2;
        this.temperatura = temperatura;
    }

    // Getters (Setters não são necessários, pois um jogo não é modificado após ocorrer)
    public int getId() {
        return id;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public int getPlacarEquipe1() {
        return placarEquipe1;
    }

    public int getPlacarEquipe2() {
        return placarEquipe2;
    }

    public double getTemperatura() {
        return temperatura;
    }

    @Override
    public String toString() {
        return String.format("Jogo %02d: %s %d vs %d %s (Temperatura: %.1f°C)",
                id, equipe1.getNome(), placarEquipe1, placarEquipe2, equipe2.getNome(), temperatura);
    }
}
