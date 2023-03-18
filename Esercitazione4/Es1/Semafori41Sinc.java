package Esercitazione4.Es1;

import java.util.concurrent.Semaphore;


public class Semafori41Sinc {

    private Semaphore sync = new Semaphore(0);

    /*
     * Due Thread A e B devono poter stampare
     * mediante un semaforo che assicura la sincronizzazione
     */

    public static void main(String[] args) {

        System.out.println("Semafori Esercizio 2 - Sincronizzazione");
        new Semafori41Sinc();
    }

    public Semafori41Sinc() {
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

    class ThreadA extends Thread {
        public void run() {
            System.out.println(this.getName()+ ": A");
            sync.release();
        }
    }

    class ThreadB extends Thread {
        public void run() {
            try {
                sync.acquire();
                System.out.println(this.getName()+ ": B");
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
    }


}//Classe
























