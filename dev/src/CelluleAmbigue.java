import java.util.ArrayList;

public class CelluleAmbigue extends Cellule {

    //Attributs
    private ArrayList<Integer> valeurs;

    //Constructeurs
    public CelluleAmbigue(int position, ArrayList<Integer> valeurs){

        //On copie le paramètre dans l'attribut
        super(position);
        this.valeurs = valeurs;
    }


    //Assesseurs
    public void addValeur(int v){
        //Si la valeur n'existe pas déjà, on crée ajoute la valeur
        if(!this.valeurs.contains(v)){
            this.valeurs.add(v);
        }
        else{
            throw new IllegalArgumentException("Ce chiffre existe déjà dans la casse !");
        }
    }

    public void delValeur(int v){
        if(this.valeurs.contains(v)){
            this.valeurs.remove(v);
        }
        else{
            throw new IllegalArgumentException("Ce chiffre n'existe pas dans la case");
        }
    }

    public void setValeurs(ArrayList<Integer> valeurs){
        this.valeurs = valeurs;
    }

    public boolean existe(int v){
        return this.valeurs.contains(v);
    }

    public ArrayList<Integer> getValeurs(){
        return this.valeurs;
    }


    //Methodes d'instance
    public Cellule copie(){
        ArrayList<Integer> valeurs = new ArrayList<Integer>();
        for (int i:this.valeurs) {
            valeurs.add(i);
        }
        return new CelluleAmbigue(this.position, valeurs);
    }

    public String getType(){
        return "CelluleAmbigue";
    }


    public String affichage(){
        return " ";
    }

    public String toString(){
        return "Cellule Ambigue " + this.getPosition()[0] + ":" + this.getPosition()[1] +" : " + this.valeurs.toString();
    }

    public String toStringSol(){
        return "Cellule :" + this.getPositionAbs() + " ---" + this.valeurs.toString();
    }
}
