package Esercitazione4.Es4.AAB;

import java.util.concurrent.Semaphore;

public class AAB {
//Definisci i semafori
private static Semaphore sem = new Semaphore(1);
//Definisci le variabili
//private static int ...;

    static class A extends Thread {
        public void run() {
            //try {
                //Inserisci le operazione
                //prima della stampa
                //sem.acquire(1);
                System.out.print("A");
                sem.release(1);
                //Inserisci le operazione
                //dopo della stampa
            //} catch (InterruptedException e) {System.out.println(e);}
        }
    }

    static class B extends Thread {
        public void run() {
            try {
                //Inserisci le operazione
                //prima della stampa
                sem.acquire(3);
                System.out.print("B");
                sem.release(1);
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