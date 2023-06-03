package Watki;

import Controler.MoveControler;

import javax.swing.*;

public class Move extends Thread{

    private int direction;
    private JTable table;
    private int row;
    private int column;
    private MoveControler moveControler;
    private boolean player;
    private int counter = 0;
    private int modelID;
    private Object lastValue = 0;
    private Move[] otherMoves;
    private int speed;
    private boolean playerZabija = false;
    public Move(int direction, JTable table, int row, int column, MoveControler moveControler, boolean player, int modelID, Move[] otherMoves, int speed){
        this.direction = direction;
        this.table = table;
        this.row = row;
        this.column = column;
        this.moveControler = moveControler;
        this.player = player;
        this.modelID = modelID;
        this.otherMoves = otherMoves;
        this.speed = speed;
    }
    @Override
    public void run() {
        while (!interrupted()) {
            boolean playerDied = false;
            synchronized (table.getValueAt(row, column)) {
                if ((counter == (int) (Math.random() * 4 + 1) || counter == 5) && !player) {
                    changeDirection((int) (Math.random() * 4 + 1));
                    counter = 0;
                }
                if (!player && row == table.getRowCount() / 2 && column == table.getColumnCount() / 2) {
                    changeDirection(4);
                }
                if (direction == 1) {
                    if (player) {
                        table.setValueAt(2, row, column);
                    } else {
                        table.setValueAt(lastValue, row, column);
                    }
                    column++;
                    try {
                        if (!table.getValueAt(row, column).equals(1)) {
                            if (table.getValueAt(row, column).equals(0) && player) {
                                moveControler.setScore();
                            }
                            lastValue = table.getValueAt(row, column);
                            if (lastValue.equals(4)) {
                                if (!player) {
                                    for (int i = 0; i < otherMoves.length; i++) {
                                        if (otherMoves[i].getRow() == row && otherMoves[i].getColumn() == column) {
                                            lastValue = otherMoves[i].getLastValue();
                                        }
                                        if (!lastValue.equals(4)) {
                                            break;
                                        }
                                    }
                                }
                            }
                            table.setValueAt(modelID, row, column);
                        } else {
                            column--;
                            table.setValueAt(modelID, row, column);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        column = 0;

                            lastValue = table.getValueAt(row, column);

                        if (lastValue.equals(4)) {
                            if (!player) {
                                for (int i = 0; i < otherMoves.length; i++) {
                                    if (otherMoves[i].getRow() == row && otherMoves[i].getColumn() == column) {
                                        lastValue = otherMoves[i].getLastValue();
                                    }
                                    if (!lastValue.equals(4)) {
                                        break;
                                    }
                                }
                            }
                        }
                        table.setValueAt(modelID, row, column);
                        if (table.getValueAt(row, column).equals(0) && player) {
                            moveControler.setScore();
                        }

                    }
                } else if (direction == 2) {
                    if (player) {
                        table.setValueAt(2, row, column);
                    } else {
                        table.setValueAt(lastValue, row, column);
                    }
                    row++;
                    try {
                        if (!table.getValueAt(row, column).equals(1)) {
                            if (table.getValueAt(row, column).equals(0) && player) {
                                moveControler.setScore();
                            }

                                lastValue = table.getValueAt(row, column);

                            if (lastValue.equals(4)) {
                                if (!player) {
                                    for (int i = 0; i < otherMoves.length; i++) {
                                        if (otherMoves[i].getRow() == row && otherMoves[i].getColumn() == column) {
                                            lastValue = otherMoves[i].getLastValue();
                                        }
                                        if (!lastValue.equals(4)) {
                                            break;
                                        }
                                    }
                                }
                            }
                            table.setValueAt(modelID, row, column);
                        } else {
                            row--;
                            table.setValueAt(modelID, row, column);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        row = 1;
                        //if(!table.getValueAt(row, column).equals(8) || player) {
                            lastValue = table.getValueAt(row, column);
                        //}
                        if (lastValue.equals(4)) {
                            if (!player) {
                                for (int i = 0; i < otherMoves.length; i++) {
                                    if (otherMoves[i].getRow() == row && otherMoves[i].getColumn() == column) {
                                        lastValue = otherMoves[i].getLastValue();
                                    }
                                    if (!lastValue.equals(4)) {
                                        break;
                                    }
                                }
                            }
                        }
                        table.setValueAt(modelID, row, column);
                        if (table.getValueAt(row, column).equals(0) && player) {
                            moveControler.setScore();
                        }
                    }
                } else if (direction == 3) {
                    if (player) {
                        table.setValueAt(2, row, column);
                    } else {
                        table.setValueAt(lastValue, row, column);
                    }
                    column--;
                    try {
                        if (!table.getValueAt(row, column).equals(1)) {
                            if (table.getValueAt(row, column).equals(0) && player) {
                                moveControler.setScore();
                            }
                            //if(!table.getValueAt(row, column).equals(8) || player) {
                                lastValue = table.getValueAt(row, column);
                            //}
                            if (lastValue.equals(4)) {
                                if (!player) {
                                    for (int i = 0; i < otherMoves.length; i++) {
                                        if (otherMoves[i].getRow() == row && otherMoves[i].getColumn() == column) {
                                            lastValue = otherMoves[i].getLastValue();
                                        }
                                        if (!lastValue.equals(4)) {
                                            break;
                                        }
                                    }
                                }
                            }
                            table.setValueAt(modelID, row, column);
                        } else {
                            column++;
                            table.setValueAt(modelID, row, column);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        column = table.getColumnCount() - 1;
                       // if(!table.getValueAt(row, column).equals(8) || player) {
                            lastValue = table.getValueAt(row, column);
                       // }
                        if (lastValue.equals(4)) {
                            if (!player) {
                                for (int i = 0; i < otherMoves.length; i++) {
                                    if (otherMoves[i].getRow() == row && otherMoves[i].getColumn() == column) {
                                        lastValue = otherMoves[i].getLastValue();
                                    }
                                    if (!lastValue.equals(4)) {
                                        break;
                                    }
                                    if(i== otherMoves.length-1 && lastValue.equals(4)){
                                        lastValue = 2;
                                    }
                                }
                            }
                        }
                        table.setValueAt(modelID, row, column);
                        if (table.getValueAt(row, column).equals(0) && player) {
                            moveControler.setScore();
                        }
                    }
                } else {
                    if (player) {
                        table.setValueAt(2, row, column);
                    } else {
                        table.setValueAt(lastValue, row, column);
                    }
                    row--;
                    if (row != 0) {
                        if (!table.getValueAt(row, column).equals(1) && !table.getValueAt(row, column).equals(5)) {
                            if (table.getValueAt(row, column).equals(0) && player) {
                                moveControler.setScore();
                            }
                            //if(!table.getValueAt(row, column).equals(8) || player) {
                                lastValue = table.getValueAt(row, column);
                            //}
                            table.setValueAt(modelID, row, column);
                        } else {
                            row++;
                            table.setValueAt(modelID, row, column);
                        }
                    } else {
                        row = table.getRowCount() - 1;
                        table.setValueAt(modelID, row, column);
                        if (table.getValueAt(row, column).equals(0) && player) {
                            moveControler.setScore();
                        }
                    }
                }
                if(lastValue.equals(3) && !player && !playerZabija){
                    moveControler.setZycia();
                    lastValue = 2;
                    playerDied = true;
                }else if(lastValue.equals(3) && !player && playerZabija){
                    moveControler.setScore();
                    row = (table.getRowCount()-1)/2;
                    column = Math.random()>0.5?table.getColumnCount()/2-1:table.getColumnCount()/2+1;
                    lastValue = table.getValueAt(row, column);
                    table.setValueAt(4, row, column);
                }
                if(lastValue.equals(4) && player&& !playerZabija){
                    moveControler.setZycia();
                    lastValue = 2;
                    playerDied = true;
                }else if(lastValue.equals(4) && player&& playerZabija){
                    moveControler.setScore();
                    for(Move d : otherMoves){
                        if(d.getColumn() == column && d.getRow() == row){
                            d.setRow((table.getRowCount()-1)/2);
                            d.setColumn(Math.random()>0.5?table.getColumnCount()/2-1:table.getColumnCount()/2+1);
                            d.setLastValue((int)table.getValueAt(d.getRow(), d.getColumn()));
                            if (d.getLastValue().equals(4)) {
                                for (int i = 0; i < otherMoves.length; i++) {
                                    if (otherMoves[i].getRow() == d.getRow() && otherMoves[i].getColumn() == d.getColumn()) {
                                        d.setLastValue((int)otherMoves[i].getLastValue());
                                    }
                                    if (!d.getLastValue().equals(4)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    lastValue = 2;
                }
                if(lastValue.equals(8) && player){
                    int pick = (int)(Math.random()*5);
                    switch (pick) {
                        case 0 -> {
                            moveControler.setPowerUp("Bonus Speed");
                            speed = speed * 2;
                        }
                        case 1 -> {
                            moveControler.setPowerUp("Extra Life");
                            moveControler.dodajZycie();
                        }
                        case 2 -> {
                            moveControler.setPowerUp("2x Score");
                        }
                        case 3 -> {
                            moveControler.setPowerUp("Slow Ghosts");
                            for (int i = 0; i<otherMoves.length;i++) {
                                otherMoves[i].setSpeed();
                            }
                        }
                        case 4 -> {
                            moveControler.setPowerUp("EAT GHOSTS!");
                            playerZabija = true;
                            for(Move d : otherMoves){
                                d.zabijanie();
                            }
                            //mozna zjadac duszki;
                        }
                    }
                    lastValue = 2;
                }

            }
            if (direction == 0 && player && !playerDied) {
                moveControler.setDirection(direction);
            } else if(direction != 0 && player && !playerDied){
                moveControler.setDirection(direction - 1);
            }
            counter++;
            if(moveControler.getZycia()==0){
                interrupt();
            }
            try {
                sleep(2000/speed);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
    public void setPosition(int row, int column){
        this.row = row;
        this.column = column;
    }
    public void koniecZabijania(){
        playerZabija = false;
    }
    public void zabijanie(){
        playerZabija = true;
    }
    public void updateOtherMoves(Move[] otherMoves){
        this.otherMoves = otherMoves;
    }
    public void changeDirection(int direction){
        this.direction = direction;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void setColumn(int column){
        this.column = column;
    }
    public Object getLastValue(){
        return lastValue;
    }
    public void koniecSpeeda(boolean player){
        if(player){
            speed = speed/2;
        }else{
            speed = speed*2;
        }

    }
    public void setSpeed(){
        if(speed>1) {
            speed = speed / 2;
        }
    }
    public void setLastValue(int lastValue){
        Watki.WatekUlepszenia wu = new Watki.WatekUlepszenia(this.lastValue, row, column, table, moveControler, "None");
        wu.start();
        this.lastValue = lastValue;
    }

}
