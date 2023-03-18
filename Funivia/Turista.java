package Funivia;

public class Turista extends Thread {

    private Funivia funivia;
    private int tipo;

    public Turista(Funivia f, int t) {
        this.funivia = f;
        this.tipo = t;
    }

    public void run() {
        try {
            funivia.turistaSali(tipo);
            funivia.turistaScendi(tipo);
        } catch (InterruptedException e) {
        }

    }

    public int getTipo() {

        return tipo;
    }

    public void setTipo(int tipo) {

        this.tipo = tipo;
    }

    public String toString() {
        return this.getId() + " (" + this.getTipo() + ")";
    }

}

