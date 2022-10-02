package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        for (int i = 0; i < a.length; i++){

        }
        hode = new Node<>(a[0]);
        antall = 0;
        Node <T> forrige = hode;
        for (int i = 0; i < a.length; i++){
            if (a[i] == null){
                continue;
            }else{
            Node<T> j = new Node<>(a[i],forrige,null);
            forrige.neste = j;
            forrige = j;
            antall++;
                }

        }
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (hode== null){
        return false;}
        else {
            return true;
        }

    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    private Node<T> finnNode(int indeks){

        if(indeks<(antall/2)){
            Node<T> current = hode;
            for(int i = 0; i<indeks;i++){
                current = current.neste;
            }
            return current;
        }
        else{
            Node<T> current = hale;
            for(int i = 0; i<(antall-indeks);i++){
                current = current.forrige;
            }
            return current;
        }
    }
    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();


    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null){
            return false;
        }
        Node <T> a = hode;
        Node <T> b;
        Node <T> c;
        for (int i = 0; i < antall; i++){

            while (a.verdi.equals(verdi)){
                if (antall == 1){
                    hode = hale;
                    hale = null;
                    break;
                }
                if (i == 0){
                    a = a.neste;
                    a.forrige = null;
                    hode = a;
                    break;
                }
                else if (i < antall - 1){

                    a = a.forrige;
                    b = a.neste;
                    c = b.neste;

                    a.neste = c;
                    c.forrige = a;

                    break;

                }else {
                    b = a.forrige;
                    b.neste = null;
                    hale = b;
                    break;
                }
            }
            if (i == antall - 1){
                return false;
            }
            a = a.neste;
        }
        antall --;
        endringer++;

        return true;
    }

    @Override
    public T fjern(int indeks) {
    indeksKontroll(indeks, false);
        T verdiUt;
    while (indeks == 0){

            if (antall == 1){
                verdiUt = hode.verdi;
                hale = hode;
                hode = null;
            }else{
                verdiUt = hode.verdi;
                hode = hode.neste;
                hode.forrige = null;
            }
        }
    if (indeks == antall -1 ){
        verdiUt = hale.verdi;
        hale = hale.forrige;
        hale.neste = null;
    }else {
        Node<T> a = finnNode(indeks - 1);
        Node<T> b = a.neste;
        Node<T> c = b.neste;
        verdiUt = b.verdi;
        a.neste = c;
        c.forrige = a;
    }
    antall--;
    endringer++;
    return verdiUt;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new  DobbeltLenketListeIterator();
    }


    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();

    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private final int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (iteratorendringer != endringer)
            {
                throw new ConcurrentModificationException();
            }
            else if (!hasNext()){
                throw new NoSuchElementException();
            }
            fjernOK = true;
            T ReturnVerdi = denne.verdi;
            denne = denne.neste;
            return ReturnVerdi;

    }

        @Override
        public void remove() {

        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


