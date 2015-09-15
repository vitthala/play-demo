package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class ActionAuthenticator extends Security.Authenticator {
	@Override
    public String getUsername(Http.Context ctx) {
        String userEmail = ctx.session().get("userEmail");

        if (userEmail != null) {
            return userEmail;
        }
        return null;
    }
	
	@Override
    public Result onUnauthorized(Http.Context ctx) {
		//return super.onUnauthorized(ctx);
        return redirect(routes.UsersController.login());
    }
}
