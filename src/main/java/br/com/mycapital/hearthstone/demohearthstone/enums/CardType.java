package br.com.mycapital.hearthstone.demohearthstone.enums;

public enum CardType {
    MAGIC("Magic"), // 0
    CREATURE("Creature"); // 1

    private final String description;

    CardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
