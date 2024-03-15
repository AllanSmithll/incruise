package br.edu.pweb2.incruise.model;

public class Candidature {
    private Student student;
    private Vacancy vacancy;

    public Candidature(){

    }

    public Student getStudent() {
        return student;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public Candidature(Student student, Vacancy vacancy) {
        this.student = student;
        this.vacancy = vacancy;
    }

//private EstadoCandidatura situacao;

}
