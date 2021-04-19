package mas.mp1;

import java.io.*;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) throws Exception {
	testExtentObjectPlus();
    testSerial();
    testClass();
    }

    private static void testSerial() throws Exception {

        var instrument1 = new InstrumentMuzyczny( "Perkusja", "Mapex", "Drewno - Brzoza");
        instrument1.wypiszOsobyGrajace();
        instrument1.dodajOsobeGrajaca("Lars Ulrich");
        instrument1.dodajOsobeGrajaca("Cezary Konrad");
        instrument1.wypiszOsobyGrajace();




    }

    private static void testClass() throws Exception {

        var instrument1 = new InstrumentMuzyczny( "Perkusja", "Mapex", "Drewno - Brzoza");
        instrument1.getCzyWymaganyPrzeglad();

    }



        private static void testExtentObjectPlus() throws Exception {
        final String extentFilePath = "extent-objectplus.bin";   // TODO update the file name

        var instrument1 = new InstrumentMuzyczny( "Perkusja", "Mapex", "Drewno - Brzoza");
        //var instrument2 = new InstrumentMuzyczny("Skrzypce", "Stradivarius", "Drewno - Mahoń");

        var instrument3 = new InstrumentMuzyczny("Skrzypce", "Stradivarius", "Drewno - Mahoń", new GregorianCalendar(2020, 12 - 1, 1).getTime());


        InstrumentMuzyczny.showExtent();

        try {
            // Write the extent to the stream
            var out = new ObjectOutputStream(new FileOutputStream(extentFilePath));
            ObjectPlus.writeExtents(out);
            out.close();

            // Read the extent from the stream
            var in = new ObjectInputStream(new FileInputStream(extentFilePath));
            ObjectPlus.readExtents(in);
            in.close();

            // Gets the extent and show the content of it
            Iterable<InstrumentMuzyczny> instrumentMuzycznyExtent = ObjectPlus.getExtent(InstrumentMuzyczny.class);
            for (var instrumentMuzyczny : instrumentMuzycznyExtent) {
                System.out.println(instrumentMuzyczny.nazwa);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        InstrumentMuzyczny.showExtent();
    }
}
