package pl.kamilbak.MetodaWytworczaUprawy;

public class KreatorUprawaTruskawek extends KreatorUpraw {

    @Override
    public Uprawa stworzUprawe(double areal) {
        Uprawa uprawa = new UprawaTruskwek();
        uprawa.setAreał(areal);
        return uprawa;
    }
}
