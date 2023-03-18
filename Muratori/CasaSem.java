package Muratori;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class CasaSem extends Casa {

    private int turno = -1;
    private int[] pareti = new int[4];
    private int pareteWork = 0;
    private boolean finito = false;
    private int N;
    Semaphore mutex = new Semaphore(1);
    Semaphore mattoni;
    Semaphore cemento;


    public CasaSem(int N) {
        super(N);
        this.N = N;
        mattoni = new Semaphore(0, true);
        cemento = new Semaphore(1, true);

    }

    public boolean inizia(int t) throws InterruptedException {

        mutex.acquire();
        turno++;
        mutex.release();
        if (!finito) {
            if(t==0){
                cemento.acquire();
                System.out.println("Muratore"+((t==0)? " cemento ": " mattoni ") +Thread.currentThread().getId() + " inizia a lavorare");
            }
            else{
                mattoni.acquire();
                System.out.println("Muratore"+((t==0)? " cemento ": " mattoni ") +Thread.currentThread().getId() + " inizia a lavorare");
                mutex.acquire();
                pareti[pareteWork]++;
                mutex.release();
            }
        }
        System.out.print(" "+Arrays.toString(pareti)+" ");
        /* sospende il muratore fino a quando non è disponibile il lavoro di tipo t,
        restituisce false se non ci sono più lavori da effettuare, true altrimenti; */
        return finito;
    }


    public void termina(int t) throws InterruptedException {

        // permette al muratore di comunicare che ha terminato il proprio lavoro.
        if (pareti[pareteWork] < N) {
            if (t == 0) {
                System.out.println(Thread.currentThread().getId() + " ha terminato il suo turno");
                mattoni.release();
            } else if (t == 1) {
                System.out.println(Thread.currentThread().getId() + " ha terminato il suo turno");
                cemento.release();
            }
        }
        mutex.acquire();
        if (pareti[pareteWork] == N && pareteWork < 4) {
            pareteWork++;
            cemento.release();
        }
        else if(pareti[pareteWork] == N && pareteWork == 4) {
            finito = true;
        }
        mutex.release();
    }


    public static void main(String[] args) throws InterruptedException {
        Casa casa = new CasaSem(20);
        casa.test();
    }

}
