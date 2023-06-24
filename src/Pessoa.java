public class Pessoa {
    private String nome;
    private TeleSena[] telesenas;
    private double valorPremiacao;
    private boolean vencedor;

    public Pessoa(String nome) {
        this.nome = nome;
        this.vencedor = false;
    }

    public String getNome() {
        return nome;
    }

    public TeleSena[] getTelesenas() {
        return telesenas;
    }

    public void setTelesenas(TeleSena[] telesenas) {
        this.telesenas = telesenas;
    }

    public double getValorPremiacao() {
        return valorPremiacao;
    }

    public void setValorPremiacao(double valorPremiacao) {
        this.valorPremiacao = valorPremiacao;
    }

    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }
}
