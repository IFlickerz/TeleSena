import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ControleTeleSena {
    private int quantVendida, quantVencedores = 0;
    private Pessoa[] pessoas = new Pessoa[20];
    private ArrayList<Integer> resultados = new ArrayList<>();

    public ControleTeleSena() { }

    public void vendeTeleSena(Pessoa pessoa, int quantasComprar) {
        if (quantasComprar <= 15) {
            if (quantVendida + quantasComprar > 300) {
                System.out.println("ERRO! Só restam " + (300 - quantVendida) + " Tele Senas para compra!");
            } else {
                int[] conjuntoA, conjuntoB;
                TeleSena teleSena;
                TeleSena[] teleSenas = new TeleSena[15];

                // Constrói TeleSenas para a pessoa
                for (int i = 0; i < quantasComprar; i++) {
                    conjuntoA = sortearNumeros();
                    conjuntoB = sortearNumeros();

                    teleSena = new TeleSena(conjuntoA, conjuntoB);

                    teleSenas[i] = teleSena;
                }
                pessoa.setTelesenas(teleSenas); // Atribui a TeleSena àquela pessoa

                quantVendida += quantasComprar;

                // Insere a nova pessoa no Array de compradores da TeleSena
                for (int i = 0; i < pessoas.length; i++) {
                    if (pessoas[i] == null) {
                        pessoas[i] = pessoa;
                        break;
                    }
                }
            }
        } else {
            System.out.println("ERRO! A quantidade máxima para compra é de 15 TeleSenas!");
        }

        // Realiza o sorteio da TeleSena
        if (pessoas[19] != null) {
            int numero;

            // Gera uma Stream do Array resultado do "sortearNumeros()", converte pra primitivo (Integer) usando o "boxed()" e usa o "collect()" pra gerar um ArrayList novo
            resultados = IntStream.of(sortearNumeros()).boxed().collect(Collectors.toCollection(ArrayList::new));

            // Enquanto não houver nenhum ganhador, novos números são gerados
            while (!verificaVencedores()) {
                numero = (int) (Math.random() * 60 + 1);

                // resultados.stream().mapToInt(i -> i).toArray() ===> Gera uma Stream do ArrayList, converte gerando uma Stream nova agora de int com "mapToInt()" e usa o "toArray()" para gerar um Array novo
                while (contemNumero(resultados.stream().mapToInt(i -> i).toArray(), numero)) {
                    numero = (int) (Math.random() * 60 + 1);
                }

                resultados.add(numero);
            }

            calculaPremios();
            exibeInformacoes();
        }
    }

    // Gera os números para os conjuntos
    public int[] sortearNumeros() {
        int[] array = new int[25];
        int numero;
        for (int i = 0; i < array.length; i++) {
            numero = (int) (Math.random() * 60 + 1);

            while (contemNumero(array, numero)) {
                numero = (int) (Math.random() * 60 + 1);
            }
            array[i] = numero;
        }
        return array;
    }

    // Dá um loop no Array para verificar se o número a ser inserido já foi repetido, se sim, retorna true
    public static boolean contemNumero(int[] array, int numero) {
        for (int numeroAtual : array) {
            if (numero == numeroAtual) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaVencedores() {
        boolean haveVencedor = false;
        int[] conjuntoA, conjuntoB;
        int contA, contB;

        for (Pessoa pessoa : pessoas) {

            for (TeleSena teleSena : pessoa.getTelesenas()) {

                if (teleSena != null) {
                    conjuntoA = teleSena.getConjuntoA();
                    conjuntoB = teleSena.getConjuntoB();
                    contA = 0;
                    contB = 0;

                    for (Integer resultado : resultados) {

                        for (int index : conjuntoA) {
                            if (resultado == index) contA++;
                        }

                        for (int index : conjuntoB) {
                            if (resultado == index) contB++;
                        }
                    }

                    if (contA >= 25 || contB >= 25) {
                        pessoa.setVencedor(true);
                        quantVencedores++;
                        haveVencedor = true;
                        break;
                    }
                }
            }
        }

        return haveVencedor;
    }

    public void calculaPremios() {
        double valorTotal = quantVendida * TeleSena.valorVenda * 0.8;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.isVencedor()) {
                pessoa.setValorPremiacao(valorTotal/quantVencedores);
            }
        }
    }

    public void exibeInformacoes() {
        double valorPremiacao = 0, valorVendida;

        System.out.println("E os números sorteados são:\n");
        for (Integer resultado : resultados) {
            try {
                Thread.sleep(1000);
            } catch (Exception ignored){}

            System.out.print(resultado + " ");
        }

        System.out.println("\n\nForam vendidas " + quantVendida + " TeleSenas, gerando um total de " + quantVencedores + " ganhador(es).\n");

        if (quantVencedores == 1) {
            System.out.println("Por incrível que pareça, só tivemos um ganhador essa vez, que foi: ");
        } else {
            System.out.println("Os ganhadores dessa vez foram: ");
        }

        for (Pessoa pessoa : pessoas) {
            if (pessoa.isVencedor()) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored){}

                System.out.println(pessoa.getNome() + "!!!");
                valorPremiacao = pessoa.getValorPremiacao();
            }
        }

        if (quantVencedores == 1) {
            System.out.println("\nO nosso único sortudo levou sozinho para casa a quantia impressionante de: R$" + String.format("%.2f", valorPremiacao) + "!");
        } else {
            System.out.println("\nCada um levou para casa a impressionante quantia de: R$" + String.format("%.2f", valorPremiacao) + "!");
        }
        valorVendida = (quantVendida * TeleSena.valorVenda);

        System.out.println("\nAs vendas dessa TeleSena alcançaram o valor total de: R$" + String.format("%.2f", valorVendida) + "!");

        System.out.println("\nO Silvio Santos lucrou um total de: R$" + String.format("%.2f", valorVendida * 0.2) + " (Para ele dar de gorjeta depois!)");
    }
}
