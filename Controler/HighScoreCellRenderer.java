package Controler;

import javax.swing.*;
import java.awt.*;

public class HighScoreCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setHorizontalAlignment(CENTER);
        return this;
    }
}
