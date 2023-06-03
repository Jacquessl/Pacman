package Watki;

import Controler.GameTableCellRenderer;

public class TimeWatek extends Thread{
    private GameTableCellRenderer gameTableCellRenderer;

    public TimeWatek(GameTableCellRenderer gameTableCellRenderer){
        this.gameTableCellRenderer = gameTableCellRenderer;
    }
    public void run(){
        while(!interrupted()){
            try{
                sleep(1000);
            }
            catch (InterruptedException e){
                interrupt();
            }
            gameTableCellRenderer.setTime();
        }
    }
}
