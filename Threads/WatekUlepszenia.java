package Watki;

import Controler.MoveControler;

import javax.swing.*;

public class WatekUlepszenia extends Thread{
    private Object value;
    private int row;
    private int column;
    private JTable jTable;
    private MoveControler moveControler;
    private String powerUP;
    private String lastPowerUP;
    private String nextPowerUP;
    public WatekUlepszenia(Object value, int row, int column, JTable table, MoveControler moveControler, String powerUP){
        this.value = value;
        this.row = row;
        this.column = column;
        this.jTable = table;
        this.powerUP = powerUP;
        this.moveControler = moveControler;
        nextPowerUP = "None";
    }
    public void run(){
        while(!interrupted()){
            try{
                sleep(10000);
            }catch (InterruptedException e){

            }
            if(powerUP.equals("Bonus Speed")){
                moveControler.koniecSpeeda(true);
            }else if(powerUP.equals("Slow Ghosts")){
                moveControler.koniecSpeeda(false);
            }else if(powerUP.equals("EAT GHOSTS!")){
                moveControler.koniecZabijania();
            }
            if(jTable.getValueAt(row, column).equals(8)){
                jTable.setValueAt(value, row, column);
            }
            moveControler.setStringPowerUp(nextPowerUP);

            interrupt();
        }
    }
    public String getPowerUP(){
        return powerUP;
    }
    public void setNextPowerUP(String powerUP){
        nextPowerUP=powerUP;
    }
}
