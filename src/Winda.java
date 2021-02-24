import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Winda extends Thread{
    ArrayList<Pasazer> lista_pasazerow = new ArrayList<>();
    Pietro pietra[];
    private int cel;
    private int stan = 0; //1 jedzie w gore, -1 jedzie w dol, 0 stoi
    private int pojemnosc;
    private int t;
    private int aktualnyPoziom = 0;


    public Winda(int t, int pojemnoscWindy, Pietro pietra[]) {
        this.t = t;
        this.pojemnosc = pojemnoscWindy;
        this.pietra = pietra;
    }

    private void mijaPietro(){
        ktoChceWysiasc();
        if(czySaWolneMiejsca()){
          czyKtosWNaszymKierunku();
        }
        if(lista_pasazerow.isEmpty() && aktualnyPoziom == cel){
            stan = 0;
        }
    }

    private void czyKtosWNaszymKierunku() {
        Iterator i = pietra[aktualnyPoziom].getCzekajacyPasazerowie().iterator();
        Pasazer pasazer = null;
        while (i.hasNext() && czySaWolneMiejsca()) {
            pasazer = (Pasazer) i.next();
            if(lista_pasazerow.isEmpty()){
                cel = pasazer.getDokad();
                stan = pasazer.getKierunek();
                lista_pasazerow.add(pasazer);
                i.remove();
            } else if ((aktualnyPoziom == pasazer.getSkad()) && (pasazer.getKierunek() == stan)){
                if(stan < 0 && pasazer.getDokad() < cel){
                    cel = pasazer.getDokad();
                } else if (stan > 0 && pasazer.getDokad() > cel){
                    cel = pasazer.getDokad();
                }
                lista_pasazerow.add(pasazer);
                i.remove();
            }
        }
    }

    private boolean czySaWolneMiejsca() {
        return (lista_pasazerow.size() < pojemnosc);
    }

    private void ktoChceWysiasc() {
        Iterator i = lista_pasazerow.iterator();
        Pasazer pasazer = null;
        while (i.hasNext()) {
            pasazer = (Pasazer) i.next();
            if (aktualnyPoziom == pasazer.getDokad()){
                i.remove();
            }
        }
    }

    public void run() {
        while (true){
            tick();
        }
    }
    public void tick() {
        try{
            sleep(t*1000);
            switch (stan) {
                case 0:
                    stoj();
                    nasluchuj();
                    break;
                case 1:
                    jedz();
                    mijaPietro();
                    break;
                case -1:
                    jedz();
                    mijaPietro();
                    break;
            }
            stanPieter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void jedz() {
        String kierunek;
        aktualnyPoziom += stan;
        if (stan > 0){
            kierunek = "gora";
        } else{
            kierunek = "dol";
        }
        System.out.println("Jade kierunek: " + kierunek + " poziom: " + aktualnyPoziom
                + " cel: " + cel + " pasazerow: " + lista_pasazerow.size());
    }

    private void stoj() {
        System.out.println("stoje na poziomie: " + aktualnyPoziom);
    }

    private void nasluchuj() {
        for(int i=0; i<pietra.length; i++){
            if (!pietra[i].getCzekajacyPasazerowie().isEmpty()){
                List<Pasazer> czekajacyPasazerowie = pietra[i].getCzekajacyPasazerowie();
                cel = czekajacyPasazerowie.get(0).getSkad();
                if (cel < aktualnyPoziom){
                    stan = -1;
                } else{
                    stan = 1;
                }
            }
        }
    }

    private void stanPieter() {
        for(int i=0; i<pietra.length; i++){
            if (!pietra[i].getCzekajacyPasazerowie().isEmpty()){
                List<Pasazer> czekaja = pietra[i].getCzekajacyPasazerowie();
                for (Pasazer p: czekaja) {
                    System.out.println("   Osoba czeka " + p.getInfo());
                }
            }
        }
    }

}
