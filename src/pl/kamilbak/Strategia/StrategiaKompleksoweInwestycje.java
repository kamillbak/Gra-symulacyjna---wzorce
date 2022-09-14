package pl.kamilbak.Strategia;

import pl.kamilbak.MetodaWytworczaUprawy.Uprawa;



public class StrategiaKompleksoweInwestycje implements StrategiaInterfejs {

    @Override
    public void inwestujWUprawe(Uprawa uprawa) {
        uprawa.zastosujNawozyIOpryski();
        uprawa.zastosujOchornePrzedZwierzetami();
    }
}
