package services;

import models.User;
import play.Logger;
import play.data.DynamicForm;
import static play.mvc.Controller.session;

import play.data.Form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vitthalawate on 10/9/15.
 */
public class UserService extends Eloquent {

    public User find(String email){
        return this.find(User.class, email);
    }

    public boolean checkLogin(DynamicForm loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = this.find(email);

        if (user == null || !password.equals(user.getPassword())) {
            return false;
        }

        session().clear();
        session("userEmail", user.getEmail());

        return true;
    }

    public boolean saveData(Form formData) {
        User user = (User) formData.get();
        this.create(user);
        return true;
    }

    public boolean updateData(Form formData, String email) {
        User user = this.find(email);
        user.setAge(Integer.parseInt(formData.field("age").value()));
        user.setEmail(formData.field("email").value());
        user.setName(formData.field("name").value());
        user.setPassword(formData.field("password").value());
        this.update(user);
        return true;
    }

    public List<User> getAllUsers() {
        return this.selectQuery("select u from User u");
    }

    public Map<String, Boolean> getAgeList() {
        Map<String, Boolean> ages = new HashMap<String, Boolean>();
        ages.put("20", false);
        ages.put("21", false);
        ages.put("22", false);
        ages.put("23", true);
        ages.put("24", false);
        ages.put("25", false);
        ages.put("26", false);
        ages.put("27", false);
        ages.put("28", false);
        ages.put("29", false);
        ages.put("30", false);

        return ages;
    }

}
