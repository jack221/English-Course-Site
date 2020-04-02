package controller;

import dao.CategoryDAO;
import dao.QuizDAO;
import dao.UserDAO;
import entity.Category;
import entity.Quiz;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "QuizController")
//@Named
@SessionScoped
public class QuizController implements Serializable {

    private Quiz quiz;
    private List<Quiz> quizList;
    private QuizDAO quizdata;

    @ManagedProperty(value = "#{UserController}")
    private UserController userController;
    private long selectedUserId;
    
    @ManagedProperty(value = "#{CategoryController}")
    private CategoryController categoryController ;
    private List<Long> selectedCategoryIds ;
    
    @ManagedProperty(value = "#{QuestionController}")
    private QuestionController questionController ;
    private List<Long> selectedQuestionIds ;
    
    public QuizController() {
    }

    public String create() {
        this.getQuizdata().create(quiz, selectedUserId);
        this.getQuizdata().createQuizCategory(getQuiz().getId(), selectedCategoryIds);
        this.getQuizdata().createQuizQuestion(quiz.getId(), selectedQuestionIds);
        return "/quiz/show";
    }

    public String update(Quiz quiz) {
        this.quiz = quiz;
        return "/quiz/update";
    }

    public String update() {
        this.getQuizdata().update(this.getQuiz(), selectedUserId);
        return "/quiz/show";
    }

    public String delete(Quiz quiz) {
        this.getQuizdata().delete(quiz);
        return "/quiz/show";
    }

    public Quiz getQuiz() {
        if (quiz == null) {
            quiz = new Quiz();
        }
        return quiz;
    }

    public List<Quiz> getQuizList() {
        quizList = getQuizdata().getQuizList();
        return quizList;
    }

    public QuizDAO getQuizdata() {
        if (quizdata == null) {
            quizdata = new QuizDAO();
        }
        return quizdata;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(long selectedUserId) {
        System.out.println("userID " + this.selectedUserId);
        this.selectedUserId = selectedUserId;
    }
    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public CategoryController getCategoryController() {
        return categoryController;
    }

    public void setCategoryController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    public List<Long> getSelectedCategoryIds() {
        if(this.selectedCategoryIds == null)
            selectedCategoryIds = new ArrayList<Long>();
        return selectedCategoryIds;
    }

    public void setSelectedCategoryIds(List<Long> selectedCategoryIds) {
        this.selectedCategoryIds = selectedCategoryIds;
    }

    public QuestionController getQuestionController() {
        return questionController;
    }

    public void setQuestionController(QuestionController questionController) {
        this.questionController = questionController;
    }

    public List<Long> getSelectedQuestionIds() {
        return selectedQuestionIds;
    }

    public void setSelectedQuestionIds(List<Long> selectedQuestionIds) {
        if(selectedQuestionIds == null)
            selectedQuestionIds = new ArrayList<Long>();
        this.selectedQuestionIds = selectedQuestionIds;
    }

    
    
}
