package pl.kamilbak.MetodaWytworczaUprawy;

import pl.kamilbak.KontoUzytkownika;

public class UprawaPszenicy implements Uprawa {
    KontoUzytkownika kontoUzytkownika = KontoUzytkownika.utworzKonto(1);

    double  aktualnieZajmowanyArealWHektarch;
    String nazwaRosliny = "pszenica";

    double cenaObsadzeniaHektara = 650; //zasadz()

    double kosztOchornyPrzezZwierztamiZaHektar = 2000; //ochorny()
    double kosztNawozyIOpryskiiZaHektar = 3000;

    boolean isUprawaZabezpieczonaPRzedZwierzetami =false;
    boolean isUprawaPryskanaNAworzona = false;

    double kgZHektara = 4500; //zbierajPlon()
    double cenaZaUslugeZbioruZaHa = 300;
    double zebranyPlonKg;

    double cenaZaKG =1.726; //sprzedaj()


    @Override
    public void setAreał(double areał) {
        aktualnieZajmowanyArealWHektarch = areał;
    }

    @Override
    public void zasadz() {
        double cenaSadzenia = cenaObsadzeniaHektara * aktualnieZajmowanyArealWHektarch;

        System.out.println("Na areale " + aktualnieZajmowanyArealWHektarch + " ha zasadzono "  + " rosline  " + nazwaRosliny + ", za cene " + cenaSadzenia + " zł" );
        System.out.println("");

        kontoUzytkownika.platnosc(cenaSadzenia);;
    }

    @Override
    public void zastosujOchornePrzedZwierzetami() {
        double cenaOchrony = aktualnieZajmowanyArealWHektarch * kosztOchornyPrzezZwierztamiZaHektar;
        System.out.println("Zastosowane ochrone przez zwierzetami na areale " + aktualnieZajmowanyArealWHektarch +", zaplacono " + cenaOchrony +" zl" );
        System.out.println("");

        isUprawaZabezpieczonaPRzedZwierzetami = true;
        kontoUzytkownika.platnosc(cenaOchrony);

    }

    @Override
    public void zastosujNawozyIOpryski() {
        double cenaSrodkow= aktualnieZajmowanyArealWHektarch * kosztNawozyIOpryskiiZaHektar;

        System.out.println("Zastosowane nawozy i opryski na areale " + aktualnieZajmowanyArealWHektarch +", zaplacono " + cenaSrodkow +" zl" );
        System.out.println("");
        isUprawaPryskanaNAworzona = true;
        kontoUzytkownika.platnosc(cenaSrodkow);
    }

    @Override
    public void zbierajPlon() {
        if(isUprawaPryskanaNAworzona)
            zebranyPlonKg = aktualnieZajmowanyArealWHektarch * kgZHektara * 1.2;
        else
            zebranyPlonKg = aktualnieZajmowanyArealWHektarch * kgZHektara * 0.6;

        if(!isUprawaZabezpieczonaPRzedZwierzetami)
        {
            System.out.println("Plon został zniszczony przez zwierzęta w 20%");
            System.out.println("");
            zebranyPlonKg = zebranyPlonKg - 0.2*zebranyPlonKg;
        }

        System.out.println("Zebrano " +zebranyPlonKg+ " kg pszenicy, za wynajęcie kombajnu zaplacono " + cenaZaUslugeZbioruZaHa*aktualnieZajmowanyArealWHektarch +" zł");
        System.out.println("");

        kontoUzytkownika.platnosc(cenaZaUslugeZbioruZaHa*aktualnieZajmowanyArealWHektarch);

    }

    @Override
    public void sprzedajPlon() {
        double zarobkiZeSprzedazy = zebranyPlonKg * cenaZaKG;

        System.out.println("Ze sprzedazy zarobiono: " + zarobkiZeSprzedazy);
        System.out.println("");
        kontoUzytkownika.przychod(zarobkiZeSprzedazy);
    }
}



//http://wir.org.pl/asp/pszenica-ozima,194,,1

