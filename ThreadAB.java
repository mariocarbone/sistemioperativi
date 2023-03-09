public class ThreadAB extends Thread{

    private String msg;
    
    public ThreadAB(String msg){
        this.msg=msg;
    }


    public void run(){
        Semafori41 sem = new Semafori41();
        sem.stampa(msg);

    }


}
