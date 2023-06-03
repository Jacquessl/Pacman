package View;

import Controler.GameTableCellRenderer;
import Controler.ModelRenderer;
import Controler.MoveControler;
import Model.Mapa;
import Model.Player;
import Model.GameTableModel;
import Controler.Zapisywanie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame {

    private int height;
    private int width;
    private JTable jTable;
    public Game(int height, int width){
        this.height = height;
        this.width = width;
        generateFrame();
    }
    private void generateFrame(){
        Mapa nMapa = new Mapa(height, width,1);
        int[][] mapa = nMapa.getMap();
        jTable = new JTable();

        jTable.setModel(new GameTableModel(mapa));
        jTable.setCellSelectionEnabled(false);
        jTable.setBorder(null);
        jTable.setBackground(Color.BLACK);
        jTable.setForeground(Color.BLACK);
        jTable.setShowGrid(false);


        GameTableCellRenderer gameTableCellRenderer = new GameTableCellRenderer(jTable);
        ModelRenderer modelRenderer = new ModelRenderer(jTable, gameTableCellRenderer);
        jTable.setDefaultRenderer(Object.class, gameTableCellRenderer);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                jTable.setRowHeight((getHeight()-getInsets().top)/jTable.getRowCount());
                modelRenderer.resize();
            }
        });

        MoveControler moveControler = new MoveControler(jTable, gameTableCellRenderer, this, height, width);
        moveControler.go();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                moveControler.koniecInterrupt();
                String nick = JOptionPane.showInputDialog(null, "Input your username", "Nick", JOptionPane.PLAIN_MESSAGE);
                Zapisywanie zapisywanie = new Zapisywanie();
                int score = gameTableCellRenderer.getScore();
                zapisywanie.save(new Player(nick, score));
                SwingUtilities.invokeLater(Menu::new);
            }
        });
        add(jTable);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(10*jTable.getColumnCount()+100, 10*jTable.getRowCount()+20);
        jTable.setPreferredSize(new Dimension(500,500));
    }
}
