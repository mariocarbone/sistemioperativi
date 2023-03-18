package Esercitazione5.BarbiereAddormentato;

public class Cliente extends Thread {
    private Sala sala; private int ID;
    public Cliente(Sala s, int i) {
        sala = s; ID = i;
    }
    public void run() {
        try {
            System.out.format("Il cliente %d vuole tagliarsi i capelli%n", ID);
            boolean ret = sala.attendiTaglio();
            if (ret)
                System.out.format("Il cliente %d è riuscito a tagliarsi i capelli%n", ID);
            else
                System.out.format("Il cliente %d abbandona la sala%n", ID);
        } catch (InterruptedException e) {System.out.println(e);}
    }
    public int getID(){return ID;}
    public int hashCode() {return 0;}
    public boolean equals(Object obj) {return true;}
}