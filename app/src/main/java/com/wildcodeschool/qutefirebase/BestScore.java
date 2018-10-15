package com.wildcodeschool.qutefirebase;

public class BestScore {

    private String name;
    private int score;

    public BestScore(String name, int score){
        this.name = name;
        this.score = score;
    }

    public BestScore(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
