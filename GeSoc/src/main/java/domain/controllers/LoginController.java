package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public ModelAndView mostrarLogin(Request request, Response response) {

        return new ModelAndView(null, "loginGesoc.hbs");
    }
}
