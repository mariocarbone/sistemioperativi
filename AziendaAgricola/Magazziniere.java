package AziendaAgricola;

public class Magazziniere extends Thread {

    private AziendaAgricola aziendaAgricola;

    public Magazziniere(AziendaAgricola aziendaAgricola) {
        this.aziendaAgricola = aziendaAgricola;
    }

    public void run() {
        while (true) {
            try {
                aziendaAgricola.caricaSacchetti();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

}
