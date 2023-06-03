package Controler;

import Model.Model;

import javax.swing.*;

public class ModelRenderer {
    private JTable jTable;



    private Model pacman;
    private Model pacmanDol;
    private Model pacmanLewo;
    private Model pacmanGora;
    private Model pacman0;
    private Model pacmanDol0;
    private Model pacmanLewo0;
    private Model pacmanGora0;
    private Model pacman1;
    private Model pacmanDol1;
    private Model pacmanLewo1;
    private Model pacmanGora1;
    private Model serce;
    private Model duszek;
    private static ImageIcon[][] pac;
    private Model[][] pacTab;
    private static ImageIcon[] serceIcon;
    private static ImageIcon[] duszekIcon;
    private GameTableCellRenderer gameTableCellRenderer;
    public ModelRenderer(JTable jTable, GameTableCellRenderer gameTableCellRenderer){
        this.jTable = jTable;
        this.gameTableCellRenderer = gameTableCellRenderer;
        setModels();
    }
    private void setModels(){
        pacman = new Model(jTable.getRowHeight(), "src\\Pictures\\pacman.png");
        pacmanDol = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanDol.png");
        pacmanLewo = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanLewo.png");
        pacmanGora = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanGora.png");
        pacman0 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacman0.png");
        pacmanDol0 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanDol0.png");
        pacmanLewo0 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanLewo0.png");
        pacmanGora0 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanGora0.png");
        pacman1 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacman1.png");
        pacmanDol1 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanDol1.png");
        pacmanLewo1 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanLewo1.png");
        pacmanGora1 = new Model(jTable.getRowHeight(), "src\\Pictures\\pacmanGora1.png");
        serce = new Model(jTable.getRowHeight(), "src\\Pictures\\serce.png");
        duszek = new Model(jTable.getRowHeight(), "src\\Pictures\\duszek.png");
        pac = new ImageIcon[][]{{new ImageIcon(pacman.getPacman()), new ImageIcon(pacman0.getPacman()), new ImageIcon(pacman1.getPacman())},
                {new ImageIcon(pacmanDol.getPacman()), new ImageIcon(pacmanDol0.getPacman()), new ImageIcon(pacmanDol1.getPacman())},
                {new ImageIcon(pacmanLewo.getPacman()), new ImageIcon(pacmanLewo0.getPacman()), new ImageIcon(pacmanLewo1.getPacman())},
                {new ImageIcon(pacmanGora.getPacman()), new ImageIcon(pacmanGora0.getPacman()), new ImageIcon(pacmanGora1.getPacman())}};
        pacTab = new Model[][]{{pacman, pacman0, pacman1}, {pacmanDol, pacmanDol0, pacmanDol1},
                {pacmanLewo, pacmanLewo0, pacmanLewo1}, {pacmanGora, pacmanGora0, pacmanGora1}};
        serceIcon = new ImageIcon[]{new ImageIcon(serce.getPacman())};
        duszekIcon = new ImageIcon[]{new ImageIcon(duszek.getPacman())};
    }

    public ImageIcon[][] getPac() {
        return pac;
    }
    public ImageIcon[] getSerceIcon() {
        return serceIcon;
    }

    public ImageIcon[] getDuszekIcon() {
        return duszekIcon;
    }
    public void resize(){
        int size = Math.min(jTable.getRowHeight(), jTable.getWidth()/jTable.getColumnCount());
        for(int i = 0; i<pac.length;i++) {
            Model pacman2 = new Model(size, pacTab[i][0].getPath());
            Model pacman3 = new Model(size, pacTab[i][1].getPath());
            Model pacman4 = new Model(size, pacTab[i][2].getPath());
            pac[i][0] = new ImageIcon(pacman2.getPacman());
            pac[i][1] = new ImageIcon(pacman3.getPacman());
            pac[i][2] = new ImageIcon(pacman4.getPacman());
        }
        Model serce2 = new Model(size, serce.getPath());
        serceIcon[0] = new ImageIcon(serce2.getPacman());
        Model duszek2 = new Model(size, duszek.getPath());
        duszekIcon[0] = new ImageIcon(duszek2.getPacman());
        gameTableCellRenderer.resize();
    }
}
