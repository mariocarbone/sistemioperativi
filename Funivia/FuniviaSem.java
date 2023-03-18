package Funivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FuniviaSem extends Funivia {


    private int postiLiberi = 0;
    Semaphore mutex = new Semaphore(1);
    protected Semaphore[] possoSalire = new Semaphore[NUM_TIPI_TURISTI];
    protected Semaphore[] possoScendere = new Semaphore[NUM_TIPI_TURISTI];
    protected Semaphore pilotaPossoPartire = new Semaphore(0);
    protected Semaphore pilotaPossoFinire = new Semaphore(0);

    protected int numViaggio = -1;

    ArrayList<Long> passeggeri = new ArrayList<>();


    public FuniviaSem() {
        super();
        for (int i = 0; i < NUM_TIPI_TURISTI; i++) {
            possoSalire[i] = new Semaphore(0);
            possoScendere[i] = new Semaphore(0);
        }

    }

    public void pilotaStart() throws InterruptedException {

        //il pilota ha portato la funivia a valle e attende che salgano i turisti secondo l’ordine specificato
        //sopra. Quando la funivia è piena, il pilota blocca gli accessi e inizia il viaggio.

        //mutex.acquire();


        numViaggio++;
        System.out.println("PILOTA START: Sono a Valle - Pronto per il viaggio: "+ numViaggio);

        if (numViaggio % 2 == 0) {
            possoSalire[TURISTA_P].release(6);
        } else {
            possoSalire[TURISTA_B].release(3);
        }
        if(postiLiberi==0) {
            pilotaPossoPartire.acquire(6);

        }//mutex.release();
        //pilotaPossoPartire.acquire();
    }


    public void pilotaEnd() throws InterruptedException {

        System.out.println("PILOTA STOP: Viaggio: "+ numViaggio);

        for (int i = 0; i < passeggeri.size(); i++) {
            System.out.println("Il turista " + passeggeri.get(i)+" di tipo" + ((numViaggio % 2 == 0)? " a piedi" : " in bici" )+ " sta scendendo");
        }

        mutex.acquire();
        if (numViaggio % 2 == 0) {
            possoScendere[TURISTA_P].release(6);
        } else {
            possoScendere[TURISTA_B].release(3);
        }
        passeggeri.clear();
        mutex.release();
    }
    //Il pilota è arrivato in cima, stampa l’ID dei turisti presenti nella funivia e il loro tipo e dopo permette
    //ai turisti di scendere dalla funivia. Subito dopo inizia il ritorno a valle.

    public void turistaSali(int t) throws InterruptedException {
        //il turista di tipo t (0 turista a piedi, 1 turista in bici) è pronto per salire in montagna. Il turista
        //viene sospeso fin quando non occupa un posto all’interno della funivia. I turisti in fila vengono risvegliati secondo un
        //ordine causale.
        possoSalire[t].acquire();
        mutex.acquire();
        if (t == 0) {
            postiLiberi--;
            pilotaPossoPartire.release();
        } else {
            postiLiberi = postiLiberi - 2;
            pilotaPossoPartire.release(2);

        }
        passeggeri.add(Thread.currentThread().getId());
        System.out.print("Cabina ("+numViaggio+ ") ");
        System.out.println(passeggeri);
        mutex.release();

    }

    public void turistaScendi(int t) throws InterruptedException {

        //permette al turista di tipo t di scendere dalla funivia

        possoScendere[t].acquire();
        mutex.acquire();
        if (t == 0) {
            postiLiberi++;
        } else {
            postiLiberi = postiLiberi + 2;
        }
        System.out.println(Thread.currentThread().getId()+ " sono sceso");
        mutex.release();

        //Ritorno a valle

        if (postiLiberi == 6) {
            pilotaPossoFinire.release();
        }
    }

    public static void main(String[] args) {
        FuniviaSem f = new FuniviaSem();
        f.test();
    }



}
