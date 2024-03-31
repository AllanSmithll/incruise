package br.edu.pweb2.incruise.model;

import lombok.Getter;

@Getter
public enum Competence {
    JAVA("Java"),
    TEAM_WORK("Trabalho em Equipe"),
    PYTHON("Python"),
    CSS("CSS"),
    JAVASCRIPT("JavaScript"),
    ADAPTABILITY("Adaptabilidade"),
    MYSQL("MySQL"),
    POSTGRES("PostgreSQL"),
    WINDOWS("Windows"),
    ANDROID("Android");

    private final String description;

    Competence(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}