package Model;

import javax.swing.*;
import java.awt.*;

public class Model {
    private int height;
    private ImageIcon pacman;
    private Image scaledImage;
    private String path;

    public Model(int height, String path){
        this.path = path;
        this.height = height;
        pacman = new ImageIcon(path);
        Image image = pacman.getImage();
        scaledImage = image.getScaledInstance(height, height, Image.SCALE_SMOOTH);
    }
    public String getPath(){
        return path;
    }
    public Image getPacman(){
        return scaledImage;
    }
}
