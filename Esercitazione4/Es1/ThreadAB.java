package Esercitazione4.Es1;

public class ThreadAB extends Thread {

    private String msg;


    private Semafori41 semMutex;


    public ThreadAB(String msg) {
        this.msg = msg;
        this.semMutex = new Semafori41();
    }


    public void run() {
        semMutex.stampa(msg);
    }

}
