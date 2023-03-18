package Esercitazione4.Es4.CronometroAd;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

/*
Modificare l’esempio della lezione 1 «Thread Cronometro» e definire la nuova classe CronometroAd.
Nella nuova classe, l’utente interagisce con il cronometro tramite i seguenti input (preme un tasto e invia il comando con INVIO):

- A, avvia (o ri-avvia) il cronometro
- F, ferma il cronometro. L’utente può ripremere il tasto «A» per far ripartire il cronometro dal tempo precedente.
- R, resetta il cronometro e azzera il tempo.
- E, esce dall’applicazione bloccando il cronometro.

Usare i semafori di Java per implementare la classe CronometroAd.
 */


public class CronometroAd extends Thread {

    Semaphore sem = new Semaphore(1);
    private int numSecondi = 1;

    public void run() {
        System.out.println("🕛: Partito");
        while (!isInterrupted()) {
            try {
                sem.acquire();
                System.out.println("🕛: " + numSecondi);
                numSecondi++;
                sem.release();
                Thread.sleep(1000);
            } catch (Exception e) {
                break;
            }
        }
    }


    public void reset() {
        System.out.println("🕛: RESET Avvenuto");
        this.numSecondi = 1;
    }

    public void ferma() throws InterruptedException {
        sem.acquire();
        System.out.println("🕛: Cronometro Fermo");
    }

    public void riavvia() throws InterruptedException {
        sem.release();
        System.out.println("🕛: Cronometro Ripartito");
    }

    public static void main(String[] args) throws InterruptedException {
        CronometroAd cronometro = new CronometroAd();
        System.out.println("🕛: Inserisci A per avviare il cronometro");
        Scanner in = new Scanner(System.in);
        boolean avviato = false;
        while (true) {
            String comando = in.nextLine();
            if (comando.equals("A")) {
                if (avviato) {
                    cronometro.riavvia();
                } else {
                    cronometro.start();
                    avviato = true;
                }
            } else if (comando.equals("F")) cronometro.ferma();
            else if (comando.equals("R")) cronometro.reset();
            else if (comando.equals("E")) {
                cronometro.interrupt();
                System.out.println("🕛: Programma terminato");
                break;
            }
        }
    }

}

