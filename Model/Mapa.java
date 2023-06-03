package Model;

import java.util.Arrays;

public class Mapa {
    private int height;
    private int width;
    private int[][] map;
    private int level;
    public Mapa(int height, int width, int level){
        this.height = height;
        this.width = width;
        map = new int[height+1][width];
        this.level = level;
        generateMap();
    }

    public void generateMap(){
        for(int i = 0; i<map.length;i++) {
            Arrays.fill(map[i], 0);
        }
        int srodekH = height/2;
        int srodekW = width/2;
        int size = Math.max(srodekH, srodekW);
        for(int i = 0;i<size*level*20;i++){
            int rand1 = (int)(Math.random()*height+1);
            int rand2 = (int)(Math.random()*width);
            int rand3 = (int)(Math.random()*width);
            int rand4 = (int)(Math.random()*height+1);
            if (Math.random() > 0.5) {
                setKwadrat(rand1 * width + rand2, rand1 * width + rand3, 1);
            } else {
                setKwadrat(rand1 * width + rand2, rand4 * width + rand2, 1);
            }
        }
        setTrasa();
        checkBorder();
        wyszukajSlepychUliczek();
        Arrays.fill(map[0], 5);
        map[0][0] = 6;
        map[0][1] = 7;
        map[0][map[0].length-1] = 9;
        map[0][map[0].length-2] = 10;
        map[0][map[0].length-3] = 12;
        setKwadrat((srodekH-1)*width+srodekW-2, (srodekH+1)*width+srodekW+2, 1);
        map[srodekH-1][srodekW] = 0;
        setKwadrat((srodekH)*width+srodekW-1,(srodekH)*width+srodekW+1, 0);
        setKwadrat((srodekH-2)*width+srodekW-3, (srodekH+2)*width+srodekW+3, 0);
    }
    private void setKwadrat(int pos1, int pos2, int value){
        int x1 = pos1/width;
        int x2 = pos2/width;
        if(x2>x1){
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        int y1 = pos1%width;
        int y2 = pos2%width;
        if(y2>y1){
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        int distanceY = x1-x2;
        int distanceX = y1-y2;

        for(int i = 0; i<=distanceY;i++){
            if(i==0 || i==distanceY){
                for(int j = 0; j<=distanceX;j++) {
                    map[x2 + i][y2 + j] = value;
                }
            }
            map[x2 + i][y2] = value;
            map[x2 + i][y2 + distanceX] = value;
        }
    }
    private void checkBorder(){
        map[1][0] = 1;
        map[1][width-1] = 1;
        map[map.length-1][0] = 1;
        map[map.length-1][width-1] = 1;
        for(int i = 1; i<height+1;i++) {
            if(i==1) {
                for (int j = 1; j < map[i].length-1; j++) {
                    if (map[1][j] == 0) {
                        map[map.length-1][j] = 0;
                        map[map.length-1][j + 1] = 1;
                        map[1][j + 1] = 1;
                    }
                }
            }
            else if (i == map.length-1) {
                for (int j = 1; j < width-1; j++) {
                    if (map[map.length-1][j] == 0) {
                            map[1][j] = 0;
                            map[map.length-1][j + 1] = 1;
                            map[1][j + 1] = 1;
                    }
                }
            } else {
                if (map[i][0] == 0) {
                    map[i][width - 1] = 0;
                    map[i + 1][0] = 1;
                    map[i + 1][width - 1] = 1;
                } else if (map[i][width - 1] == 0) {
                    map[i][0] = 0;
                    map[i + 1][0] = 1;
                    map[i + 1][width - 1] = 1;
                }
            }
        }
    }
    private void setTrasa(){
        int[] valueKolumn = new int[width];
        for(int i = 0; i < height; i++){
            int value = 0;
            for(int j = 0; j < width; j++){
                checkPattern(i,j);
                if(map[i][j]==0){
                    checkPrzejscie(i,j);
                }
                valueKolumn[j] += map[i][j];
                value+=map[i][j];
            }
            if(i!=0&&i!=height-1){
                if(value==width){
                    generateMap();
                }
            }
        }
        for(int i = 1; i<valueKolumn.length-1;i++){
            if(valueKolumn[i]>=height-2 || valueKolumn[i] == 0){
                //generateMap();
            }
        }
    }
    private void checkPattern(int i, int j){
        try{
            if(map[i-1][j]==map[i][j]&&map[i+1][j]==map[i][j]){
                if(Math.random()>0.5)
                    map[i][j] = 1 - map[i][j];
            }
            else if(map[i][j-1]==map[i][j]&&map[i][j+1]==map[i][j]){
                if(Math.random()>0.5)
                    map[i][j] = 1 - map[i][j];
            }
        }catch (IndexOutOfBoundsException e){

        }
    }
    private void checkPrzejscie(int i, int j){
        int value = 0;
        int counter = 4;
        boolean mamyPrzejscie = false;
        if(i==0){
            map[i+1][j] = 0;
            mamyPrzejscie = true;
        }
        else if(i==height-1){
            map[i-1][j] = 0;
            mamyPrzejscie = true;
        }
        else if(j==0){
            map[i][j+1] = 0;
            mamyPrzejscie = true;
        }
        else if(j==width-1){
            map[i][j-1] = 0;
            mamyPrzejscie = true;
        }
        else {
            value += map[i - 1][j];
            value += map[i][j - 1];
            value += map[i][j + 1];
            value += map[i + 1][j];
            if(value==2||value==3){
                if(map[i - 1][j]==1 && map[i][j - 1]==1 && map[i-1][j-1]==0){
                    if(Math.random()<0.5){
                        map[i-1][j]=0;
                    }
                    else{
                        map[i][j-1] = 0;
                    }
                }
                else if(map[i - 1][j]==1 && map[i][j + 1]==1 && map[i-1][j+1]==0){
                    if(Math.random()<0.5){
                        map[i-1][j]=0;
                    }
                    else{
                        map[i][j+1] = 0;
                    }
                }
                else if(map[i + 1][j]==1 && map[i][j + 1]==1 && map[i+1][j+1]==0){
                    if(Math.random()<0.5){
                        map[i+1][j]=0;
                    }
                    else{
                        map[i][j+1] = 0;
                    }
                }
                else if(map[i + 1][j]==1 && map[i][j - 1]==1 && map[i+1][j-1]==0){
                    if(Math.random()<0.5){
                        map[i+1][j]=0;
                    }
                    else{
                        map[i][j-1] = 0;
                    }
                }
                mamyPrzejscie = true;
            }
        }
        if(value==counter && !mamyPrzejscie){
            int rand = (int)(Math.random()*4);
            switch (rand){
                case 0:{
                    map[i - 1][j] = 0;
                    break;
                }
                case 1:{
                    map[i][j-1] = 0;
                    break;
                }
                case 2:{
                    map[i][j+1] = 0;
                    break;
                }
                case 3:{
                    map[i + 1][j] = 0;
                    break;
                }
            }
        }
    }
    private void wyszukajSlepychUliczek(){
        for(int i = 1; i<height+1; i++){
            for(int j = 0;j<width;j++){
                if(map[i][j]==0){
                    idz(i,j);
                }
            }
        }
    }
    private void idz(int x, int y){
        int startowyX = x;
        int startowyY = y;
        int size = Math.max(height, width);
        int[] kroki = (size/4)>4?new int[size/4]:new int[5];
        for(int i = 0; i<kroki.length;i++){
            if(x==1){
                if(map[x+1][y]==0 && bylesTam(2,y,kroki)){
                    kroki[i] = x*(width+1)+y;
                    x = 2;
                }
                else if(map[height][y]==0 && bylesTam(height,y,kroki)){
                    kroki[i] = x*(width+1)+y;
                    x = height;
                }
                else if(startowyX!=-1){
                    kroki[i] = x*(width+1)+y;
                    x = startowyX;
                    y = startowyY;
                    startowyX = -1;
                    startowyY = -1;
                }
                else{
                    if(bylesTam(2,y,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[2][y] = 0;
                        x = 2;
                    }
                }
            }
            else if(x==height){
                if(map[height-1][y]==0 && bylesTam(height-1,y,kroki)){
                    kroki[i] = x*(width+1)+y;
                    x = height-1;
                }
                else if (map[1][y]==0 && bylesTam(1,y,kroki)){
                    kroki[i] = x*(width+1)+y;
                    x = 1;
                }
                else if(startowyX!=-1){
                    kroki[i] = x*(width+1)+y;
                    x = startowyX;
                    y = startowyY;
                    startowyX = -1;
                    startowyY = -1;
                }
                else{
                    if(bylesTam(height-1,y,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[height-1][y] = 0;
                        x = height-1;
                    }
                }
            }
            else if(y==0){
                if(map[x][y+1]==0 && bylesTam(x,1,kroki)){
                    kroki[i] = x*(width+1)+y;
                    y = 1;
                }
                else if(map[x][width-1]==0 && bylesTam(x,width-1,kroki)){
                    kroki[i] = x*(width+1)+y;
                    y= width-1;
                }
                else if(startowyX!=-1){
                    kroki[i] = x*(width+1)+y;
                    x = startowyX;
                    y = startowyY;
                    startowyX = -1;
                    startowyY = -1;
                    i--;
                }
                else{
                    if(bylesTam(x,1,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[x][1] = 0;
                        y = 1;
                    }
                }
            }
            else if(y==width-1){
                if(map[x][y-1]==0 && bylesTam(x,y-1,kroki)){
                    kroki[i] = x*(width+1)+y;
                    y = width-2;
                }
                else if (map[x][0]==0 && bylesTam(x,0,kroki)){
                    kroki[i] = x*(width+1)+y;
                    y = 0;
                }
                else if(startowyX!=-1){
                    kroki[i] = x*(width+1)+y;
                    x = startowyX;
                    y = startowyY;
                    startowyX = -1;
                    startowyY = -1;
                    i--;
                }
                else {
                    if(bylesTam(x,y-1,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[x][y-1] = 0;
                        y = y-1;
                    }
                }
            }
            else{
                if(map[x-1][y] == 0 && bylesTam(x-1,y,kroki)){
                    kroki[i] = x*(width+1)+y;
                    x = x-1;
                }
                else if(map[x][y=1] == 0 && bylesTam(x,y-1,kroki)){
                    kroki[i] = x*(width+1)+y;
                    y = y-1;
                }
                else if(map[x][y+1] == 0 && bylesTam(x,y+1,kroki)){
                    kroki[i] = x*(width+1)+y;
                    y = y+1;
                }
                else if(map[x+1][y] == 0 && bylesTam(x+1,y,kroki)){
                    kroki[i] = x*(width+1)+y;
                    x = x+1;
                }
                else if(startowyX!=-1){
                    kroki[i] = x*(width+1)+y;
                    x = startowyX;
                    y = startowyY;
                    startowyX = -1;
                    startowyY = -1;
                    i--;
                }
                else {
                    if(bylesTam(x-1,y,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[x-1][y] = 0;
                        x = x-1;
                    }else if(bylesTam(x,y-1,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[x][y-1] = 0;
                        y = y-1;
                    }
                    else if(bylesTam(x,y+1,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[x][y+1] = 0;
                        y = y+1;
                    }
                    else if(bylesTam(x+1,y,kroki)){
                        kroki[i] = x*(width+1)+y;
                        map[x+1][y] = 0;
                        x = x+1;
                    }
                }
            }
        }
    }
    private boolean bylesTam(int x, int y,int[] kroki){
        for(int i = 0; i<kroki.length;i++){
            if(kroki[i]==x*(width+1)+y){
                return false;
            }
        }
        return true;
    }
    public int[][] getMap(){
        return map;
    }
}