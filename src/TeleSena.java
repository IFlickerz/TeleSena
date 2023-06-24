public class TeleSena {
    static double valorVenda = 10;
    private int[] conjuntoA, conjuntoB;

    public TeleSena(int[] conjuntoA, int[] conjuntoB) {
        this.conjuntoA = conjuntoA;
        this.conjuntoB = conjuntoB;
    }

    public int[] getConjuntoA() {
        return conjuntoA;
    }

    public int[] getConjuntoB() {
        return conjuntoB;
    }
}