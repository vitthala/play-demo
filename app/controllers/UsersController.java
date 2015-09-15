package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.*;
import views.html.user.*;

import services.UserService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by vitthalawate on 11/9/15.
 */
public class UsersController  extends Controller {

    private UserService service = new UserService();

    public Result login() {
        session().clear();
        return ok(login.render());
    }

    public Result addUser() {
        Form<User> formData = Form.form(User.class);
        play.api.mvc.Call formAction = routes.UsersController.save();
        String title = "Add User";
        return ok(addUser.render(title, formData, formAction, this.service.getAgeList()));
    }

    public Result save() {
        Form<User> formData = Form.form(User.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Please correct errors");
            play.api.mvc.Call formAction = routes.UsersController.save();
            return badRequest(addUser.render("Add User", formData, formAction, this.service.getAgeList()));
        }

        this.service.saveData(formData);

        flash("success", "Registered successfully!!");
        return redirect(routes.Application.index());
    }

    public Result edit(String email) {
        User user = this.service.find(email);
        Form<User> formdata = Form.form(User.class).fill(user);
        play.api.mvc.Call formAction = routes.UsersController.update(email);
        String title = "Edit user";
        return ok(addUser.render(title, formdata, formAction, this.service.getAgeList()));
    }

    public Result update(String email) {

        Form<User> formData = Form.form(User.class).bindFromRequest();
        if (formData.hasErrors()) {
            List<ValidationError> emailErrors = formData.field("email").errors();
            if (!emailErrors.isEmpty()) {

                List<ValidationError> emailErrors2 = emailErrors;
                String errMsg;
                String errorMessages = "";
                String emailNew = formData.field("email").value();

                Iterator<ValidationError> it = emailErrors2.iterator();
                while(it.hasNext()){
                    ValidationError errorV = it.next();
                    errMsg = errorV.message();
                    errorMessages = errorMessages + errMsg;
                }

                if (errorMessages.equals("Email already exist") && emailNew.equals(email)) {
                    this.service.updateData(formData, email);
                } else {
                    return badRequest(addUser.render("Edit User", formData, routes.UsersController.update(email), this.service.getAgeList()));
                }
            }
        } else {
            this.service.updateData(formData, email);
        }

        flash("success", "User updated successfully");
        return redirect(routes.UsersController.listUsers());
    }

    public Result listUsers() {
        List<User> users = this.service.getAllUsers();
        return ok(list.render(users));
    }

    public Result doLogin() {
        DynamicForm loginData = Form.form().bindFromRequest();

        if (this.service.checkLogin(loginData)) {
            flash("success", "Login successfull!!");
            return redirect(routes.Application.index());
        } else {
            flash("error", "Invalid username or password");
            return badRequest(login.render());
        }
    }
}
