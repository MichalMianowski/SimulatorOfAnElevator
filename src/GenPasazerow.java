public class GenPasazerow extends Thread{
    private int t;
    private double p;
    private int liczbaPieter;
    private Pietro pietra[];

    public GenPasazerow(int t, double p, int liczbaPieter, Pietro pietra[]) {
        this.t = t;
        this.p = p;
        this.liczbaPieter = liczbaPieter;
        this.pietra = pietra;
    }

    public void run() {
        while (true){
            generuj();
        }
    }

    private void generuj() {
        try {
            sleep(t*1000);
            if (losuj()){
                System.out.println("   Nowa osoba czeka");
                dodajPasazera();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sleep(){
        try {
            Thread.sleep(t*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private boolean losuj(){
        return (Math.random() < p);
    }
    private void dodajPasazera(){
        Pasazer pasazer = new Pasazer(liczbaPieter);
        pietra[pasazer.getSkad()].addToCzekajacyPasazerowie(pasazer);
    }
}
