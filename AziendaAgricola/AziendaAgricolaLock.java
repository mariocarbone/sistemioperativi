package AziendaAgricola;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaLock extends AziendaAgricola{

    Lock lock = new ReentrantLock();
    Condition paga = lock.newCondition();
    Condition ritira = lock.newCondition();
    Condition rifornire = lock.newCondition();
    LinkedList<Thread> filaCassa = new LinkedList<>();
    LinkedList<Thread> filaMagazzino = new LinkedList<>();
    int costoSacchetto = 3;
    private int sacchettiIniziali=0;


    public AziendaAgricolaLock(int numSacchetti){
        super(numSacchetti);
        this.sacchettiIniziali=numSacchetti;
    }

    public void paga(int numSacchetti) throws InterruptedException{
        lock.lock();
        try{
            filaCassa.add(Thread.currentThread());
            while(!possoPagare()){
                paga.await();
            }
            filaCassa.removeFirst();
            incasso += numSacchetti*costoSacchetto;
            System.out.println("🧑 "+Thread.currentThread().getId() + " ha pagato " +numSacchetti*costoSacchetto+  " alla cassa");
            paga.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void ritiraSacchetti() throws InterruptedException{
        lock.lock();
        try{
            filaMagazzino.add(Thread.currentThread());
            while(!possoRitirare()){
                ritira.await();
            }
            filaMagazzino.removeFirst();
            sacchetti --;
            System.out.println("🧑 "+ Thread.currentThread().getId() + " ha prelevato 1 sacchetto dal magazzino" + " |📦:" +sacchetti);
            if(sacchetti == 0){
                rifornire.signalAll();
            }
            ritira.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void caricaSacchetti() throws InterruptedException{
        lock.lock();
        try{
            while(!possoRifornire()){
                rifornire.await();
            }
            sacchetti = sacchettiIniziali;
            System.out.println("👨‍🌾: I sacchetti di nuovo disponibili sono: "+sacchetti);
            ritira.signalAll();
        }finally {
            lock.unlock();
        }

    } //per il magazziniere

     public boolean possoPagare(){
        return Thread.currentThread()==filaCassa.getFirst();
    }

    public boolean possoRitirare(){
        return Thread.currentThread()==filaMagazzino.getFirst() && sacchetti > 0;
    }

    public boolean possoRifornire(){
        return sacchetti == 0;
    }

    public static void main(String []args){
        AziendaAgricolaLock azienda = new AziendaAgricolaLock(10); //dovrebbe essere 200
        azienda.test(10);

    }

}
