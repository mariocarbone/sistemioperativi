package Esercitazione4.Es4;

import java.util.concurrent.Semaphore;

/*

Si realizzi un’applicazione multithreaded composta da 2 tipologie di Thread: A, B.

Ogni Thread della classe A stampa a video, una sola volta, la stringa «A» e poi termina la sua esecuzione.
Lo stesso fa un Thread della classe B stampando la stringa «B».

Il main inizializza ogni secondo un thread di tipo A e un thread di tipo B. L’output che si genera è:
AB AB AB …

Sincronizzare i thread A e B, sapendo che condividono dei semafori e delle variabili di supporto.

*/
public class Semafori44 {

    private Semaphore sync = new Semaphore(0);

    public Semafori44() {
        while (true) {
            new ThreadA().start();
            new ThreadB().start();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Semafori Esercizio 2 - Sincronizzazione");
        new Semafori44();
    }

    public class ThreadA extends Thread {
        public void run() {
            System.out.print("A");
            sync.release();
        }
    }

    public class ThreadB extends Thread {
        public void run() {
            try {
                sync.acquire();
                System.out.print("B");
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
    }


}
