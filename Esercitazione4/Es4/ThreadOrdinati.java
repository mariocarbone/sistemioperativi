package Esercitazione4.Es4;

import java.util.concurrent.TimeUnit;

public class ThreadOrdinati {
    //Definisci semafori e variabili
    private static int N = 20;
    public static void main(String[] args) {
        Atleta[] atleti = new Atleta[N];
        for (int i = 0; i < N; i++) {
            atleti[i] = new Atleta(i); atleti[i].start();
        }
    }
    public static class Atleta extends Thread{
        private int numMaglia;
        public Atleta(int nMaglia) {
            this.numMaglia = nMaglia;
        }
        public void run() {
            try {
//Definisci le operazioni prima della stampa
                System.out.println(numMaglia);
                TimeUnit.SECONDS.sleep(2);
//Definisci le operazioni dopo della stampa
            } catch (InterruptedException e) {System.out.println(e);}
        }//run
    }//Atleta
}//ThreadOrdinati