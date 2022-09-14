package pl.kamilbak.MetodaWytworczaUprawy;

public class KreatorUprawaJablek extends KreatorUpraw{

    @Override
    public Uprawa stworzUprawe(double areal) {
        Uprawa uprawa = new UprawaJablek();
        uprawa.setAreał(areal);
        return uprawa;
    }
}
