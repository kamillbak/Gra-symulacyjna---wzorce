package pl.kamilbak.MetodaSzablonowa;

import pl.kamilbak.MetodaWytworczaUprawy.KreatorUprawaPzenicy;

public class MetodaSzablonowaPszenica extends MetodaSzablonowa{

    public MetodaSzablonowaPszenica( double areal)
    {
        super.areal =areal;
    }
    @Override
    public void wybierzUprawe() {
        super.kreatorUpraw = new KreatorUprawaPzenicy();
    }

}
