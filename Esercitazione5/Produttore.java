package Esercitazione5;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Produttore implements Runnable {
    private static final int MAX_RANDOM = 10;
    private static final int MIN_TEMPO_PRODUZIONE = 1000;
    private static final int MAX_TEMPO_PRODUZIONE = 5000;
    private Random random = new Random();
    private Buffer buffer;
    public Produttore(Buffer b) { buffer = b; }
    public void run() {
        try {
            while (true) {
                int i = produci();
                buffer.put(i);
            }
        } catch (InterruptedException e) {}
    }
    private int produci() throws InterruptedException {
        System.out.println("Produttore " + Thread.currentThread().getId()+ ": Ho prodotto ");

        attendi(MIN_TEMPO_PRODUZIONE, MAX_TEMPO_PRODUZIONE);
        return random.nextInt(MAX_RANDOM);
    }

    private void attendi(int minTempo, int maxTempo) throws InterruptedException {
        int tempo =  (int) ((Math.random() * (maxTempo - minTempo)) + minTempo);
        TimeUnit.SECONDS.sleep(tempo);
    }

}
