import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.ArrayList;

public class main {
    
    public static void main(String[] args){

        afficherFenetre();
    }


    public static void afficherFenetre(){
        //On initialise la fenetre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("R�solveur de Sudoku");
        fenetre.setSize(503, 600);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);

        fenetre.setIconImage(new ImageIcon("sudoku.jar\\sudo.png").getImage());

        //On cr�e tout ses composants
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Titre
        JLabel label = new JLabel("R�solveur de Sudoku !");
        label.setFont((new Font("Courier New", Font.BOLD, 20)));
        label.setBounds(89,10,300,20);
        label.setHorizontalAlignment(JLabel.CENTER);

        panel.add(label);

        //Traits pour les cases
        JLabel contour = new JLabel("");
        contour.setBounds(10,40,468,468);
        contour.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        panel.add(contour);


        //Trait des verticauxs
        int x = 10;
        int y = 40;
        int taille = 156;
        for(int i= 0; i<9; i++){
            JLabel groupe = new JLabel("");
            groupe.setBounds(x,y,taille,taille);
            groupe.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.add(groupe);
            if(i%3 == 2){
                x = 10;
                y += 156;
            }
            else{
                x+=156;
            }



        }

        //Traits verticaux
        JLabel vertical1 = new JLabel("");
        vertical1.setBounds(62, 40, 364,468);
        vertical1.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        panel.add(vertical1);

        JLabel vertical2 = new JLabel("");
        vertical2.setBounds(114, 40, 260,468);
        vertical2.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        panel.add(vertical2);

        JLabel vertical3 = new JLabel("");
        vertical3.setBounds(218, 40, 52,468);
        vertical3.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        panel.add(vertical3);


        //Traits horizontaux
        JLabel horizontal1 = new JLabel("");
        horizontal1.setBounds(10, 10,400,300);
        horizontal1.setBounds(10, 92,468,364);
        horizontal1.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.black));
        panel.add(horizontal1);

        JLabel horizontal2 = new JLabel("");
        horizontal2.setBounds(10, 144,468, 260);
        horizontal2.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.black));
        panel.add(horizontal2);

        JLabel horizontal3 = new JLabel("");
        horizontal3.setBounds(10, 248,468, 52);
        horizontal3.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.black));
        panel.add(horizontal3);


        //On ajoute les textbox
        x = 15;
        y = 45;
        ArrayList<JTextField> textBoxs = new ArrayList<JTextField>();
        for(int i =0; i<81; i++){
            JTextField text = new JTextField();
            text.setHorizontalAlignment(JTextField.CENTER);
            text.setFont(new Font("Arial", Font.PLAIN, 40));
            text.setBounds(x, y, 45, 45);
            textBoxs.add(text);
            panel.add(text);


            if(i%9 == 8){
                x = 15;
                y += 52;
            }
            else{
                x +=52;
            }
            if(i%6 == 5){
                x -= 1;
            }
            if(i%45 == 44){
                y -= 2;
            }




        }


        //Boutons
        //Bouton de nettoyage
        JButton bouton = new JButton("Nettoyer la grille");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nettoyage(textBoxs);
            }
        } );


        bouton.setBounds(10, 513, 150, 40);
        panel.add(bouton);

        JButton res = new JButton("Resoudre la grille");
        res.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resoudre(textBoxs);

            }
        } );

        res.setBounds(329, 513, 150, 40);
        panel.add(res);

        fenetre.add(panel);





        fenetre.setVisible(true);
    }

    public static void resoudre(ArrayList<JTextField> texts){

        ArrayList<String> s = new ArrayList<String>();

        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");
        s.add("6");
        s.add("7");
        s.add("8");
        s.add("9");
        s.add("");


        boolean marche = true;
        for (JTextField t:texts) {
            if(!s.contains(t.getText())){
                //On affiche une erreur
                javax.swing.JOptionPane.showMessageDialog(null,"Certaines cases n'ont pas �t� remplies correctement !");
                marche = false;
            }
        }

        if(marche){
            //On cr�e la grille
            int[] valeur = new int[81];
            for (int i = 0; i<81; i++) {

                if(texts.get(i).getText().isEmpty()) {

                    valeur[i] = 0;




                }


                else{

                    valeur[i] = Integer.parseInt(texts.get(i).getText());
                }
            }

            Grille gr = new Grille(valeur);
            System.out.println("Grille :");
            System.out.println(gr.toString());

            if(Resolveur.resoudre(gr) == null) {
                //Message d'erreur, imposible de r�soudre la grille
                System.out.println("Impossible de r�soudre la grille !");
                javax.swing.JOptionPane.showMessageDialog(null,"Impossible de r�soudre la grille !");
            }
            else{
                Grille grResou = Resolveur.resoudre(gr);
                //On affiche la grille r�solue
                System.out.println();
                System.out.println(grResou);


                //On l'implante dans l'interface
                for (int i = 0; i<81; i++) {
                    texts.get(i).setText(Integer.toString(((CelluleFixee)grResou.getCelluleAbs(i)).getValeur()));
                }
            }






        }

    }

    public static void nettoyage(ArrayList<JTextField> texts){
        for (JTextField t: texts) {
            t.setText("");
        }
    }
}
