public class Principal {
    public static void main(String[]args) {
        ControleTeleSena controleTeleSena = new ControleTeleSena();
        Pessoa[] pessoas = new Pessoa[20];

        System.out.println("Bem-vindo a TeleSena!\n");

        for (int i = 0; i < 20; i++) {
            pessoas[i] = new Pessoa("Pessoa " + (i + 1));
        }

        for (Pessoa pessoa : pessoas) {
            controleTeleSena.vendeTeleSena(pessoa, (int) (Math.random() * 15 + 1));
        }

    }
}
