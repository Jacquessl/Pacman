package Controler;

import Model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Zapisywanie implements Serializable {
    public void save(Player player) {
        try {
            List<Player> players = read();
            players.add(player);
            FileOutputStream fo = new FileOutputStream("HighScores.ser");

            ObjectOutputStream oo = new ObjectOutputStream(fo);
            for(Player p : players) {
                oo.writeObject(p);
            }
        } catch (IOException e) {

        }
    }
    public List<Player> read(){
        Player player = null;
        List<Player> deserializedPlayers = new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream("HighScores.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            boolean endOfFile = false;
            while(!endOfFile) {
                try {
                    player = (Player) oi.readObject();
                    deserializedPlayers.add(player);
                } catch(EOFException e){
                    endOfFile = true;
                }

            }
            fi.close();
        } catch (IOException | ClassNotFoundException e) {

        }
        return deserializedPlayers;
    }
}
