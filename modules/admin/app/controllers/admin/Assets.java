package controllers.admin;
import play.api.mvc.*;

public class Assets {
    public static Action<AnyContent> at(String path, String file) {
        return controllers.Assets.at(path, file);
    }
}