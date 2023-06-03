package Watki;

import Watki.Move;

public class Ulepszenia extends Thread{
    private Move duszek;

    public Ulepszenia(Move duszek){
        this.duszek = duszek;
    }
    public void run(){
        while(!interrupted()){
            if(Math.random()<0.25){
                duszek.setLastValue(8);
            }
            try {
                sleep(5000);
            }catch (InterruptedException e){

            }
        }
    }
}
