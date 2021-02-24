public class main {
    static int F = 4; //liczba pięter
    static int t = 2; //czas w s potrzebny do przemieszczenia windy o jedno piętro
    static double p = 0.2; //prawdopodobienstwo pojawienia sie pasazera w jednostce czasu t
    //na losowym pietrze z zamiarem podróży na inne, losowe pietro

    //dodatkowe parametry
    static int pojemnoscWindy = 10;
    static int liczbaPoziomow = 10;

    public Pietro pietra[] = new Pietro[liczbaPoziomow];

    public main() {
        for(int i=0; i<liczbaPoziomow; i++){
            pietra[i] = new Pietro();
        }
        Winda w = new Winda(t, pojemnoscWindy, pietra);
        new Thread(w).start();
        GenPasazerow g = new GenPasazerow(t, p, liczbaPoziomow, pietra);
        new Thread(g).start();
    }


    public static void main(String[] args) {
        new main();
    }
}
