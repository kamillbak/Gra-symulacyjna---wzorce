package pl.kamilbak.MetodaWytworczaUprawy;

import pl.kamilbak.KontoUzytkownika;

public class UprawaJablek implements Uprawa {
    KontoUzytkownika kontoUzytkownika = KontoUzytkownika.utworzKonto(1);

    double  aktualnieZajmowanyArealWHektarch;
    int aktualnaLiczbaDrzewek;
    String nazwaRosliny = "jablko";


    int liczbaDrzewekPotrzenychNaHektar = 2000; //zasadz()
    double cenaZaJednoDrzewko = 10;

    double kosztOchornyPrzezZwierztamiZaHektar = 8000;
    double kosztNawozyIOpryskiiZaHektar = 10000;
    boolean isUprawaZabezpieczonaPRzedZwierzetami =false;
    boolean isUprawaPryskanaNaworzona = false;

    double kosztPracownikaZaZebranyKG= 0.2; //zbierajPlon()
    double kgZHektara = 30000;
    double zebranyPlonKg;

    double plonPierwszaKlasa;
    double plonDrugaKlasa;

    double cenaZaKGPierwszaKlasa = 1; //sprzedaj()
    double cenaZaKGDrugaKlasa = 0.5;


    @Override
    public void setAreał(double areał) {
        aktualnieZajmowanyArealWHektarch = areał;
    }

    @Override
    public void zasadz() {
        int liczbaSadzonychDrzewek = (int)(aktualnieZajmowanyArealWHektarch * liczbaDrzewekPotrzenychNaHektar);
        double cenaSadzenia= cenaZaJednoDrzewko * liczbaSadzonychDrzewek;

        System.out.println("Na areale " + aktualnieZajmowanyArealWHektarch + " ha zasadzono " + liczbaSadzonychDrzewek + " drzewek " + nazwaRosliny + ", za cene " + cenaSadzenia + " zł" );
        System.out.println("");

        aktualnaLiczbaDrzewek += liczbaSadzonychDrzewek;
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
        isUprawaPryskanaNaworzona = true;
        kontoUzytkownika.platnosc(cenaSrodkow);
    }

    public void zbierajPlon() {
        if(isUprawaPryskanaNaworzona){
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
        System.out.println("Zebrano " + plonPierwszaKlasa+ " kg jablek I klasy i " + plonDrugaKlasa + " kg II klasy, zbieraczom zaplacono " +kosztyPracownikow+" zł");
        System.out.println("");

        kontoUzytkownika.platnosc(kosztyPracownikow);
    }

    @Override
    public void sprzedajPlon() {
        double zarobkiZeSprzedazy = plonPierwszaKlasa * cenaZaKGPierwszaKlasa + plonDrugaKlasa * cenaZaKGDrugaKlasa;

        System.out.println("Ze sprzedazy zarobiono: " + zarobkiZeSprzedazy);
        kontoUzytkownika.przychod(zarobkiZeSprzedazy);
    }
}




