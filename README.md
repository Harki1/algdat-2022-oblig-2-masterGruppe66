# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Mahdi Ahmed Yusuf, S364517, s364517@oslomet.no
* Harkirat singh, S362077, s362077@oslomet.no
* Hassan Mehmod Hussain, S354545, s354545@oslomet.no
* Ibrahima Secka, S355423, s355423@oslomet.no 
* Sabasan Selvachandran, s362076, s362076@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Mahdi har hatt hovedansvar for oppgave ? og ?. 
* Harkirat har hatt hovedansvar for oppgave 3 og 5. 
* Ibrahima har hatt hovedansvar for oppgave ? og ?. 
* Sabasan har hatt hovedansvar for oppgave 2 og 7.
* Hassan har hatt hovedansvar for oppgave 4 og 10. 

* Vi har i fellesskap hjelpet hverandre med oppgavene som vi synes var vanskelige.

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å ...

I oppgave 2 så brukte vi en toString() metode til å returene tegnstreng med listens veridier. Vi brukte "[" og "]" på begge sider av strengen slik at hvis man hadde verdiene "1", "2", og "3", så fikk man "[1,2,3]". For å traversere fra hode til hale bruker vi en Stringbuuilder. Deretter lager vi en omvendtString() får å returnere verdiene på samme måte bare en omvendt rekkefølge. I 2b bruker vi boolean leggInn metode, vi tar hensyn til null-verdier og legger inn noder med innsatte verdier som legges bakerst, metoden skal returnere true.

I oppgave 3 så lagde vi først hjelpe metoden finnNode til å finne noden. Dette gjorde vi ved å først sjekke om indeksen var i første eller andre halvdel av Dobbellenket liste, slik at det ble mer effektivt. Også lagde vi hent for å finne verdien. Også lagde vi oppdater, først sjekker vi om den nye verdien er null og om den er det trower vi en exeption, også sjekker vi indeksen med indeksKontroll. Også finner vi noden i den indeksen og endrer den til ned nye verdien og returnerer den gamle. Også lagde vi subliste, som lager en kopi av en del av en Dobbellenket liste.

I Oppgave 4 brukte vi først et if-statement som da returnerer "-1" hvis verdien da er lik "null". Deretter bestemte vi oss for å lage en for løkke som looper gjennom verdiene for å sjekke om "p.verdi" blir det samme som verdi, og som da skal returnere indeksen hvis dette er tilfellet, hvis ikke så returnerer den fremdeles -1.

I oppgave 5 så lagde vi først sjekker for om den verdien som skal legges inn er null, og trower en exeption om det er det. Etter det sjekker vi om verdien skal legges foran (indeks = 0) bakerst (indeks = antall) eller mellom to verdier. Og forsikrer om at pekerene blir riktige. og vi øker antall og endringer om det blir lagt til noe. Vi bruker finnNode(indeks), for å finne den noden som er der vi vil legge inn den nye verdien, også dytter vi de verdiene etter ett hakk til høyre, og legger inn den nye verdien.

I oppgave 7 bruker vi en void nullstill() metode som tømmer listen. Vi innser at metode 1 er mye mer effektiv og bruker den der man starter med "hode" og går mot "hale" ved hjelp av "neste" pekeren. vi p.neste = q.neste = null, dermed nulles nodeverdien for hver node og blir null til slutt og endringen økes.

I Oppgave 10 brukte vi en rekke byggeklosser innen java for å løse oppgaven. Vi brukte først og fremst en for løkke som looper gjennom listen, og enda en for løkke som da er inni den første. deretter ble det brukt en if-setning som da bruker kommando "compare" for å da sjekke om verdiene er mindre enn 0, og om disse er det, så får "min_verdi" en ny verdi. Etter dette brukes variabelen temp til å oppdatere listen ved hjelp av "liste.oppdater".
