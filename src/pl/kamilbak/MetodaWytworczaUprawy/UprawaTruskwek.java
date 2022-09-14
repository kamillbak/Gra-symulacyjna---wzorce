package pl.kamilbak.MetodaWytworczaUprawy;

import pl.kamilbak.KontoUzytkownika;

public class UprawaTruskwek implements Uprawa{
    KontoUzytkownika kontoUzytkownika = KontoUzytkownika.utworzKonto(1);

    double  aktualnieZajmowanyArealWHektarch ;
    int aktualnaLiczbaRoslin;
    String nazwaRosliny = "truskawka";

    int liczbaSadzonekPotrzenychNaHektar = 50000; //zasadz()
    double cenaZaJednaSadzonke = 1.5;

    double kosztOchornyPrzezZwierztamiZaHektar = 10000; //ochorny()
    double kosztNawozyIOpryskiiZaHektar = 30000;
    boolean isUprawaZabezpieczonaPRzedZwierzetami =false;
    boolean isUprawaPryskanaNAworzona = false;

    double kosztPracownikaZaZebranyKG = 3; //zbierajPlon()
    double kgZHektara = 9000;
    double plonPierwszaKlasa;
    double plonDrugaKlasa;

    double cenaZaKGPierwszaKlasa = 10; //sprzedaj()
    double cenaZaKGDrugaKlasa = 7;

    @Override
    public void setAreał(double areał) {
        aktualnieZajmowanyArealWHektarch = areał;
    }

    @Override
    public void zasadz() {
        int liczbaSadzonychRoslin = (int)(aktualnieZajmowanyArealWHektarch * liczbaSadzonekPotrzenychNaHektar);
        double cenaSadzenia= cenaZaJednaSadzonke * liczbaSadzonychRoslin;

        System.out.println("Na areale " + aktualnieZajmowanyArealWHektarch + " ha zasadzono " + liczbaSadzonychRoslin + " roslin " + nazwaRosliny + ", za cene " + cenaSadzenia + " zł" );
        System.out.println("");

        aktualnaLiczbaRoslin += liczbaSadzonychRoslin;
        kontoUzytkownika.platnosc(cenaSadzenia);
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
        if(isUprawaPryskanaNAworzona) {
            plonPierwszaKlasa = aktualnieZajmowanyArealWHektarch * kgZHektara * 0.8;
            plonDrugaKlasa = aktualnieZajmowanyArealWHektarch * kgZHektara * 0.2;
        }
        else
        {
            plonPierwszaKlasa = aktualnieZajmowanyArealWHektarch * kgZHektara * 0.4;
            plonDrugaKlasa = aktualnieZajmowanyArealWHektarch * kgZHektara * 0.4;
        }

        if(!isUprawaZabezpieczonaPRzedZwierzetami)
        {
            System.out.println("Plon został zniszczony przez zwierzęta w 20%");
            System.out.println("");
            plonPierwszaKlasa = 0.8*plonPierwszaKlasa;
            plonDrugaKlasa = 0.8*plonDrugaKlasa;
        }

        double kosztyPracownikow = (plonPierwszaKlasa + plonDrugaKlasa) * kosztPracownikaZaZebranyKG;
        System.out.println("Zebrano " + plonPierwszaKlasa+ " kg truskawek I klasy i " + plonDrugaKlasa + " kg II klasy, zbieraczom zaplacono " +kosztyPracownikow+" zł");
        System.out.println("");

        kontoUzytkownika.platnosc(kosztyPracownikow);
    }

    @Override
    public void sprzedajPlon() {
        double zarobkiZeSprzedazy = plonPierwszaKlasa * cenaZaKGPierwszaKlasa + plonDrugaKlasa * cenaZaKGDrugaKlasa;
        System.out.println("Ze sprzedazy zarobiono: " + zarobkiZeSprzedazy);
        System.out.println("");
        kontoUzytkownika.przychod(zarobkiZeSprzedazy);
    }
}
