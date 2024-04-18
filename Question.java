package com.example.task31c;

public class Question {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int answer;

    public Question(String question, String option1, String option2, String option3, int answer){
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
    }

    public String getQuestion() {return question;}
    public String getOption1() {return option1;}
    public String getOption2() {return option2;}
    public String getOption3() {return option3;}
    public int getAnswer() {return answer;}


}
