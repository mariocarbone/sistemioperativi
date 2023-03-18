package AziendaAgricola;

import Casello.Casello;
import Casello.CaselloSem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AziendaAgricolaSem extends AziendaAgricola{


    private Semaphore mutex = new Semaphore(1);
    private Semaphore filaCassa = new Semaphore(1,true);
    private Semaphore filaMagazzino; //in base a quanti sacchetti ci sono
    private Semaphore magazzino = new Semaphore(0);
    private int prezzoSacchetto=3;


    public AziendaAgricolaSem(int sacchettiIniziali){
        super(sacchettiIniziali);
        filaMagazzino= new Semaphore(sacchettiIniziali,true);
    }

    public void paga(int numSacchetti) throws InterruptedException{
        filaCassa.acquire();
        System.out.println("🧑 "+Thread.currentThread().getId() + " è in fila alla cassa");
        mutex.acquire();
        incasso += numSacchetti*prezzoSacchetto;
        System.out.println("🧑 "+Thread.currentThread().getId() + " ha pagato " +numSacchetti+  " alla cassa");
        mutex.release();
        filaCassa.release();
    }

    @Override
    public void ritiraSacchetti() throws InterruptedException {
        filaMagazzino.acquire();
        mutex.acquire();
        sacchetti--;
        System.out.println("🧑 "+ Thread.currentThread().getId() + " ha prelevato 1 sacchetto dal magazzino" + " |📦:" +sacchetti);
        if(sacchetti==0){
            System.out.println("👨‍🌾: Sacchetti finiti");
            magazzino.release();
        }
        mutex.release();
    }

    public void caricaSacchetti() throws InterruptedException{
        magazzino.acquire();
        mutex.acquire();
        System.out.println("👷: Il magazziniere sta rifornendo il magazzino");
        caricamentoMagazziniere();
        sacchetti=sacchettIniziali;
        System.out.println("👨‍🌾: I sacchetti di nuovo disponibili sono: "+sacchetti);
        mutex.release();
        filaMagazzino.release(sacchettIniziali);

    }

    private void caricamentoMagazziniere() throws InterruptedException{
        TimeUnit.SECONDS.sleep(20); //dovrebbero essere 10 min
    }

    public static void main(String []args){
        AziendaAgricolaSem azienda = new AziendaAgricolaSem(10); //dovrebbe essere 200
        azienda.test(10);

    }

}
