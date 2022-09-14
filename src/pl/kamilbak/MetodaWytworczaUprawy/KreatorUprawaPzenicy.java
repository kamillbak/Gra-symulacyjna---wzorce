package pl.kamilbak.MetodaWytworczaUprawy;

public class KreatorUprawaPzenicy extends KreatorUpraw{

    @Override
    public Uprawa stworzUprawe(double areal) {
        Uprawa uprawa = new UprawaPszenicy();
        uprawa.setAreał(areal);
        return uprawa;
    }
}
