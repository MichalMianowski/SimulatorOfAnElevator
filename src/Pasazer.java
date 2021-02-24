public class Pasazer {
    private int skad;
    private int dokad;

    public Pasazer(int liczbaPieter) {
        skad = dokad = (int) (Math.random() * liczbaPieter);
        while(skad == dokad){
            dokad = (int) (Math.random() * liczbaPieter);
        }
    }

    /*
    1 - jedzie w gore
    -1 - jedzie w dol
     */
    public int getKierunek(){
        if(skad < dokad){
            return 1;
        } else{
            return -1;
        }
    }
    public int getSkad() {
        return skad;
    }

    public int getDokad() {
        return dokad;
    }
    public String getInfo() {
        return ("z " + getSkad() + " do " + getDokad());
    }
}
