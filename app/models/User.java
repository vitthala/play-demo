package models;

import javax.persistence.*;

import play.Logger;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitthalawate on 10/9/15.
 */
@Entity
@Table(name="users", schema = "demo@cassandra_pu")
public class User {
    @Id
    protected String email;

    @Column(name = "age")
    protected int age;

    @Column(name = "name")
    protected String name;

    @Column(name = "password")
    protected String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = (int)age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {
        return "User Class";
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<ValidationError>();
        Logger log = new Logger();
        log.debug("Name: " + name + " Email: " + email);


        if (name == null || name.length() == 0) {
            errors.add(new ValidationError("name", "No name was given."));
        }

        if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "No password was given."));
        } else if (password.length() < 5) {
            errors.add(new ValidationError("password", "Given password is less than five characters."));
        }

        services.UserService userService = new services.UserService();

        if (email == null ||  email.length() == 0) {
            errors.add(new ValidationError("email", "Invalid email"));
        } else if (userService.find(email) != null) {
            errors.add(new ValidationError("email", "Email already exist"));
        }

        if(errors.size() > 0)
            return errors;

        return null;
    }
}
