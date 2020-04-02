package entity;

import java.util.List;


public class Quiz {

    private long id;
    private String name;
    private String comment;
    private User user;
    private List<Question> quizQuestion ;

    public Quiz() {
    }

    public Quiz(long id, String name, String comment, User user, List<Question> quizQuestion) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.user = user;
        this.quizQuestion = quizQuestion ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(List<Question> quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

}
