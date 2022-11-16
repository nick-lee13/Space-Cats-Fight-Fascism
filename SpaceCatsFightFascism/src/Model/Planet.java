public class Planet {
    int id;
    int tokenCount;
    String symbol;

    public Planet(int inId, int inTokenCount) {
            id = inId;
            tokenCount = inTokenCount;
            //might change this to input a symbol in construction at some point
            symbol = null
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
    
}
