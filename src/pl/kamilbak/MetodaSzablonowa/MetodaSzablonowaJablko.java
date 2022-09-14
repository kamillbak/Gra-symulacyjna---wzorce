package pl.kamilbak.MetodaSzablonowa;

import pl.kamilbak.MetodaWytworczaUprawy.KreatorUprawaJablek;
import pl.kamilbak.MetodaWytworczaUprawy.KreatorUprawaTruskawek;

public class MetodaSzablonowaJablko extends MetodaSzablonowa{

    public MetodaSzablonowaJablko( double areal)
    {
        super.areal =areal;
    }
    @Override
    public void wybierzUprawe() {
        super.kreatorUpraw = new KreatorUprawaJablek();
    }

}
