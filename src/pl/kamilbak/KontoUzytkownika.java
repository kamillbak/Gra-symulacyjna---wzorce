package pl.kamilbak;

public class KontoUzytkownika {
    public static KontoUzytkownika unikalneKonto;
    public double stanKonta;
    public  double poczatkowyStanKonta;

    private KontoUzytkownika(double stanKonta)
    {
        poczatkowyStanKonta = stanKonta;
        this.stanKonta = stanKonta;
    }

    public static KontoUzytkownika utworzKonto(double stanKonta)
    {
        if(unikalneKonto== null)
        {
            unikalneKonto = new KontoUzytkownika(stanKonta);
        }

        return unikalneKonto;
    }
    public void platnosc( double koszt)
    {
        stanKonta = stanKonta - koszt;
//        System.out.println("Z konta pobrano " + koszt +" zł");
    }

    public void przychod( double kwota)
    {
        stanKonta = stanKonta + kwota;
//        System.out.println("Na konto wplynelo " + kwota +" zł");
    }

    public void podsumujStanKonta()
    {
        System.out.println("STAN KONTA POCZATKOWY: " + poczatkowyStanKonta + " zł");
        System.out.println("STAN KONTA AKTUALNY: "+ stanKonta +" zł");
        System.out.println("PIENIADZE ZAROBIONE: " + (stanKonta - poczatkowyStanKonta) + " zł");
    }
}

