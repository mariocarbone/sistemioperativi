package Funivia;
/*
Si consideri una funivia che permette di spostare i turisti da un piccolo paese fino alla cima della montagna. La funivia può
essere occupata da 6 turisti che sono arrivati lì a piedi o da 3 turisti in bici (i tre posti rimanenti sono occupati dalle bici).
La funivia è guidata da un pilota che continuamente sale e scende dalla montagna. Il pilota una volta arrivato a valle fa
entrare nella funivia un gruppo di turisti a piedi oppure un gruppo di turisti in bici. Non potranno mai salire sia turisti a piedi
sia turisti in bici. Il pilota usa una politica round-robin: fa salire un gruppo di 6 turisti a piedi, poi un gruppo di 3 turisti in bici e
così via.
La funivia parte solo dopo aver raggiunto il pieno carico (o 6 turisti a piedi o 3 turisti in bici) impiegando 5 minuti per giungere
in cima. Il pilota, una volta arrivato in cima, lascia i turisti e ritorna con la funivia vuota a valle impiegando 2 minuti (i turisti
scenderanno a piedi o in bici dalla montagna a valle).*/

public abstract class Funivia {

    protected static final int NUM_TIPI_TURISTI =2;
    protected static final int TURISTA_P = 0, TURISTA_B =1;
    protected static final int[] MAX_TURISTI={6,3};
    protected static final int TEMPO_SALITA = 5; //minuti
    protected static final int TEMPO_DISCESA = 2; //minuti

    public abstract void pilotaStart() throws InterruptedException;
    //il pilota ha portato la funivia a valle e attende che salgano i turisti secondo l’ordine specificato
    //sopra. Quando la funivia è piena, il pilota blocca gli accessi e inizia il viaggio.

    public abstract void pilotaEnd() throws InterruptedException;
    //Il pilota è arrivato in cima, stampa l’ID dei turisti presenti nella funivia e il loro tipo e dopo permette
    //ai turisti di scendere dalla funivia. Subito dopo inizia il ritorno a valle.

    public abstract void turistaSali (int t) throws InterruptedException;
    // il turista di tipo t (0 turista a piedi) è pronto per salire in montagna. Il turista viene sospeso fin
    //quando non occupa un posto all’interno della funivia. I turisti in fila vengono risvegliati secondo un ordine causale.
    public abstract void turistaScendi (int t) throws InterruptedException;

    public void test() {

        final int TURISTI_A_PIEDI=18;
        final int TURISTI_IN_BICI=9;

        for(int i=0; i<TURISTI_A_PIEDI;i++) {

            new Thread(new Turista (this,0)).start();
        }

        for(int i=0; i<TURISTI_IN_BICI;i++) {

            new Thread(new Turista (this,1)).start();
        }

        Thread t= new Thread(new Pilota(this));
        t.setDaemon(true);
        t.start();

    }

}
