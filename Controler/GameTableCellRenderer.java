package Controler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import static javax.swing.text.StyleConstants.setIcon;


public class GameTableCellRenderer extends DefaultTableCellRenderer {
    private final int[] counterZyc = {0};
    private final int[] counter = {0};
    private ImageIcon[][] pac;
    private ImageIcon[] serceIcon;
    private ImageIcon[] duszekIcon;
    private int direction = 0;
    private int state = 0;
    private int score = 0;
    private int zycia = 3;
    private String powerUp = "None";
    ModelRenderer modelRenderer;
    private int ss = 0;
    private int mm = 0;
    private int hh = 0;
    public GameTableCellRenderer(JTable jTable){
        this.modelRenderer = new ModelRenderer(jTable, this);
        resize();
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column){
        table.setFont(new Font(Font.MONOSPACED, Font.BOLD, 0));
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
        int borderLine = 0; //1-gora, 2-prawo 4-dol 8-lewo
        if(value.equals(1)){
            setFont(null);
            counterZyc[0] = 0;
            if(row==0 && column==0){
                if(!table.getValueAt(row, column+1).equals(1)){
                    borderLine+=2;
                }
                if(!table.getValueAt(row+1, column).equals(1)){
                    borderLine+=4;
                }
            } else if(row==0 && column==table.getColumnCount()-1){
                if(!table.getValueAt(row, column-1).equals(1)){
                    borderLine+=8;
                }
                if(!table.getValueAt(row+1, column).equals(1)){
                    borderLine+=4;
                }
            } else if (row==0){
                if(!table.getValueAt(row, column+1).equals(1)){
                    borderLine+=2;
                }
                if(!table.getValueAt(row, column-1).equals(1)){
                    borderLine+=8;
                }
                if(!table.getValueAt(row+1, column).equals(1)){
                    borderLine+=4;
                }
            } else if(row==table.getRowCount()-1 && column==0){
                if(!table.getValueAt(row, column+1).equals(1)){
                    borderLine+=2;
                }
                if(!table.getValueAt(row-1, column).equals(1)){
                    borderLine+=1;
                }
            } else if(row==table.getRowCount()-1 && column==table.getColumnCount()-1){
                if(!table.getValueAt(row, column-1).equals(1)){
                    borderLine+=8;
                }
                if(!table.getValueAt(row-1, column).equals(1)){
                    borderLine+=1;
                }
            } else if (row==table.getRowCount()-1){
                if(!table.getValueAt(row, column+1).equals(1)){
                    borderLine+=2;
                }
                if(!table.getValueAt(row, column-1).equals(1)){
                    borderLine+=8;
                }
                if(!table.getValueAt(row-1, column).equals(1)){
                    borderLine+=1;
                }
            } else if(column==0) {
                if (!table.getValueAt(row, column + 1).equals(1)) {
                    borderLine += 2;
                }
                if (!table.getValueAt(row - 1, column).equals(1)) {
                    borderLine += 1;
                }
                if (!table.getValueAt(row + 1, column).equals(1)) {
                    borderLine += 4;
                }
            }
            else if(column==table.getColumnCount()-1){
                if(!table.getValueAt(row, column-1).equals(1)){
                    borderLine+=8;
                }
                if(!table.getValueAt(row-1, column).equals(1)){
                    borderLine+=1;
                }
                if(!table.getValueAt(row+1, column).equals(1)){
                    borderLine+=4;
                }
            } else {
                if(!table.getValueAt(row, column-1).equals(1)){
                    borderLine+=8;
                }
                if(!table.getValueAt(row-1, column).equals(1)){
                    borderLine+=1;
                }
                if(!table.getValueAt(row+1, column).equals(1)){
                    borderLine+=4;
                }
                if(!table.getValueAt(row, column+1).equals(1)){
                    borderLine+=2;
                }
            }
            switch(borderLine){
                case 1->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLUE));
                case 2->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLUE));
                case 3->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, Color.BLUE));
                case 4->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
                case 5->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));
                case 6->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLUE));
                case 7->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.BLUE));
                case 8->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLUE));
                case 9->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.BLUE));
                case 10->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.BLUE));
                case 11->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.BLUE));
                case 12->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 2, 2, 0, Color.BLUE));
                case 13->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.BLUE));
                case 14->((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.BLUE));
                case 15->((JComponent) c).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
            }
            ((JLabel) c).setIcon(null);
        }
        else if(value.equals(0)){
            ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            setIcon(new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    g.setColor(Color.ORANGE);
                    int diameter = Math.min(getWidth(), getHeight()) / 3;
                    x = (getWidth() - diameter) / 2;
                    y = (getHeight() - diameter) / 2;
                    g.fillOval(x, y, diameter, diameter);
                }
                @Override
                public int getIconWidth() {
                    return Math.min(getWidth(), getHeight()) / 3;
                }

                @Override
                public int getIconHeight() {
                    return Math.min(getWidth(), getHeight()) / 3;
                }
            });
        }else if(value.equals(3)){
            JLabel pacLabel = new JLabel();
            pacLabel.setIcon(pac[direction][state]);
            table.setValueAt(pacLabel, row, column);
            pacLabel.setHorizontalAlignment(JLabel.CENTER);
            pacLabel.setVerticalAlignment(JLabel.CENTER);
            return pacLabel;
        }
        else if(value.equals(5)){
            JLabel heartLabel = new JLabel();
            if(counterZyc[0] <zycia) {
                heartLabel.setIcon(serceIcon[0]);
            }
            table.setValueAt(heartLabel, row, column);
            heartLabel.setHorizontalAlignment(JLabel.CENTER);
            heartLabel.setVerticalAlignment(JLabel.CENTER);
            counterZyc[0]++;
            heartLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLUE));
            return heartLabel;
        }
        else if(value.equals(6)){
            JLabel scoreLabel = new JLabel();
            int size = Math.min(table.getRowHeight(), table.getWidth()/table.getColumnCount());
            scoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, size/3));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setText("Score:");
            scoreLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLUE));
            table.setValueAt(scoreLabel, row, column);
            return scoreLabel;
        }
        else if(value.equals(7)){
            JLabel scoreLabel = new JLabel();
            int size = Math.min(table.getRowHeight(), table.getWidth()/table.getColumnCount());
            scoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, size/3));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setText(""+score);
            scoreLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLUE));
            table.setValueAt(scoreLabel, row, column);
            return scoreLabel;
        }
        else if(value.equals(8)){
            ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            setIcon(new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    g.setColor(Color.PINK);
                    int diameter = Math.min(getWidth(), getHeight()) / 2;
                    x = (getWidth() - diameter) / 2;
                    y = (getHeight() - diameter) / 2;
                    g.fillOval(x, y, diameter, diameter);
                }
                @Override
                public int getIconWidth() {
                    return Math.min(getWidth(), getHeight()) / 3;
                }

                @Override
                public int getIconHeight() {
                    return Math.min(getWidth(), getHeight()) / 3;
                }
            });
        }
        else if(value.equals(10)){
            JLabel powerUpLabel = new JLabel();
            int size = Math.min(table.getRowHeight(), table.getWidth()/table.getColumnCount());
            powerUpLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, size/3));
            powerUpLabel.setForeground(Color.WHITE);
            powerUpLabel.setText("Power up:");
            return powerUpLabel;
        }
        else if(value.equals(9)){
            JLabel powerUpLabel = new JLabel();
            int size = Math.min(table.getRowHeight(), table.getWidth()/table.getColumnCount());
            powerUpLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, size/3));
            powerUpLabel.setForeground(Color.WHITE);
            powerUpLabel.setText(powerUp);
            return powerUpLabel;
        }
        else if(value.equals(4)){
            JLabel duszekLabel = new JLabel();
            duszekLabel.setIcon(duszekIcon[0]);
            table.setValueAt(duszekLabel, row, column);
            duszekLabel.setHorizontalAlignment(JLabel.CENTER);
            duszekLabel.setVerticalAlignment(JLabel.CENTER);
            return duszekLabel;
        }
        else if(value.equals(12)){
            JLabel timerLabel = new JLabel();
            int size = Math.min(table.getRowHeight(), table.getWidth()/table.getColumnCount());
            timerLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, size/4));
            timerLabel.setForeground(Color.WHITE);
            String seconds = String.format("%02d", ss);
            String minutes = String.format("%02d", mm);
            String hours = String.format("%02d", hh);
            timerLabel.setText(hours+":"+minutes+":"+seconds);
            table.setValueAt(timerLabel, row, column);
            return timerLabel;
        }
        else if(value.equals(2)){
            setIcon(null);
        }
        else if(value.equals(11)){
            JLabel end = new JLabel();
            int size = Math.min(table.getRowHeight(), table.getWidth()/table.getColumnCount());
            end.setFont(new Font(Font.MONOSPACED, Font.BOLD, size));
            end.setForeground(Color.WHITE);
            if(counter[0] ==0) {
                end.setText("G");
            }else if(counter[0] ==1){
                end.setText("A");
            }else if(counter[0] ==2){
                end.setText("M");
            }else if(counter[0] ==3){
                end.setText("E");
            }else if(counter[0] ==4){
                end.setText(" ");
            }else if(counter[0] ==5){
                end.setText("O");
            }else if(counter[0] ==6){
                end.setText("V");
            }else if(counter[0] ==7){
                end.setText("E");
            }else if(counter[0] ==8){
                end.setText("R");
            }
            counter[0]++;
            table.setValueAt(end, row, column);
            return end;
        }
        return c;
    }
    public void setTime(){
        ss++;
        if(ss==60){
            ss=0;
            mm++;
        }
        if(mm==60){
            mm=0;
            hh++;
        }
    }
    public void setZycia(int zycia){
        this.zycia += zycia;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void setPowerUp(String powerUp){
        this.powerUp = powerUp;
    }
    public void resize(){
        //System.out.println("COS");
        this.pac = modelRenderer.getPac();
        this.serceIcon = modelRenderer.getSerceIcon();
        this.duszekIcon = modelRenderer.getDuszekIcon();
    }
    public void setDirection(int direction){
        this.direction = direction;
    }
    public void setState(int state){
        this.state = state;
    }
}
