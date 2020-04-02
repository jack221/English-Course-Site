package entity;


public class Course {

    private long id;
    private String name;
    private String lessonText;
    private User user;

    public Course() {
    }

    public Course(long id, String name, String lessonText, User user) {
        this.id = id;
        this.name = name;
        this.lessonText = lessonText;
        this.user = user;
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

    public String getLessonText() {
        return lessonText;
    }

    public void setLessonText(String lessonText) {
        this.lessonText = lessonText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
