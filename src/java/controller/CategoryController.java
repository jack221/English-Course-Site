package controller;

import dao.CategoryDAO;
import entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "CategoryController")
//@Named
@SessionScoped
public class CategoryController implements Serializable {

    private CategoryDAO categorydata;
    private List<Category> categoryList;
    private Category category;

    public CategoryController() {
    }

    public String create(){
        this.getCategorydata().create(category);
        return "/category/show" ;
    }
    public String update(Category category){
        this.category = category ;
        return "/category/update" ;
    }
    public String update(){
        this.getCategorydata().update(category);
        return "/category/show" ;
    }
    public String delete(Category category){
        this.getCategorydata().delete(category);
        return "/category/show" ;
    }
    public CategoryDAO getCategorydata() {
        if (categorydata == null) {
            categorydata = new CategoryDAO();
        }
        return categorydata;
    }

    public Category getCategory() {
        if (category == null) {
            category = new Category();
        }
        return category;
    }

    public List<Category> getCategoryList() {
        categoryList = getCategorydata().getCategoryList();
        return categoryList;
    }
    public List<Category> getQuizCategoryList(long quizID) {
        return getCategorydata().getQuizCategoryList(quizID) ;
    }
    
    public List<Category> getCourseCategoryList(long courseId){
        return getCategorydata().getCourseCategoryList(courseId) ;
    }

}
