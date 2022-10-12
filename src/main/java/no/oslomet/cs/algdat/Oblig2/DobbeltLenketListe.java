package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.*;
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

    }

    public DobbeltLenketListe(T[] a) {
        if(a.length==0){
            return;
        }
        if(a == null){
            throw new NullPointerException("a er null");
        }
        int starter = 0;
        for (int i = 0; i < a.length; i++){
            if(a[i] != null) {
                hode = new Node<>(a[i]);
                starter = i;
                antall =1;
                break;
            }
        }


        Node <T> forrige = hode;
        for (int i = starter+1; i < a.length; i++){
            if (a[i] == null){

            }else{
                Node<T> j = new Node<>(a[i],forrige,null);
                forrige.neste = j;
                forrige = j;
                antall++;
            }
        }
        endringer = 0;
        hale = forrige;
    }

    private void fratilKontroll(int fra, int til){
        if(fra<0){throw new IndexOutOfBoundsException("fra er mindre enn 0!");}
        if(til>antall){throw new IndexOutOfBoundsException("til indeksen finnes ikke!");}
        if(fra>til){throw new IllegalArgumentException("til indeksen er mindre enn fra indeksen!");}
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(fra, til);
        Liste<T> nyliste = new DobbeltLenketListe<>();
        Node<T> current = finnNode(fra);
        for(int i = fra; i<til; i++){
            nyliste.leggInn(current.verdi);
            current = current.neste;
        }

        return nyliste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (hode == null){
            return true;}
        else {
            return false;
        }

    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi);

        if(tom()) hode = hale = new Node<>(verdi, null, null);
        else hale = hale.neste = new Node<>(verdi, hale, null);
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        if(verdi == null){
            throw new NullPointerException("verdi er null");
        }
        indeksKontroll(indeks, true);
        if(indeks==0){
            if(antall==0){
                Node<T> verdiNode = new Node<>(verdi);
                hode = verdiNode;
                hale = hode;
            }
            else{
                Node<T> forjeverdi = hode;
                Node<T> verdiNode = new Node<>(verdi);
                verdiNode.neste = forjeverdi;
                forjeverdi.forrige = verdiNode;
                hode = verdiNode;
            }
        } else if (indeks==antall) {
            Node<T> forjeverdi = hale;
            Node<T> verdiNode = new Node<>(verdi);
            forjeverdi.neste = verdiNode;
            verdiNode.forrige = forjeverdi;
            hale = verdiNode;
        } else{
            Node<T> forjeverdi = finnNode(indeks);
            System.out.println(forjeverdi.verdi);
            Node<T> verdiNode = new Node<>(verdi, forjeverdi.forrige, forjeverdi);
            forjeverdi.forrige.neste = verdiNode;
            forjeverdi.forrige = verdiNode;
        }
        antall++;
        endringer++;
    }


    @Override
    public boolean inneholder(T verdi) {
        int retur = indeksTil(verdi);
        return retur != -1;
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
            for(int i = antall-1; i>indeks;i--){
                current = current.forrige;
            }
            return current;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        T Verdi = finnNode(indeks).verdi;
        return Verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }

        Node <T> p = hode;

        for (int i =0; i < antall; i++, p = p.neste) {
            if (p.verdi.equals(verdi))
                return i;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if(nyverdi == null){
            throw new NullPointerException("nyverdi er null");
        }
        indeksKontroll(indeks, false);
        Node <T> node = finnNode(indeks);
        T gammelverdi = node.verdi;

        node.verdi = nyverdi;
        endringer++;

        return gammelverdi;

    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }
        Node <T> node = hode;
        int i;
        for (i =0; i < antall; i++, node = node.neste) {
            if (node.verdi.equals(verdi)) {
                break;
            }
        }
        if(i==antall){
            return false;
        }
        if (antall == 1) {
            hode = null;
            hale = null;
        }
        else if (node.neste == null){
            hale.forrige.neste = null;
            hale = hale.forrige;
        }
        else if (node == hode){
            node.neste.forrige = null;
            hode = hode.neste;
        }
        else {
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }
        antall --;
        endringer ++;

        return true;
    }
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node <T> node = finnNode(indeks);
        T verdiUt = node.verdi;

        if (antall == 1) {
            hode = null;
            hale = null;
        }
        else if (node.neste == null){
            hale.forrige.neste = null;
            hale = hale.forrige;
        }
        else if (node == hode){
            node.neste.forrige = null;
            hode = hode.neste;
        }
        else {
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }
        antall --;
        endringer ++;

        return verdiUt;
    }

    @Override
    public void nullstill() {

        //metode 1
        for (Node<T> t = hode; t != null; t=t.neste){
            t.verdi = null;
            t.forrige = t.neste = null;
        }

        hode = hale = null;
        antall = 0;
        endringer++;

    }
//jeg velger å bruke metode 1 siden den er mer effektiv

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append('[');

        if(!tom()){
            Node<T> p = hode;
            s.append(p.verdi);

            p = p.neste;



            while (p!=null){
                s.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }
        s.append(']');


        return s.toString();

    }

    public String omvendtString()  {
        StringBuilder s = new StringBuilder();

        s.append('[');

        if(!tom()){
            Node<T> p = hale;
            s.append(p.verdi);

            p = p.forrige;


            while (p!=null){
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }
        s.append(']');


        return s.toString();

    }

    @Override
    public Iterator<T> iterator() {
        return new  DobbeltLenketListeIterator();
    }


    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new  DobbeltLenketListeIterator(indeks);

    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

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
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            }
            fjernOK = true;
            T ReturnVerdi = denne.verdi;
            denne = denne.neste;
            return ReturnVerdi;

        }

        @Override
        public void remove() {
            if (!fjernOK) throw new IllegalStateException("Du kan ikke fjene før next()");
            if (endringer != iteratorendringer) throw new ConcurrentModificationException();
            fjernOK = false;

            if (antall == 1) {
                hode = null;
                hale = null;
            }
            else if (denne == null){
                hale.forrige.neste = null;
                hale = hale.forrige;
            }
            else if (denne.forrige == hode){
                denne.forrige = null;
                hode = denne;
            }
            else {
                denne.forrige.forrige.neste = denne;
                denne.forrige = denne.forrige.forrige;
            }
            antall --;
            iteratorendringer ++;
            endringer ++;

        }

    } // class DobbeltLenketListeIterator


    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        if (liste == null) {
            throw new UnsupportedOperationException();
        }

        for (int i = 0; i < liste.antall() -1; i++) {

            int min_index = i;
            for (int h = i + 1; h < liste.antall(); h++) {

                if (c.compare(liste.hent(h), liste.hent(min_index)) < 0)
                    min_index = h;


            }


                T temp = liste.hent(min_index);
                liste.oppdater(min_index, liste.hent(i));
                liste.oppdater(i, temp);



        }

    }

} // class DobbeltLenketListe


