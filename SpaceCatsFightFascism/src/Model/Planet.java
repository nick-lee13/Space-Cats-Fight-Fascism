import javafx.scene.image.Image;

public class Planet {
    int id;
    int tokenCount;
    String symbol;
    Image img;
    int x,y;
    boolean liberated;
    boolean occupied;
    public Planet(int inId, int index, int indey) {
            id = inId;
            tokenCount = 0;
            //might change this to input a symbol in construction at some point
            symbol = null;
            x = index;
            y = indey;
    }

    public boolean isLiberated() {
        return liberated;
    }

    public boolean isOccupied(){
        return occupied;
    }
    public int getId() {
        return id;
    }

    public int getTokens() {
        return tokenCount;
    }

    public void setTokens(int inToken) {
        tokenCount = inToken;
        if(tokenCount > 3)
        {
            liberated = true;
        }
        else
        {
            liberated = false;
        }
        if(tokenCount < -3)
        {
            occupied = true;
        }
        else
        {
            occupied = false;
        }
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
