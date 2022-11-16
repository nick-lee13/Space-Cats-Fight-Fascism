public class Planet {
    int id;
    int tokenCount;

    public Planet(int inId, int inTokenCount) {
            id = inId;
            tokenCount = inTokenCount;
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
    
}
