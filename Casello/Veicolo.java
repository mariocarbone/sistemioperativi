package Casello;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Veicolo extends Thread{

    Random random = new Random();
    int maxChilometri =10;
    int minChilometri = 5;
    int sPerChilometri = 4;
    int maxTempoPagamento = 6;
    int minTempoPagamento = 4;
    Casello casello;

    public Veicolo(Casello casello){
        this.casello=casello;
    }

    public void run(){

        //percorro un tratto di autostrada
        try {

            int kmPercorsi = random.nextInt(maxChilometri-minChilometri)+minChilometri;
            attesaPercorso(kmPercorsi);

            int nPorta = random.nextInt(casello.nPorte);
            casello.scegliPorta(nPorta);

            attesaPagamento();

            casello.pagaEdEsci(nPorta,kmPercorsi);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    void attesaPercorso(int nChilometri) throws InterruptedException{
        TimeUnit.SECONDS.sleep(sPerChilometri*nChilometri);
    }

    void attesaPagamento() throws InterruptedException{
        int sec = random.nextInt(maxTempoPagamento-minTempoPagamento)+minTempoPagamento;
        TimeUnit.SECONDS.sleep(sec);
    }


}
