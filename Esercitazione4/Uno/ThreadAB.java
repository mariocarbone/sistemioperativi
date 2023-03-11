package Esercitazione4.Uno;

import Esercitazione4.Uno.Semafori41;

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
