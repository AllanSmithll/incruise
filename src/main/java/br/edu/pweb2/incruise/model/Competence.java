package br.edu.pweb2.incruise.model;

import lombok.Getter;

@Getter
public enum Competence {
    JAVA("Java"),
    PYTHON("Python"),
    HTML("HTML"),
    CSS("CSS"),
    JAVASCRIPT("JavaScript"),
    MYSQL("MySQL"),
    POSTGRES("PostgreSQL"),
    WINDOWS("Windows"),
    ANDROID("Android"),
    TEAM_WORK("Trabalho em Equipe"),
    ADAPTABILITY("Adaptabilidade");

    private final String description;

    Competence(String description) {
        this.description = description;
    }

}