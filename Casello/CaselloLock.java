package Casello;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaselloLock extends Casello{

    Lock lock;
    Condition[] porte;
    LinkedList<Thread>[] codaPorta;

    public CaselloLock(int nPorte, int tariffa){
        super(nPorte,tariffa);
        lock = new ReentrantLock();
        porte = new Condition[nPorte];
        codaPorta = new LinkedList[nPorte];

        for(int i=0;i<nPorte;i++){
            porte[i] = lock.newCondition();
            codaPorta[i] = new LinkedList<Thread>();
        }
    }

    public void scegliPorta(int nPorta) throws InterruptedException{
        try{
            lock.lock();
            codaPorta[nPorta].add(Thread.currentThread());
            while(!possoEntrare(nPorta)) {
                porte[nPorta].await();
            }
            System.out.println(Thread.currentThread().getId()+ " sono entrato alla porta "+ nPorta);
        }finally {
            lock.unlock();
        }

    }

    public void pagaEdEsci(int nPorta, int chilometriPercorsi) throws InterruptedException{
        try{
            lock.lock();
            codaPorta[nPorta].removeFirst();
            incasso += chilometriPercorsi*tariffa;
            System.out.println(Thread.currentThread().getId()+ " ho pagato alla porta "+ nPorta+ " e sto uscendo");
            porte[nPorta].signalAll();
        }finally {
            lock.unlock();
        }
    }

    boolean possoEntrare(int nPorta){
        return Thread.currentThread() == codaPorta[nPorta].getFirst();
    }

    public static void main(String[] args) {
        System.out.println("Avviato SemCasello");
        Casello casello = new CaselloLock(10,55);
        try {
            casello.Test(20);
        }catch (InterruptedException e){System.out.println(e);}
    }


}

