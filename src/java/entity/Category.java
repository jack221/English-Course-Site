package entity;

import java.util.List;


public class Category {

    private long id;
    private String text;
    private List<Course> courseCategory;
    private List<Quiz> quizCategory;

    public Category() {
    }

    public Category(long id, String text, List<Course> courseCategory, List<Quiz> quizCategory) {
        this.id = id;
        this.text = text;
        this.courseCategory = courseCategory;
        this.quizCategory = quizCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Quiz> getQuizCategory() {
        return quizCategory;
    }

    public List<Course> getCourseCategory() {
        return courseCategory;
    }

    public void setQuizCategory(List<Quiz> quizCategory) {
        this.quizCategory = quizCategory;
    }

    public void setCourseCategory(List<Course> courseCategory) {
        this.courseCategory = courseCategory;
    }

}
