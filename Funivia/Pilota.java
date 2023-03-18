package Funivia;

import java.sql.Time;
import java.util.concurrent.TimeUnit;


public class Pilota extends Thread {

    private Funivia funivia;
    private int nViaggi;

    public Pilota(Funivia f) {
        this.funivia = f;
    }

    public void run() {
        try {
            while (true) {
                funivia.pilotaStart();
                attendi(5);
                funivia.pilotaEnd();
                attendi(2);
            }
        } catch (InterruptedException e) {
        }
    }

    public void attendi(int tempo_attesa) throws InterruptedException {

        TimeUnit.SECONDS.sleep(tempo_attesa);
    }


}