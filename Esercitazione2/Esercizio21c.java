public class Esercizio21c extends Thread{

public Esercizio21c(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.print(getName());
        }
    }

    public static void main(String args[]) {
        new Esercizio21c("0").start();
        new Esercizio21c("1").start();
    }

}
