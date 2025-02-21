package nationGen.GUI;

import nationGen.NationGen;
import nationGen.entities.Race;
import nationGen.naming.NameGenerator;

public class NameGen {

  public static void main(String[] args) {
    NationGen ng = new NationGen();
    NameGenerator nameg = new NameGenerator(ng);

    for (Race r : ng.getAssets().races) {
      if (!r.tags.containsName("secondary")) {
        System.out.print(r.visiblename + ": ");
        for (int i = 0; i < 10; i++) {
          System.out.print(nameg.generateNationName(r, null));
          if (i < 9) System.out.print(", ");
        }
        System.out.println();
      }
    }
  }
}
