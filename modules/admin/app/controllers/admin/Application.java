package controllers.admin;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    @Authenticated(ActionAuthenticator.class)
    public Result index() {
        return ok("In admin module");
    }


}
