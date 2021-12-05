package com.amigo.control;

import com.amigo.present.UserPresenter;
import com.amigo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @Autowired
    UserPresenter presenter;

    @GetMapping("/dashboard")
    public ModelAndView showDashboard(@ModelAttribute User user) {
        if (!user.isValid() && !presenter.hasUser()) {
            return new ModelAndView("404");
        }
        if (user != null) {
            presenter.setUser(user);
        }
        ModelAndView mav = new ModelAndView("dashboard");
        presenter.populate(mav.getModel());
        return mav;
    }

    @GetMapping("/matching-screen")
    public ModelAndView showMatchingScreen() {
        ModelAndView mav = new ModelAndView("matching-screen");
        User currentUser = presenter.getUser();

        int counter = 0;
        for (var each : currentUser.getCurrentMatches()) {
            UserPresenter p = new UserPresenter();
            if (each.getUser1() == currentUser){
                p.setUser(each.getUser2());
            }
            else {
                p.setUser(each.getUser1());
            }
            p.setSuffix(String.valueOf(++counter));
            p.populate(mav.getModel());
        }
        return mav;
    }
}
