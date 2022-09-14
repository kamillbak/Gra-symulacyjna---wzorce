package pl.kamilbak.MetodaSzablonowa;

import pl.kamilbak.MetodaWytworczaUprawy.KreatorUpraw;
import pl.kamilbak.MetodaWytworczaUprawy.Uprawa;
import pl.kamilbak.Strategia.StrategiaInterfejs;

public abstract class MetodaSzablonowa {
    Uprawa uprawa;
    KreatorUpraw kreatorUpraw;
    StrategiaInterfejs strategia;
    double areal;

    public void metodaSzablonowa()
    {
        if(uprawa == null)
        {
            wybierzUprawe();
            rozpocznijUprawe(areal);
        }
        inwestujWUprawe(strategia);
        przeprowadzZbiory();
    }

    abstract public void wybierzUprawe();

    public void setStrategia(StrategiaInterfejs strategia)
    {
        this.strategia = strategia;
    }

    public  void inwestujWUprawe(StrategiaInterfejs strategia){
        strategia.inwestujWUprawe(uprawa);
    }

    public void rozpocznijUprawe(double areal)
    {
        uprawa = kreatorUpraw.stworzUprawe(areal);
        uprawa.zasadz();

    }

    public void przeprowadzZbiory(){
        uprawa.zbierajPlon();
        uprawa.sprzedajPlon();
    }
}
