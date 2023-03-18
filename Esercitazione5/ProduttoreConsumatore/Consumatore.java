package Esercitazione5.ProduttoreConsumatore;

import Esercitazione5.ProduttoreConsumatore.Buffer;

import java.util.concurrent.TimeUnit;

public class Consumatore implements Runnable {
    private Buffer buffer;
    public Consumatore(Buffer b) { buffer = b; }
    public void run() {
        try {
            while (true) {
                int i = buffer.get();
                consuma(i);
            }
        } catch (InterruptedException e) {}
    }
    private void consuma(int i) throws InterruptedException {
        System.out.println("Consumatore " + Thread.currentThread().getId()+ ": Ho consumato "+ i);
        TimeUnit.SECONDS.sleep(i);
    }
}