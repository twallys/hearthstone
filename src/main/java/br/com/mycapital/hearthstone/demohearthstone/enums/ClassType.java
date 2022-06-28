package br.com.mycapital.hearthstone.demohearthstone.enums;

public enum ClassType {
    MAGE("Magic"), // 0
    PALADIN("Paladin"), // 1
    HUNTER("Hunter"), // 2
    DRUID("Druid"), // 3
    ANY("Any"); // 4

    private final String description;

    ClassType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
