package Controler;

import Model.Mapa;
import Model.Player;
import Model.GameTableModel;
import View.Game;
import View.Menu;
import Watki.Move;
import Watki.TimeWatek;
import Watki.Ulepszenia;
import Watki.WatekUlepszenia;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

public class MoveControler {
    private JTable jTable;
    private GameTableCellRenderer gameTableCellRenderer;
    private int direction = 0;
    private int state = 0;
    private int score = 0;
    private int zycia = 3;
    private String powerUp = "None";
    private int level = 1;
    private boolean koniec = false;
    private Game game;
    private int height;
    private int width;
    private static Move movePac;
    private Move[] duszki;
    private int indexI;
    private int indexJ;
    private WatekUlepszenia wu;
    private TimeWatek timeWatek;
    public MoveControler(JTable jTable, GameTableCellRenderer gameTableCellRenderer, Game game, int height, int width){
        this.jTable = jTable;
        this.gameTableCellRenderer = gameTableCellRenderer;
        this.game = game;
        this.height = height;
        this.width = width;
        wu = new WatekUlepszenia(0,0,0, jTable, this, "None");
    }
    public void go(){
        int ileDuszkow = (Math.max(height, width))/4>3?(Math.max(height, width))/4:4;
        duszki = new Move[ileDuszkow];
        for(int i = 0; i<duszki.length; i++){
            indexI = (int)(Math.random()* jTable.getRowCount());
            indexJ = (int)(Math.random()* jTable.getColumnCount());
            if(jTable.getValueAt(indexI, indexJ).equals(0)){
                jTable.setValueAt(4, indexI, indexJ);
                duszki[i] = new Move((int)(Math.random()*4+1), jTable, indexI, indexJ, this, false, 4, duszki, 5);
            }
            else{
                i--;
            }
        }
        setPacman();
        movePac = new Move(1, jTable, indexI , indexJ, this, true, 3, duszki, 5);
        movePac.start();
        for(Move d : duszki){
            d.updateOtherMoves(duszki);
            Ulepszenia u = new Ulepszenia(d);
            u.start();
            d.start();
        }
        timeWatek = new TimeWatek(gameTableCellRenderer);
        timeWatek.start();
        jTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
                    MoveControler.setPacDirection(1);
                }else if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN){
                    MoveControler.setPacDirection(2);
                }
                else if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
                    MoveControler.setPacDirection(3);
                }else if(e.getExtendedKeyCode() == KeyEvent.VK_UP){
                    MoveControler.setPacDirection(4);
                }
                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                    String nick = JOptionPane.showInputDialog(null, "Input your username", "Nick", JOptionPane.PLAIN_MESSAGE);
                    if(nick != null && (!"".equals(nick))) {
                        Zapisywanie zapisywanie = new Zapisywanie();
                        int score = gameTableCellRenderer.getScore();
                        zapisywanie.save(new Player(nick, score));
                        movePac.interrupt();
                        for (Move d : duszki) {
                            d.interrupt();
                        }
                        timeWatek.interrupt();
                        game.dispose();
                        SwingUtilities.invokeLater(Menu::new);
                        game.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void koniecZabijania(){
        movePac.koniecZabijania();
        for(Move d : duszki){
            d.koniecZabijania();
        }
    }
    public void setDirection(int direction){
        this.direction = direction;
        for(int i = 0; i<jTable.getRowCount();i++){
            for(int j = 0; j<jTable.getColumnCount();j++){
                if(jTable.getValueAt(i, j).equals(0)){
                    koniec = false;
                    i = jTable.getRowCount();
                    break;
                }
            }
        }
        if(koniec){
            level++;
            Mapa nMapa = new Mapa(height, width, level);
            int[][] mapa = nMapa.getMap();
            jTable.setModel(new GameTableModel(mapa));
        }
        if(state == 2){
            state = 0;
            gameTableCellRenderer.setState(state);
        }
        else {
            state++;
            gameTableCellRenderer.setState(state);
        }
        koniec = true;
        gameTableCellRenderer.setDirection(direction);
    }
    public void setScore(){
        if(wu.getPowerUP().equals("2x Score")){
            score += 200 * level;
        }
        else {
            score += 100 * level;
        }
        gameTableCellRenderer.setScore(score);
    }
    public void setZycia() {
        zycia--;
        gameTableCellRenderer.setZycia(-1);
        setPacman();
        if(zycia<1){
            int srodek = jTable.getColumnCount()/2;
            jTable.setValueAt(11,0,srodek-4);
            jTable.setValueAt(11,0,srodek-3);
            jTable.setValueAt(11,0,srodek-2);
            jTable.setValueAt(11,0,srodek-1);
            jTable.setValueAt(11,0,srodek);
            jTable.setValueAt(11,0,srodek+1);
            jTable.setValueAt(11,0,srodek+2);
            jTable.setValueAt(11,0,srodek+3);
            jTable.setValueAt(11,0,srodek+4);
            String nick = JOptionPane.showInputDialog(null, "Input your username", "Nick", JOptionPane.PLAIN_MESSAGE);
            Zapisywanie zapisywanie = new Zapisywanie();
            int score = gameTableCellRenderer.getScore();
            zapisywanie.save(new Player(nick, score));
            movePac.interrupt();
            for(Move d: duszki){
                d.interrupt();
            }
            timeWatek.interrupt();
            game.dispose();
            SwingUtilities.invokeLater(Menu::new);
        }
        else{
            movePac.setPosition(indexI, indexJ);
        }
    }
    public void dodajZycie(){
        zycia++;
        gameTableCellRenderer.setZycia(1);
    }
    public int getZycia(){
        return zycia;
    }
    public void setPowerUp(String powerUP){
        this.powerUp = powerUP;
        if(!powerUP.equals("None")){
            wu.setNextPowerUP(powerUP);
            wu = new WatekUlepszenia(0,0,0, jTable, this, powerUP);
            wu.start();
        }
        gameTableCellRenderer.setPowerUp(powerUp);
    }
    public void setStringPowerUp(String powerUp){
        this.powerUp = powerUp;
        gameTableCellRenderer.setPowerUp(powerUp);
    }
    public void koniecSpeeda(boolean player){
        if(player){
            movePac.koniecSpeeda(true);
        }else{
            for(Move d : duszki){
                d.koniecSpeeda(false);
            }
        }
    }
    private void setPacman(){
        for(int i = 2 ;i <jTable.getRowCount();i++){
            for(int j = 0; j< jTable.getColumnCount();j++){
                if(jTable.getValueAt(i,j).equals(0)){
                    jTable.setValueAt(3, i,j );
                    indexI = i;
                    indexJ = j;
                    i=jTable.getRowCount();
                    break;
                }
            }
        }
    }
    public void koniecInterrupt(){
        movePac.interrupt();
        for(Move d: duszki){
            d.interrupt();
        }
        timeWatek.interrupt();
    }
    public static void setPacDirection(int direction){
        movePac.changeDirection(direction);
    }
}
