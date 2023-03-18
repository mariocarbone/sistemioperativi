package Muratori;

import java.util.concurrent.TimeUnit;

public class Muratore extends Thread {

    private int tipo;
    private Casa casa;

    public Muratore(Casa casa, int tipo) {
        this.tipo = tipo;
        this.casa = casa;
    }

    public void run() {
        try {
            while (!casa.inizia(tipo)) {
                prepara();
                lavora(tipo);
                casa.termina(tipo);
                riposa();
            }


        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void lavora(int tipo) throws InterruptedException {
        if (tipo == 0) {
            TimeUnit.MILLISECONDS.sleep(700);
        }
        else{
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public void prepara() throws InterruptedException{
        TimeUnit.SECONDS.sleep(1);
    }
    public void riposa() throws InterruptedException{
        TimeUnit.SECONDS.sleep(5);
    }

}
