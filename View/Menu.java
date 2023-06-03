package View;

import View.Game;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    public Menu(){
        generateFrame();
    }
    private void generateFrame(){

        JPanel jPanel = new JPanel();

        JButton b1 = new JButton("New Game");
        JButton b2 = new JButton("High Scores");
        JButton b3 = new JButton("Exit");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new HighScores());
                dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        b1.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW), new EmptyBorder(10, 10, 10, 10)));
        b2.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW), new EmptyBorder(10, 10, 10, 10)));
        b3.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.YELLOW), new EmptyBorder(10, 10, 10, 10)));

        b1.setForeground(Color.YELLOW);
        b2.setForeground(Color.YELLOW);
        b3.setForeground(Color.YELLOW);

        b1.setBackground(Color.BLUE);
        b2.setBackground(Color.BLUE);
        b3.setBackground(Color.BLUE);

        b1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        b2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        b3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.add(Box.createVerticalGlue());
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(b1);
        jPanel.add(Box.createVerticalGlue());
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(b2);
        jPanel.add(Box.createVerticalGlue());
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(b3);
        jPanel.add(Box.createVerticalGlue());
        jPanel.setBackground(Color.BLACK);
        add(jPanel);

        jPanel.setOpaque(true);

        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.BLACK);
        for(int i = 0; i<getWidth()/5;i++){
            g2d.setColor(Color.WHITE);
            int rand1 = (int)(Math.random()*(getWidth()-1));
            int rand2 = (int)(Math.random()*(getHeight()-1));
            g2d.fillOval(rand1, rand2, 2, 2);
        }
    }
    private void startGame(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
            String height = JOptionPane.showInputDialog(null, "Input height from 10 to 100", "Height", JOptionPane.PLAIN_MESSAGE);
            try {
                int h = Integer.parseInt(height);
                String width = JOptionPane.showInputDialog(null, "Input width from 10 to 100", "Width", JOptionPane.PLAIN_MESSAGE);
                int w = Integer.parseInt(width);
                if(h>=10 && h <= 100 && w>=10 && w<=100) {
                    int finalH = h;
                    int finalW = w;
                    SwingUtilities.invokeLater(() -> new Game(finalH, finalW));
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Input", "", JOptionPane.WARNING_MESSAGE);
                    SwingUtilities.invokeLater(Menu::new);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Incorrect Input", "", JOptionPane.WARNING_MESSAGE);
                SwingUtilities.invokeLater(Menu::new);
            }
        }catch (Exception e){
            String height = JOptionPane.showInputDialog(null, "Input height from 10 to 100", "Height", JOptionPane.PLAIN_MESSAGE);
            try {
                int h = Integer.parseInt(height);
                String width = JOptionPane.showInputDialog(null, "Input width from 10 to 100", "Width", JOptionPane.PLAIN_MESSAGE);
                int w = Integer.parseInt(width);
                if(h>=10 && h <= 100 && w>=10 && w<=100) {
                    int finalH = h;
                    int finalW = w;
                    SwingUtilities.invokeLater(() -> new Game(finalH, finalW));
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Input", "", JOptionPane.WARNING_MESSAGE);
                    SwingUtilities.invokeLater(Menu::new);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Incorrect Input", "", JOptionPane.WARNING_MESSAGE);
                SwingUtilities.invokeLater(Menu::new);
            }
        }
    }
}
