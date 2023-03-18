package BarMod;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BarModSem extends BarMod {

    /*
        protected static final int CASSA = 0, BANCONE = 1, FILE = 2;
        protected static final int[] MAX_PERSONE_FILA = {1,4};
        protected int [] numPostiLiberi = new int[FILE];
     */
    Semaphore mutex = new Semaphore(1);
    Semaphore[] file = new Semaphore[FILE];
    int[] personePerFila = new int[FILE];

    public BarModSem() {
        super();
        for (int i = 0; i < FILE; i++) {
            file[i] = new Semaphore(MAX_PERSONE_FILA[i], true);
            numPostiLiberi[i] = MAX_PERSONE_FILA[i];
        }
    }

    public int scegli() throws InterruptedException {

        int i = -1;
        mutex.acquire();
        if (numPostiLiberi[CASSA] > 0) {
            i = CASSA;
        } else if (numPostiLiberi[BANCONE] > 0) {
            i = BANCONE;
        } else if (personePerFila[CASSA] <= personePerFila[BANCONE]) {
            i = CASSA;
        } else {
            i = BANCONE;

        }
        mutex.release();
        return i;
    }

    public void inizia(int i) throws InterruptedException {

        mutex.acquire();
        personePerFila[i]++;
        mutex.release();

        System.out.println("🧑("+Thread.currentThread().getId() + ") è in fila" + ((i == CASSA) ? " alla CASSA" : " al BANCONE"));
        System.out.println("☕Posti liberi: " + Arrays.toString(numPostiLiberi) + " 🧍Fila: " + Arrays.toString(personePerFila));
        file[i].acquire();

        mutex.acquire();
        personePerFila[i]--;
        numPostiLiberi[i]--;

        System.out.println("🧑("+Thread.currentThread().getId() + ") è " + ((i == CASSA) ? "alla CASSA" : "al BANCONE"));
        System.out.println("☕Posti liberi: " + Arrays.toString(numPostiLiberi) + " 🧍Fila: " + Arrays.toString(personePerFila));

        mutex.release();
    }

    public void finisci(int i) throws InterruptedException {

        mutex.acquire();
        numPostiLiberi[i]++;

        file[i].release();
        System.out.println("🧑("+Thread.currentThread().getId() + ") lascia" + ((i == CASSA) ? " la CASSA" : " il BANCONE"));
        System.out.println("☕Posti liberi: " + Arrays.toString(numPostiLiberi) + " 🧍Fila: " + Arrays.toString(personePerFila));
        mutex.release();
    }


    public static void main(String[] args) throws InterruptedException {
        BarModSem bar = new BarModSem();
        bar.test(20);

    }

}
