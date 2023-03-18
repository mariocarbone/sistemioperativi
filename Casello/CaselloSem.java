package Casello;


import java.util.concurrent.Semaphore;

public class CaselloSem extends Casello {

    Semaphore mutex = new Semaphore(1);
    Semaphore[] porte;


    public CaselloSem(int nPorte, int tariffa) {
        super(nPorte, tariffa);
        porte = new Semaphore[nPorte];
        for(int i=0;i<nPorte;i++){
            porte[i]=new Semaphore(1);
        }
    }

    public void scegliPorta(int nPorta) throws InterruptedException {

        porte[nPorta].acquire();
        System.out.println(Thread.currentThread().getId() + " ho scelto la porta " + nPorta);
        //non rilascio, così altri processi non possono entrare
        // porte[nPorta].release();
    }

    public void pagaEdEsci(int nPorta, int chilometriPercorsi) throws InterruptedException {
        mutex.acquire();
        this.incasso += chilometriPercorsi * tariffa;
        //ho pagato
        System.out.println(Thread.currentThread().getId()+ " ho pagato ed esco");
        mutex.release();
        porte[nPorta].release();

    }

    public static void main(String[] args) {
        System.out.println("Avviato SemCasello");
        Casello casello = new CaselloSem(10,55);
        try {
            casello.Test(20);
        }catch (InterruptedException e){System.out.println(e);}
    }


}
