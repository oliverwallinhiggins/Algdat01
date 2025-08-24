import java.util.Random;

public class AksjeAnalyse {

  /**
   * <h2>Oppgave 1-1 </h2>
   *
   * Oppgaven 1-1 går ut på å lage en enkel algoritme som skriver ut høyest mulig fortjeneste
   * ved kjøp og salg av aksjer, basert på prisendringen over en 24h periode.
   * @author Thomas Oliver Wallin Higgins
   */

  public static void main(String[] args) {

    int[] prisEndring = {-1, -3, 1, 2, 3,-3};

    int[] aksjeKurs = new int[prisEndring.length + 1];
    aksjeKurs[0] = 0;
    for (int i = 0; i < prisEndring.length; i++) {
      aksjeKurs[i + 1] = aksjeKurs[i] + prisEndring[i];
    }

    int minPris = aksjeKurs[0];
    int minDag = 0;
    int maxProfitt = 0;
    int kjopDag = 0;
    int salgDag = 0;

    for (int dag = 1; dag < aksjeKurs.length; dag++) {
      int pris = aksjeKurs[dag];

      int profitt = pris - minPris;
      if (profitt > maxProfitt) {
        maxProfitt = profitt;
        kjopDag = minDag;
        salgDag = dag;
      }

      if (pris < minPris) {
        minPris = pris;
        minDag = dag;
      }
    }
    System.out.println("Beste kjøp: dag " + kjopDag);
    System.out.println("Beste salg: dag " + salgDag);
    System.out.println("Maks fortjeneste: " + maxProfitt);

  }

  /**
   * <h2>Oppgave 1-2</h2>
   *
   * <p>Kompleksiteten til en algoritme beskriver hvor mange operasjoner som utføres
   * i forhold til inputet den tar inn, i dette tilfellet prisendring per 24h.</p>
   *
   * <p>Algoritmen i oppgave 1-1 har to hoveddeler, begge med
   * <b>&Theta;(n)</b> kompleksitet:</p>
   *
   * <ul>
   *   <li><b>&Theta;(n)</b> for å beregne kursene ut fra endringene</li>
   *   <li><b>&Theta;(n)</b> for å finne beste kjøp- og salgstidspunkt</li>
   * </ul>
   *
   * <p>Siden <b>&Theta;(n) + &Theta;(n) = &Theta;(n)</b>,
   * er total kompleksitet lineær.</p>
   *
   * <p>Vi bruker <b>Big-Theta</b> ( <b>&Theta;(n)</b> ) notasjon i denne oppgaven
   * da programmet må kjøre igjennom hele datasettet. Øvre og nedre grense for kjøretid
   * er dermed like, og <b>&Theta;</b>-notasjon brukes.</p>
   */

  public static class oppgavetekst {
  }


  /**
   * <h2>Oppgave 1-3</h2>
   * <p>Legger inn tidsmåling for algoritmen fra Oppgave 1-1.</p>
   * <p>For å unngå for grove målinger på små tallrekker kjører vi samme test flere ganger
   * og deler total tid på antall repetisjoner.</p>
   */
  public static class TidsMaling {
    public static void main(String[] args) {
      int[] storrelser = {10,1_000,10_000, 100_000,1_000_000};
      int repetisjoner = 200;

      java.util.Random rand = new java.util.Random();

      for (int n : storrelser) {
        int[] prisEndring = new int[n];
        for (int i = 0; i < n; i++) {
          prisEndring[i] = rand.nextInt(21) - 10;
        }

        long start = System.nanoTime();
        int kontroll = 0;
        for (int r = 0; r < repetisjoner; r++) {
          kontroll += maksFortjenesteOppg1_1(prisEndring);
        }
        long slutt = System.nanoTime();

        double totalMs = (slutt - start) / 1000000.0;


        System.out.printf("n=%-6d  repetisjoner=%-4d  total=%.2f ms (kontroll=%d)%n",
                n, repetisjoner, totalMs, kontroll);
      }

      System.out.println("\nForventning: snitt-tid per kjøring skal øke omtrent lineært med n (Θ(n)).");
    }

    private static int maksFortjenesteOppg1_1(int[] prisEndring) {
      int[] aksjeKurs = new int[prisEndring.length + 1];
      aksjeKurs[0] = 0;
      for (int i = 0; i < prisEndring.length; i++) {
        aksjeKurs[i + 1] = aksjeKurs[i] + prisEndring[i];
      }

      int minPris = aksjeKurs[0];
      int maxProfitt = 0;

      for (int dag = 1; dag < aksjeKurs.length; dag++) {
        int pris = aksjeKurs[dag];
        int profitt = pris - minPris;
        if (profitt > maxProfitt) maxProfitt = profitt;
        if (pris < minPris) minPris = pris;
      }
      return maxProfitt;
    }
  }

}
