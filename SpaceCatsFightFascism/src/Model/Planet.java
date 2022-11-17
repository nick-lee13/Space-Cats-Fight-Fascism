import javafx.scene.image.Image;

public class Planet {
    int id;
    int tokenCount;
    String symbol;
    Image img;
    int x,y;

    public Planet(int inId, int index, int indey) {
            id = inId;
            tokenCount = 0;
            //might change this to input a symbol in construction at some point
            symbol = null;
            x = index;
            y = indey
    }

    public boolean isLiberated() {
        if(tokenCount < 4)
            return true;
        return false;
    }

    public int getId() {
        return id;
    }

    public int getTokens() {
        return tokenCount;
    }

    public void setTokenCount(int inToken) {
        tokenCount = inToken;
    }

    public void setSymbol(String inSymbol)
    {
        symbol = inSymbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setImage(String path){
        img = new Image(path);
    }

    public Image getImage(){
        return img;
    }

    public int[] getIndex(){
        int[] xy = {x,y};
        return xy;
    }

    public void setIndex(int indexX, int indexY){
        x = indexX;
        y= indexY;
    }
}
