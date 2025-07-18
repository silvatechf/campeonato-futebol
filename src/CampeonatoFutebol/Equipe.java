package CampeonatoFutebol;

public class Equipe {

    private String nome;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int golsMarcados;
    private int golsSofridos;

    public Equipe(String nome) {
        this.nome = nome;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos para atualizar estatísticas
    public void registrarVitoria(int golsMarcados, int golsSofridos) {
        this.vitorias++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
    }

    public void registrarDerrota(int golsMarcados, int golsSofridos) {
        this.derrotas++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
    }

    public void registrarEmpate(int golsMarcados, int golsSofridos) {
        this.empates++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
    }

    @Override
    public String toString() {
        return String.format("%-25s | V: %d | D: %d | E: %d | Gols Pró: %d | Gols Contra: %d",
                nome, vitorias, derrotas, empates, golsMarcados, golsSofridos);
    }
}