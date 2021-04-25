package mas.mp1;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class InstrumentMuzyczny extends ObjectPlus implements Serializable {
    String nazwa;
    String producent;
    String rodzajMaterialu;
    Date dataProdukcji;
    private Boolean czyWymaganyPrzeglad;
    Date dataOstatniegoPrzegladu;
    List<String> osobyGrajace = new LinkedList<>();
    static int IloscDniMiedzyPrzegladami = 365;



    public InstrumentMuzyczny( String nazwa, String producent, String rodzajMaterialu) {
        this.nazwa = nazwa;
        this.producent = producent;
        this.rodzajMaterialu = rodzajMaterialu;
        this.dataProdukcji = null;
        this.dataOstatniegoPrzegladu = new Date();
        this.czyWymaganyPrzeglad = false;



    }

    public InstrumentMuzyczny( String nazwa, String producent, String rodzajMaterialu, Date dataProdukcji) {
        this.nazwa = nazwa;
        this.producent = producent;
        this.rodzajMaterialu = rodzajMaterialu;
        this.dataProdukcji = dataProdukcji;
        this.dataOstatniegoPrzegladu = new Date();
        this.czyWymaganyPrzeglad = false;
    }




    public void dodajOsobeGrajaca(String imieNazwisko) {this.osobyGrajace.add(imieNazwisko);}
    public void wypiszOsobyGrajace() {System.out.println(Arrays.toString(this.osobyGrajace.toArray()));
    }
    public void usunOsobeGrajaca(int index) {this.osobyGrajace.remove(index);}

    public void zapiszWykonaniePrzegladu(){ this.dataOstatniegoPrzegladu= new Date(); this.czyWymaganyPrzeglad=false;}
    public void oznaczDoPrzegladu() {this.czyWymaganyPrzeglad=true;}
    public void getCzyWymaganyPrzeglad() {
        Date xDaysAgo = Date.from( Instant.now().minus( Duration.ofDays( IloscDniMiedzyPrzegladami ) ) );

        if (czyWymaganyPrzeglad) System.out.println("Przegląd wymagany");
        else if (dataOstatniegoPrzegladu.before(xDaysAgo)){
            czyWymaganyPrzeglad=true;
            System.out.println("Przegląd wymagany");
        }
        else System.out.println("Przegląd niewymagany");

    }


    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(InstrumentMuzyczny.class);
    }

    public static void oznaczWszystkoDoPrzegladu() throws Exception {
        ObjectPlus.getExtent(InstrumentMuzyczny.class).forEach(InstrumentMuzyczny::oznaczDoPrzegladu);



    }
    public String toString(){
        if(dataProdukcji==null)return nazwa+" "+producent+" "+rodzajMaterialu;
        else
        return nazwa+" "+producent+" "+rodzajMaterialu +" "+dataProdukcji;
    }






}

