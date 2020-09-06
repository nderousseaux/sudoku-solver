import javax.swing.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class Resolveur {

    public static Grille resoudre(Grille grille){

        //On copie la grille paramètre dans la grille retour
        Grille gr = Grille.copieGrille(grille);

        //Tant que la grille n'est pas finie :
        int tours = 0;
        while(!gr.gagne() && tours < 100){



            //Pour chaque cellule
            for (Cellule cel: gr.getLesCellules()){

                //Si elle est sure, on passe à la suivante

                //Si elle est ambigue, on regarde si on peut diminuer son incertitude
                if(cel.getType() == "CelluleAmbigue"){




                    //Si la cellule est fixé
                    if(((CelluleAmbigue)cel).getValeurs().size() == 1){
                        gr.setCellule(cel.getPositionAbs(), new CelluleFixee(cel.getPositionAbs(), ((CelluleAmbigue)cel).getValeurs().get(0)));


                    }

                    //Si elle reste ambigue
                    else {





                        ((CelluleAmbigue) cel).setValeurs(gr.valeursPossible(cel));


                        //On regarde si l'une des valeurs de la celulle est unique
                        for (int val:((CelluleAmbigue)cel).getValeurs()) {

                            int nbOccurences = 0;
                            //La valeur est-elle unique dans la ligne ?
                            for (Cellule celL : gr.getCelLigne(cel)) {
                                //Pour les ambigues
                                if(celL.getType() == "CelluleAmbigue"){
                                    for (int valCL :((CelluleAmbigue)celL).getValeurs()) {
                                        if(val == valCL){
                                            nbOccurences++;
                                        }
                                    }
                                }
                                else{
                                    if(val == ((CelluleFixee)celL).getValeur()){
                                        nbOccurences++;
                                    }
                                }
                            }
                            if(nbOccurences == 1){

                                gr.setCellule(cel.getPositionAbs(), new CelluleFixee(cel.getPositionAbs(), val));
                                break;
                            }

                            nbOccurences = 0;
                            //La valeur est-elle unique dans la colonne ?
                            for (Cellule celL : gr.getCelColonne(cel)) {
                                if(celL.getType() == "CelluleAmbigue"){
                                    for (int valCL :((CelluleAmbigue)celL).getValeurs()) {
                                        if(val == valCL){
                                            nbOccurences++;
                                        }
                                    }
                                }
                                else{
                                    if(val == ((CelluleFixee)celL).getValeur()){
                                        nbOccurences++;
                                    }
                                }
                            }
                            if(nbOccurences == 1){

                                gr.setCellule(cel.getPositionAbs(), new CelluleFixee(cel.getPositionAbs(), val));
                                break;
                            }

                            nbOccurences = 0;
                            //La valeur est-elle unique dans le groupe ?
                            for (Cellule celL : gr.getCelGroupe(cel)) {
                                if(celL.getType() == "CelluleAmbigue"){
                                    for (int valCL :((CelluleAmbigue)celL).getValeurs()) {
                                        if(val == valCL){
                                            nbOccurences++;
                                        }
                                    }
                                }
                                else{
                                    if(val == ((CelluleFixee)celL).getValeur()){
                                        nbOccurences++;
                                    }
                                }
                            }
                            if(nbOccurences == 1){

                                gr.setCellule(cel.getPositionAbs(), new CelluleFixee(cel.getPositionAbs(), val));
                                break;
                            }

                        }
                    }







                }

            }

            tours++;

        }

        if(tours>= 100){
            gr = null;
        }
        return  gr;
    }


}
