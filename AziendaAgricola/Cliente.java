package AziendaAgricola;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread {


    /*
  • Decide quanti sacchi di terriccio acquistare (un numero random compreso tra 1 e 10);
• Si presenta in cassa e paga i sacchetti (ogni sacchetto costa 3€);
• Va in magazzino e ritira i sacchetti da spostare in auto. Ogni cliente ritira i sacchi di terriccio uno alla volta, spendendo 1 minuto per ogni spostamento.
Si noti che i sacchi di terriccio presenti in magazzino non sono illimitati.
Inizialmente sono presenti 200 sacchi di terriccio. Ogni volta che i sacchi si esauriscono un addetto magazziniere li riporta al valore iniziale
impiegando per questa operazione 10 minuti. Sia davanti la cassa sia davanti al magazzino si possono formare delle code
 in quanto il pagamento e il ritiro dei sacchi avviene in maniera FIFO.
*/

    private Random random = new Random();
    private AziendaAgricola aziendaAgricola;
    private int sacchettiMax = 10;
    private int sacchettiMin = 1;
    private int sacchettiAcquistati = 0;
    private int numeroSacchiDaPrelevare = 0;

    private int id;

    public Cliente(AziendaAgricola aziendaAgricola, int id) {
        this.aziendaAgricola = aziendaAgricola;
        this.id = id;
    }

    public void run() {

        int numSacchetti = random.nextInt(sacchettiMax - sacchettiMin) + sacchettiMin;
        try {
            aziendaAgricola.paga(numSacchetti);
            sacchettiAcquistati = numSacchetti;
            numeroSacchiDaPrelevare = numSacchetti;

            while (numeroSacchiDaPrelevare > 0) {
                aziendaAgricola.ritiraSacchetti();
                numeroSacchiDaPrelevare--;
                caricaInAuto();
            }
            System.out.println("🧑 "+this.getId() + ": Ho finito di prelevare i sacchetti");
        } catch (InterruptedException e) {
            System.out.println(e);
        }


    }

    void caricaInAuto() throws InterruptedException {
        TimeUnit.SECONDS.sleep(6);//in realtà sarebbe un minuto
    }

}
