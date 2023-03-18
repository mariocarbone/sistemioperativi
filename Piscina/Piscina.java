package Piscina;

public abstract class Piscina {

    /*
    Delle persone frequentano una piscina per esercitarsi nel nuoto. La piscina è composta da 5 corsie, ciascuna delle
    quali può essere usata al più da 4 persone contemporaneamente. Ogni persona sceglie la corsia occupata dal mi-
    nor numero di persone: se la piscina è al completo, sceglie una corsia in maniera casuale e attende in ordine FIFO.

    Non appena entra in corsia, nuota per un tempo tra 30 e 60 minuti, quindi si fa la doccia (20 minuti), dopodiché va
    via. La piscina è gestita da un istruttore che deve controllare tutte le attività che si svolgono nelle corsie.
    L’istruttore ogni giorno gestisce due turni: la mattina tiene aperta la piscina per 4 ore, poi fa una pausa di 1 ora, e
    riapre la piscina per il turno pomeridiano di 5 ore. Nessuna persona può entrare in piscina se non è presente
    l’istruttore. Alla chiusura della piscina (sia alla pausa sia alla chiusura finale), se sono presenti delle persone nelle
    corsie, l’istruttore forza le persone ad uscire dalla piscina. Le persone devono immediatamente liberare la piscina,
    terminando in anticipo l’attività in acqua e andando subito a fare la doccia.

    Si implementino due soluzioni che riproducano il funzionamento del problema sopra descritto utilizzando:
    • gli strumenti di mutua esclusione e sincronizzazione del package java.util.concurrent.locks;
    • la classe Semaphore (usare solo i metodi acquire e release) del package java.util.concurrent;

    Si scriva infine un main d'esempio facendo uso di una delle due soluzioni precedenti, che inizializzi la piscina,
    l’istruttore e 100 persone, e ne avvii l’esecuzione.
    N.B. 1: Si chiede di commentare il codice. In particolare, scrivere un breve commento per ogni semaforo e condition
    usati nelle soluzioni.
    N.B. 2: Si prega di numerare i fogli dove sono svolti gli esercizi.
    */
}
