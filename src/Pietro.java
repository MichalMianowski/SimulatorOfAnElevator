import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pietro {
    //lista_czekajacych_pasazerow   antykolizyjna
    List czekajacyPasazerowie = Collections.synchronizedList(new ArrayList<Pasazer>());

    public Pietro() {

    }


    public void addToCzekajacyPasazerowie(Pasazer pasazer) {
        czekajacyPasazerowie.add(pasazer);
    }

    public List getCzekajacyPasazerowie() {
        return czekajacyPasazerowie;
    }

}
