import java.util.concurrent.Semaphore;

class Semafori41{

private Semaphore mutex = new Semaphore(1);


public Semafori41(){
}


public static void main(String [] args){

    System.out.println("Semafori Esercizio 1");
    ThreadAB threadA = new ThreadAB("A");
    ThreadAB threadB = new ThreadAB("B");
    threadA.start();
    threadB.start();

}

public void stampa(String msg){
    try{
    mutex.acquire();
    System.out.print("Il thread "+Thread.currentThread().getId() + " stampa: ");
    System.out.println(msg);
    mutex.release();
    }catch(InterruptedException e){
        System.out.println(e);
    }
}

}//Classe
























