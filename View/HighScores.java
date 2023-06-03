package View;

import Controler.HighScoreCellRenderer;
import Controler.Zapisywanie;
import Model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScores extends JFrame {
    public HighScores(){
        generateFrame();
    }
    private void generateFrame(){

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        Zapisywanie zapisywanie = new Zapisywanie();
        List<Player> playerss = zapisywanie.read();
        DefaultListModel<Player> listModel = new DefaultListModel<>();
        Collections.sort(playerss, Comparator.comparingInt(Player::getScore));
        Collections.reverse(playerss);
        for(Player p : playerss){
            listModel.addElement(p);
        }
        JList<Player> players = new JList<>(listModel);
        players.setCellRenderer(new HighScoreCellRenderer());
        players.setEnabled(false);
        players.setBackground(Color.BLACK);
        players.setForeground(Color.YELLOW);
        jPanel.setBackground(Color.BLACK);
        players.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));

        JLabel title = new JLabel();
        title.setText("High Scores");
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        title.setForeground(Color.YELLOW);

        jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(title);
        players.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane jScrollPane = new JScrollPane(players);
        jScrollPane.setBorder(null);
        jPanel.add(jScrollPane);
        add(jPanel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(Menu::new);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                    dispose();
                    SwingUtilities.invokeLater(Menu::new);
                }
            }
        });

        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
