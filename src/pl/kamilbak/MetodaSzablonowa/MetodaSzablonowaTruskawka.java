package pl.kamilbak.MetodaSzablonowa;

import pl.kamilbak.MetodaWytworczaUprawy.KreatorUprawaTruskawek;

public class MetodaSzablonowaTruskawka extends MetodaSzablonowa{

    public MetodaSzablonowaTruskawka( double areal)
    {
        super.areal =areal;
    }
    @Override
    public void wybierzUprawe() {
        super.kreatorUpraw = new KreatorUprawaTruskawek();
    }
}
