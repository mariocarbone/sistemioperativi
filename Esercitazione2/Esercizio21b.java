public class Esercizio21b extends Thread {

    public Esercizio21b(String name) {
        super(name);
        }

    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.print(getName());
        }
    }

    public static void main(String args[]) {
        new Esercizio21b("0").run();
        new Esercizio21b("1").run();
    }
}
