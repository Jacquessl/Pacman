package Model;

import java.io.Serializable;

public class Player implements Serializable {
    private String nick;
    private int score;
    public Player(String nick, int score){
        this.nick = nick;
        this.score = score;
    }
    public String getNick(){
        return nick;
    }
    public int getScore(){
        return score;
    }
    public String toString(){
        return nick + " " + score;
    }
}
