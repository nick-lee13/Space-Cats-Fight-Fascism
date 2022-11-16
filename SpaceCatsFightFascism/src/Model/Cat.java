public class Cat {

    private String name;
    private int homePlanet;
    private AbilityCard ability;
    private Planet planet;
    private int scratchCount = 1; // I've set anything requiring a getter to be a private variable

    public Cat(String inName, int inHomePlanet, AbilityCard inAbility) { // Constructor does not include planet and scratch count 
                                                                            // since they are informed by the players actions.
            name = inName;
            homePlanet= inHomePlanet;
            ability = inAbility;
    }

    public String getName() {
        return name;
    }

    public int getHomePlanet() {
        return homePlanet;
    }

    public AbilityCard getAbilityCard() {
        return ability;
    }

    public Planet getPlanet() {
        return planet;
    }

    public int getScratchCount() {
        return scratchCount;
    }

    public void setScratchCount(int inScracthCount) {

        if(inScracthCount > 4 || inScracthCount < 0) { // Throws exception if the incoming scratch count is out of bounds 0-4
            throw new IllegalArgumentException("Scratch Count must be between 0-4");
        }

        scratchCount = inScracthCount;
    }

    public void setPLanet(Planet inPlanet){
        planet = inPlanet;
    }
}
