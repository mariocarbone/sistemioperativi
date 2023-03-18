package Casello;

public abstract class Casello {

    protected int nPorte;
    protected int tariffa;
    protected int incasso = 0;

    public Casello(int nPorte, int tariffa){
        this.nPorte=nPorte;
        this.tariffa = tariffa;
    }

    public abstract void scegliPorta(int nPorta) throws InterruptedException;

    public abstract void pagaEdEsci(int nPorta, int chilometriPercorsi) throws InterruptedException;

    public void Test(int numVeicoli) throws InterruptedException {
        Veicolo[] veicoli = new Veicolo[numVeicoli];
        System.out.println("Inizio il Test");
        for(int i=0;i<numVeicoli;i++){
            veicoli[i] = new Veicolo(this);
        }
        for(int i=0; i<numVeicoli; i++){
            veicoli[i].start();
        }
        for(int i=0; i<numVeicoli; i++){
            veicoli[i].join();
        }
        System.out.println("Incasso totale = "+incasso);
    }

}
