package pl.kamilbak;

import pl.kamilbak.MetodaSzablonowa.MetodaSzablonowa;
import pl.kamilbak.MetodaSzablonowa.MetodaSzablonowaJablko;
import pl.kamilbak.MetodaSzablonowa.MetodaSzablonowaPszenica;
import pl.kamilbak.MetodaSzablonowa.MetodaSzablonowaTruskawka;
import pl.kamilbak.Strategia.*;

import java.util.Scanner;

public class Main {

    public static final String ANSI_YELLOW = "\u001B[33m"; // zmienna do kolorowania tekstu na zolto
    public static final String ANSI_RESET = "\u001B[0m"; // resetowanie na domyslny kolor

    public static void main(String[] args) {
        boolean czyChceszKontynuowacGre = true;
        StrategiaInterfejs wybranaStrategia = null;
        StrategiaInterfejs strategiaBezInwestycji= new StrategiaBezInwestycji();
        StrategiaInterfejs strategiaNawozyIOpryski= new StrategiaNawozyIOpryski();
        StrategiaInterfejs strategiaOchornaPrzedZwierzetami= new StrategiaOchornaPrzedZwierzetami();
        StrategiaInterfejs strategiaKompleksoweInwestycje = new StrategiaKompleksoweInwestycje();
        MetodaSzablonowa metodaSzablonowadanejUprawy;

        //----------------------------  Stworzenie konta z pienidzmi uzytkownika  --------------------------

        Scanner scanner = new Scanner(System.in);
        System.out.println("Jakim środki możesz przeznaczyć na prowadzenie upraw [zł] ?");
        double pieniadzeNaStart;
        try {
             pieniadzeNaStart = scanner.nextDouble();
        } catch (Exception e)
        {
            System.out.println(ANSI_YELLOW +"Niepoprawna liczba, zaczynasz gre z 0 na koncie" + ANSI_RESET);
            System.out.println("");
            pieniadzeNaStart = 0;
        }
        KontoUzytkownika konto = KontoUzytkownika.utworzKonto(pieniadzeNaStart);

        //---------------------------- Pytanie o areal  ------------------

        Scanner scannerA = new Scanner(System.in);
        System.out.println("Jaki areal chcesz przeznaczyc na uprawe [ha] ? ");

        double areal;
        try {
            areal = scannerA.nextDouble();
        }catch (Exception e)
        {
            System.out.println( ANSI_YELLOW + " Nieprawidlowy areał, zaczynasz gre z 1 ha" +  ANSI_RESET);
            System.out.println("");
            areal = 1;
        }

        //----------------------------  wybor uprawy na poczatku gry --------------------------

        Scanner scanner0 = new Scanner(System.in);
        System.out.println("Jaka Uprawe chcesz rozpoczac?");
        System.out.println("1. Truskawek");
        System.out.println("2. Jablek");
        System.out.println("3. Pszenicy");

        int wybor0;
        try{
            wybor0 = scanner0.nextInt();
        } catch(Exception e){
            System.out.println(ANSI_YELLOW + "Nieprawidlowo wprowadzona liczba, rozpoczynasz hodowle pszenicy" + ANSI_RESET);
            System.out.println("");
            wybor0 = 3;
        }


        switch (wybor0)
        {
            case 1 -> metodaSzablonowadanejUprawy = new MetodaSzablonowaTruskawka(areal);
            case 2 -> metodaSzablonowadanejUprawy = new MetodaSzablonowaJablko(areal);
            case 3 -> metodaSzablonowadanejUprawy = new MetodaSzablonowaPszenica(areal);
            default ->{
                System.out.println(ANSI_YELLOW + "Niedostepna opcja, rozpoczynasz hodowle pszenicy " + ANSI_RESET);
                metodaSzablonowadanejUprawy = new MetodaSzablonowaPszenica(areal);
            }
        }

        // ------------------------------------------------------------------------------------

        // ---------------------------- Petla powtarzjaca sie w trakcie gry -------------------
        while(czyChceszKontynuowacGre)
        {

            // ------ Wybor strategi inwestycji w gospodarstwo  -----
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Jaka strategie podczas prowadzenia upraw wybierasz w tym sezonie?");
            System.out.println("1. Bez inwestowania dodatkowych pieniędzy");
            System.out.println("2. Inwsetycja w nowozy i opryski");
            System.out.println("3. Inwestycja w ochorne upraw przed zwierzetami" );
            System.out.println("4. Kompleksowe inwestycje ( Opcja 2 i 3 jednocześnie)");

            int wybor1;
            try{
                wybor1 = scanner1.nextInt();
            } catch(Exception e){
                System.out.println(ANSI_YELLOW + "Nieprawidlowo wprowadzony wybor, przypisano strategie bez iwestycji" + ANSI_RESET);
                System.out.println("");
                wybor1 = 1;
            }

            switch (wybor1)
            {
                case 1 ->  wybranaStrategia = strategiaBezInwestycji;
                case 2 ->  wybranaStrategia = strategiaNawozyIOpryski;
                case 3 ->  wybranaStrategia = strategiaOchornaPrzedZwierzetami;
                case 4 ->  wybranaStrategia =strategiaKompleksoweInwestycje;
                default -> {
                    System.out.println(ANSI_YELLOW + "Niedostepna opcja, przypisano strategie bez iwestycji" + ANSI_RESET);
                    System.out.println("");
                    wybranaStrategia = strategiaBezInwestycji;
                }
            }
            // ----------------------------------------------------------

            metodaSzablonowadanejUprawy.setStrategia(wybranaStrategia);
            metodaSzablonowadanejUprawy.metodaSzablonowa();
            konto.podsumujStanKonta();

            // ------------------- Decyzja o kotynuowaniu gry ----------
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Chcesz kontynuwoac gre? ");
            System.out.println("1. Tak");
            System.out.println("2. Nie");

            int wybor2;
            try {
                wybor2 = scanner2.nextInt();
            }catch (Exception e)
            {
                System.out.println(ANSI_YELLOW + "Nieprawidlowo wprowadzony wybor, kontynujemy gre" + ANSI_RESET);
                System.out.println("");
                wybor2 = 1;
            }

            switch (wybor2)
            {
                case 1 -> czyChceszKontynuowacGre = true;
                case 2 -> czyChceszKontynuowacGre = false;
                default ->{
                    System.out.println("Nie ma takiej opcji, kontynujemy gre");
                    System.out.println("");
                    czyChceszKontynuowacGre = true;
                }
            }
            // ----------------------------------------------------------

        }
        // -----------------------------------------------------------------------------------------



    }
}




