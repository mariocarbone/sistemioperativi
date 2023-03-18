public abstract class Supermercato {

    /*
    Dei clienti di un supermercato fanno la spesa e alla fine si recano alle casse per pagare i p prodotti
presenti nel proprio carrello (con p compreso tra 1 e 50). Alle casse c'è un'unica coda FIFO: il primo
della coda ha davanti a sé uno schermo su cui compare il numero della cassa presso cui si deve recare.
Quando appare il numero sullo schermo, la persona si reca presso la cassa identificata dal numero
visualizzato, consegna al cassiere i prodotti attendendo che questi siano scanditi, dopodiché va via. Ad
ogni cassa è presente un cassiere che effettua ciclicamente le seguenti operazioni: segnala allo schermo
che è pronto a ricevere un cliente, attende un cliente, all'arrivo del cliente scandisce i suoi prodotti
impiegando per questa operazione p secondi, infine congeda il cliente.
Si modelli il sistema descritto in Java, dove i clienti e i cassieri sono dei thread che interagiscono
tramite un oggetto casse che espone i seguenti metodi:
• int getIdCassa(): permette al cliente di ottenere l'id della cassa presso la quale si deve
recare.
• void consegnaProdotti(int id, int p): permette al cliente di consegnare i p prodotti
presenti nel proprio carrello al cassiere della cassa avente l'id specificato.
• int segnalaCassaLibera(int id): permette al cassiere di segnalare allo schermo che la
propria cassa, avente l'id specificato, è pronta a ricevere un cliente. Restituisce il numero di
prodotti consegnati dal cliente.
• void congedaCliente(int id): permette al cassiere della cassa avente l'id specificato di
congedare il proprio cliente dopo aver terminato la scansione dei suoi prodotti.
Si implementino due soluzioni che riproducano il funzionamento del problema sopra descritto
utilizzando:
• la classe Semaphore del package java.util.concurrent
• gli strumenti di mutua esclusione e sincronizzazione del package java.util.concurrent.locks
Si scriva infine un main d'esempio che, facendo uso di una delle due soluzioni precedenti, inizializzi un
oggetto casse, inizializzi 100 persone, 10 cassieri e ne avvii l’esecuzione.



     */


}

