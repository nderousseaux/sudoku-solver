import jdk.jfr.Frequency;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Grille {

    //Attributs
    private Cellule[] lesCellules;


    //Constructeurs
    //Les cellules vide seront des 0
    public Grille(){
        this.lesCellules = new Cellule[81];

    }
    public Grille(int[] lesCellules){

        this.lesCellules = new Cellule[81];
        for(int i = 0; i<this.lesCellules.length; i++){
            if(lesCellules[i] != 0){
                this.lesCellules[i] = new CelluleFixee(i, lesCellules[i]);
            }
            else{
                this.lesCellules[i] = new CelluleFixee(i, 0);
            }
        }


        //On liste les valeurs possible pour chaque cellule ambigue
        for (Cellule cel: this.lesCellules) {
            if(((CelluleFixee)cel).getValeur() == 0){

                this.lesCellules[cel.getPositionAbs()] = new CelluleAmbigue(cel.getPositionAbs(), this.valeursPossible(cel));
            }
        }



    }


    public ArrayList<Integer> valeursPossible(Cellule cel){
        //On crée une liste de toutes les valeurs que l'on ne peut pas mettre dans cette case
        ArrayList<Integer> valeursImp = new ArrayList<>();

        //On regarde la ligne
        for (int val : this.getValeursLigne(cel)) {
            if(!valeursImp.contains(val)){
                valeursImp.add(val);
            }
        }


        //On regarde la colonne
        for (int val : this.getValeursColonne(cel)) {
            if(!valeursImp.contains(val)){
                valeursImp.add(val);

            }
        }

        //On regarde le groupe
        for (int val : this.getValeursGroupe(cel)) {
            if(!valeursImp.contains(val)){
                valeursImp.add(val);
            }
        }


        valeursImp.remove((Object)0);


        //A partir des valeurs impossible, on en déduis les valeurs possible
        ArrayList<Integer> valeursPossible = new ArrayList<Integer>();
        valeursPossible.add(1);
        valeursPossible.add(2);
        valeursPossible.add(3);
        valeursPossible.add(4);
        valeursPossible.add(5);
        valeursPossible.add(6);
        valeursPossible.add(7);
        valeursPossible.add(8);
        valeursPossible.add(9);



        valeursPossible.removeAll(valeursImp);


        return valeursPossible;
    }

    //Assesseurs
    public Cellule getCellule(int x, int y){
        int index = x*9+y;
        return lesCellules[index];
    }
    public Cellule getCelluleAbs(int i){

        return lesCellules[i];
    }
    public void setCellule(int pos, Cellule cellule){

        lesCellules[pos] = cellule;
    }
    public Cellule[] getLesCellules(){
        return this.lesCellules;
    }


    //Méthdodes d'instances //TODO: Faire une fonction qui vérifie que tout les chiffres ne sont présent qu'une fois (ligne, colonne et case)
    public boolean gagne(){

        Boolean retour = false;

        int i = 0;
        for (Cellule cel: this.lesCellules) {
            if(cel.getType() == "CelluleFixee"){
                i++;
            }
        }

        if(i < 81){
            retour = false;
        }

        //Si la grille fait bien 84 cases, on teste sa justesse (toutes ses chiffres sont présent une seule fois (ligne, colonne et case))
        else if(i == 81){
            retour = true;
            for (Cellule cel:this.lesCellules) {

                if(Collections.frequency(this.getValeursGroupe(cel), ((CelluleFixee) cel).getValeur()) != 1){
                    retour = false;
                    break;
                }

                if(Collections.frequency(this.getValeursColonne(cel), ((CelluleFixee) cel).getValeur()) != 1){
                    retour = false;
                    break;
                }
                if(Collections.frequency(this.getValeursLigne(cel), ((CelluleFixee) cel).getValeur()) != 1){
                    retour = false;
                    break;
                }
            }
        }



        return retour;
    }

    //Fonction qui renvoie la valeur de toutes les cellules fixée de la ligne
    public ArrayList<Integer> getValeursLigne(Cellule cel){

        ArrayList<Integer> valeursLigne = new ArrayList<Integer>();

        for (Cellule celG: this.lesCellules) {
            if(celG.getType() == "CelluleFixee" && celG.getPosition()[0] == cel.getPosition()[0]){
                valeursLigne.add(((CelluleFixee)celG).getValeur());
            }
        }

        return valeursLigne;
    }

    //Fonction qui renvoie la valeur de toutes les cellules fixée de la colonne
    public ArrayList<Integer> getValeursColonne(Cellule cel){

        ArrayList<Integer> valeursColonne = new ArrayList<Integer>();

        for (Cellule celG: this.lesCellules) {
            if(celG.getType() == "CelluleFixee" && celG.getPosition()[1] == cel.getPosition()[1]){
                valeursColonne.add(((CelluleFixee)celG).getValeur());
            }
        }

        return valeursColonne;
    }

    //Fonction qui renvoie la valeur de toutes les cellules fixée du groupe
    public ArrayList<Integer> getValeursGroupe(Cellule cel){

        ArrayList<Integer> valeursGroupe = new ArrayList<Integer>();

        for (Cellule celG: this.lesCellules) {
            if(celG.getType() == "CelluleFixee" && celG.getGroupe() == cel.getGroupe()){
                valeursGroupe.add(((CelluleFixee)celG).getValeur());
            }
        }

        return valeursGroupe;
    }

    //Fonction qui renvoie toutes les cellules de la ligne
    public ArrayList<Cellule> getCelLigne(Cellule cel){

        ArrayList<Cellule> CLigne = new ArrayList<>();

        for (Cellule celG: this.lesCellules) {
            if(celG.getPosition()[1] == cel.getPosition()[1]){
                CLigne.add(celG);
            }
        }

        return CLigne;
    }
    //Fonction qui renvoie toutes les cellules de la Colonne
    public ArrayList<Cellule> getCelColonne(Cellule cel) {

        ArrayList<Cellule> CColonne = new ArrayList<>();

        for (Cellule celG : this.lesCellules) {
            if (celG.getPosition()[0] == cel.getPosition()[0]) {
                CColonne.add(celG);
            }
        }

        return CColonne;
    }

    //Fonction qui renvoie toutes les cellules du groupe
    public ArrayList<Cellule> getCelGroupe(Cellule cel) {

        ArrayList<Cellule> CLigne = new ArrayList<Cellule>();

        for (Cellule celG : this.lesCellules) {
            if (celG.getGroupe() == cel.getGroupe()) {
                CLigne.add(celG);
            }
        }

        return CLigne;
    }
    //Methode de classe
    public static Grille copieGrille(Grille grille){
        Grille grilleRetour = new Grille();
        for(int i = 0; i<grille.getLesCellules().length; i++){
            grilleRetour.getLesCellules()[i] = grille.getLesCellules()[i].copie();
        }

        return grilleRetour;
    }



    public String toString(){
        String s = "";
        int i = 0;
        int j = 0;
        for (Cellule cel: this.lesCellules) {
            s += cel.affichage();

            if(i <=7){
                i++;
                if(i%3 == 0){
                    s+= " | ";
                }
                else{
                    s += " ";
                }

            }
            else{
                j++;
                s += "\n";
                i = 0;
                if(j%3 == 0 && j != 9){
                    s+= "--------------------- \n";
                }
            }

        }
        return s;

    }

}
