package controllers;

import com.impetus.client.cassandra.common.CassandraConstants;
import models.Book;
import play.*;
import play.mvc.*;
import javax.persistence.Query;
import play.mvc.Security.*;

import views.html.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Application extends Controller {

    @Security.Authenticated(ActionAuthenticator.class)
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
