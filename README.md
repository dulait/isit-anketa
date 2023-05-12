# ISiT Anketa

Anketa za ISiT studente Fakulteta organizacionih nauka. Ovaj projekat koristi anketu kreiranu u HTML-u i CSS-u, povezuje se sa MySQL bazom podataka koristeći PHP, a zatim analizira podatke koristeći Java aplikaciju.

## Opis

Aplikacija ima sledeće funkcionalnosti:

- Kreiranje i prikaz ankete koristeći HTML i CSS (`anketa.html` i `anketa.css`)
- Povezivanje na MySQL bazu podataka koristeći PHP (`connect.php`)
- Učitavanje informacija o studentima iz baze podataka
- Prikazivanje informacija o studentima u konzoli (`DatabaseConnection.java`)
- Prikazivanje broja muških i ženskih studenata
- Prikazivanje broja studenata na budžetu i samofinansiranju
- Prikazivanje broja studenata koji uče kod kuće ili u čitaonici
- Prikazivanje broja studenata koji su članovi studentskih organizacija
- Izračunavanje prosečnih ocena studenata
- Izračunavanje i prikazivanje histograma ocena
- Izračunavanje i prikazivanje prosečnih ocena po ispitima
- Izračunavanje i prikazivanje prosečnih ocena po godinama studija

## Početak

Da biste pokrenuli ovaj projekat, morate imati instalirane sledeće softvere:

- Java Development Kit (JDK)
- MySQL Server
- PHP
- JFreeChart (Java biblioteka za kreiranje grafikona)
- SQL JDBC Driver (Java biblioteka za povezivanje sa SQL bazama podataka)
- MySQL Connector (PHP biblioteka za povezivanje sa MySQL bazama podataka)

## Instalacija

1. Klonirajte ili preuzmite ovaj repozitorijum
2. Importujte projekat u vaše omiljeno Java razvojno okruženje (IDE)
3. Podesite JDBC konekciju sa vašom MySQL bazom podataka
4. Postavite PHP server za povezivanje sa bazom podataka

## Korišćenje

Da biste koristili ovaj projekat, pratite ove korake:

1. Prvo, napravite MySQL bazu podataka. Struktura baze podataka mora odgovarati strukturi koja se koristi u aplikaciji.
2. Otvorite `anketa.html` u web browser-u i unesite informacije u anketu. Ove informacije će biti sačuvane u vašoj MySQL bazi podataka.
3. U `connect.php`, unesite detalje vaše MySQL baze podataka kako biste omogućili povezivanje.
4. U `DatabaseConnection.java`, unesite detalje vaše MySQL baze podataka kako biste omogućili povezivanje. Kada pokrenete ovu klasu, podaci o studentima će biti učitani iz baze podataka i prikazani u konzoli.
5. `DatabaseConnection.java` takođe izračunava i prikazuje razne statistike o studentima koristeći podatke iz baze podataka.

Imajte na umu da morate da ažurirate `connect.php` i `DatabaseConnection.java` sa detaljima vaše baze podataka kako bi aplikacija pravilno funkcionisala.

## Autori

Ovaj projekat je razvio student Dušan Drašković.

## Licenca

Ovaj projekat je licenciran pod MIT licencom.
