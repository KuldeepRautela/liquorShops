package com.example.liquorshops.jetpack.entities;

public class AdvisorModel {
    private String question;
    private String ansOne,ansTwo,ansThree,ansFour,ansFive;

    public AdvisorModel(String question, String ansOne, String ansTwo, String ansThree, String ansFour, String ansFive) {
        this.question = question;
        this.ansOne = ansOne;
        this.ansTwo = ansTwo;
        this.ansThree = ansThree;
        this.ansFour = ansFour;
        this.ansFive = ansFive;
    }

    public AdvisorModel(String question, String ansOne, String ansTwo, String ansThree) {
        this.question = question;
        this.ansOne = ansOne;
        this.ansTwo = ansTwo;
        this.ansThree = ansThree;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsOne() {
        return ansOne;
    }

    public void setAnsOne(String ansOne) {
        this.ansOne = ansOne;
    }

    public String getAnsTwo() {
        return ansTwo;
    }

    public void setAnsTwo(String ansTwo) {
        this.ansTwo = ansTwo;
    }

    public String getAnsThree() {
        return ansThree;
    }

    public void setAnsThree(String ansThree) {
        this.ansThree = ansThree;
    }

    public String getAnsFour() {
        return ansFour;
    }

    public void setAnsFour(String ansFour) {
        this.ansFour = ansFour;
    }

    public String getAnsFive() {
        return ansFive;
    }

    public void setAnsFive(String ansFive) {
        this.ansFive = ansFive;
    }
}
