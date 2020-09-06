public abstract class Cellule {

    int position;

    public Cellule(int position){
        this.position = position;
    }

    public int getPositionAbs(){
        return this.position;
    }

    public int[] getPosition(){
        int x;
        int y;

        x = this.position%9;
        y = (this.position-x)/9;
        int[] tab = new int[2];
        tab[0] = x;
        tab[1] = y;

        return tab;
    }

    public int getGroupe(){
        int groupe;
        int colonneGroupe;
        int ligneGroupe;

        if(this.getPosition()[0] < 3){
            colonneGroupe = 0;
        }
        else if (this.getPosition()[0]<6){
            colonneGroupe = 1;
        }
        else{
            colonneGroupe = 2;
        }

        if(this.getPosition()[1] < 3){
            ligneGroupe = 0;
        }
        else if (this.getPosition()[1]<6){
            ligneGroupe = 1;
        }
        else{
            ligneGroupe = 2;
        }

        groupe = ligneGroupe*3 + colonneGroupe;
        return  groupe;


    }

    //Méthode d'instance
    public abstract Cellule copie();

    public abstract String getType();

    public abstract String affichage();


}
