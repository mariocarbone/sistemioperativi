import java.util.concurrent.Semaphore;

class Semafori41Sinc{

private Semaphore sync = new Semaphore(0);


public Semafori41Sinc(){
}


public static void main(String [] args){

    System.out.println("Semafori Esercizio 2");
    ThreadAB threadA = new ThreadAB("A");
    ThreadAB threadB = new ThreadAB("B");
    threadA.start();
    threadB.start();

}

public void stampa(String msg){
    try{
    sync.acquire();
    System.out.print("Il thread "+Thread.currentThread().getId() + " stampa: ");
    System.out.println(msg);
    sync.release();
    }catch(InterruptedException e){
        System.out.println(e);
    }
}

}//Classe
























