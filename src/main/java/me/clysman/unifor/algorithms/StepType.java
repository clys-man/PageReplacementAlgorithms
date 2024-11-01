package me.clysman.unifor.algorithms;

public enum StepType {
	MISS("-miss"),
	HIT("+hit");

    private final String text;

    StepType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
