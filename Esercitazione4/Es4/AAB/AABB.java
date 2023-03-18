package Esercitazione4.Es4.AAB;

import java.util.concurrent.Semaphore;

public class AABB {
//Definisci i semafori
private static Semaphore semA = new Semaphore(2);
private static Semaphore semB = new Semaphore(1);
//Definisci le variabili
//private static int ...;

    static class A extends Thread {
        public void run() {
            try {
                //Inserisci le operazione
                //prima della stampa
                semA.acquire(1);
                System.out.print("A");
                semB.release(1);
                //Inserisci le operazione
                //dopo della stampa
            } catch (InterruptedException e) {System.out.println(e);}
        }
    }

    static class B extends Thread {
        public void run() {
            try {
                //Inserisci le operazione
                //prima della stampa
                semB.acquire(2);
                System.out.print("B");
                semA.release(2);
                //Inserisci le operazione
                //dopo della stampa
            } catch (InterruptedException e) {System.out.println(e);}
        }
    }

    public static void main(String[] args) {
        while (true) {
            new A().start();
            new B().start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {System.out.println(e);}
        }

    }
}