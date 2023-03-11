public class Esercizio21a extends Thread {

    private static String nome = "Ciao";

    public static void main(String[] args) {
        new Esercizio21a().prova();
        System.out.println(nome);
    }

    public void prova() {
        start();
        nome = nome + " mondo";
    }

    public void run() {
        nome += " 1 2 3";
    }
}