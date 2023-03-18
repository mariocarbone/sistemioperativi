package BarMod;

public abstract class BarMod {

    protected static final int CASSA = 0, BANCONE = 1, FILE = 2;
    protected static final int[] MAX_PERSONE_FILA = {1,4};
    protected int [] numPostiLiberi = new int[FILE];

    public BarMod(){
        for(int i=0;i<FILE;i++){
            numPostiLiberi[i]=MAX_PERSONE_FILA[i];
        }

    }

    public abstract int scegli() throws InterruptedException;

    public abstract void inizia(int i) throws InterruptedException;

    public abstract void finisci(int i) throws InterruptedException;

    public void test(int numPersone) throws InterruptedException{
        System.out.println("☕ Bar Aperto");
        Persona[] persone = new Persona[numPersone];
        for(int i=0;i<numPersone;i++){
            persone[i] = new Persona(this);
            persone[i].start();
        }
        for(int i=0;i<numPersone;i++){
            persone[i].join();
        }
        System.out.println("☕ Bar Chiuso");
    }

}
