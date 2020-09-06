public class CelluleFixee extends Cellule{

    //Attributs
    private int valeur;

    //Constructeurs
    public CelluleFixee(int position, int valeur){
        super(position);
        this.valeur = valeur;
    }

    //Assesseurs
    public int getValeur() {
        return valeur;
    }


    //Methodes d'instance
    public Cellule copie(){
        return new CelluleFixee(this.position, this.valeur);
    }

    public String getType(){
        return "CelluleFixee";
    }


    public String affichage(){
        String a = Integer.toString(this.valeur);

        return a;
    }
    public String toString(){
        int r = this.getValeur();

        return "Cellule fixée " + this.getPosition()[0] + ":" + this.getPosition()[1] + " : " + Integer.toString(r);
    }

}
