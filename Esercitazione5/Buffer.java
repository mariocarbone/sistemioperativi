package Esercitazione5;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public abstract class Buffer {
    protected int[] buffer;
    protected int in = 0;
    protected int out = 0;
    public Buffer(int dimensione) {
        buffer = new int[dimensione];
    }
    public abstract void put(int i) throws InterruptedException;
    public abstract int get() throws InterruptedException;
    public void test(int numProduttori, int numConsumatori) {
        for (int i = 0; i < numProduttori; i++) {
            new Thread(new Produttore(this)).start();
        }
        for (int i = 0; i < numConsumatori; i++) {
            new Thread(new Consumatore(this)).start();
        }
        while(true){
            System.out.println(Calendar.getInstance().getTime()+ " " +Arrays.toString(buffer));
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){System.out.println(e);};
        }
    }}
