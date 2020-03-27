package com.example.family.model;

public enum CreditLabels {

    FOOD("Питание"),
    TRANSPORT("Транспорт"),
    ENTERTAINMENT("Развлечение"),
    OTHER("Другое...");

    private String label;

    CreditLabels (String label) {
        this.label = label;
    }

    public String get_label() {
        return this.label;
    }
}
