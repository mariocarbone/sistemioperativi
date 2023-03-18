package BarMod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread{

    private BarMod barMod;
    private static final int[] MIN_ATTESA = {5,20};
    private static final int[] MAX_ATTESA = {10,40};
    private Random random = new Random();

    public Persona(BarMod barMod){
        this.barMod=barMod;
    }

    public void run(){

        try{
        attendi(10,0);

        int scelta = -1;

            scelta=barMod.scegli();
            barMod.inizia(scelta);
            attendi(MAX_ATTESA[scelta],MIN_ATTESA[scelta]);
            barMod.finisci(scelta);

            int scelta2 = (scelta == 0)? barMod.BANCONE : barMod.CASSA;
            barMod.inizia(scelta2);
            attendi(MAX_ATTESA[scelta2],MIN_ATTESA[scelta2]);
            barMod.finisci(scelta2);
            System.out.println("🧑("+this.getId() + ") sto uscendo dal Bar");

        }catch (InterruptedException e){
            System.out.println(e);
        }

    }

    public void attendi(int max, int min) throws InterruptedException {
        int value = random.nextInt(max-min)+min;
        TimeUnit.SECONDS.sleep(value);
    }

}
