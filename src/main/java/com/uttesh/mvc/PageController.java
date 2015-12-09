package com.uttesh.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Controller
public class PageController {

    protected static Logger logger = Logger.getLogger(PageController.class);

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest q, HttpServletResponse r, Model model)
            throws Exception {
        model.addAttribute("message", "anil");

        return "/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(HttpServletRequest q, HttpServletResponse r, Model model)
            throws Exception {
        model.addAttribute("message", "uttesh");

        return "/index";
    }

    @RequestMapping("/error")
    public String error(Model model) {
        model.addAttribute("errorCode", "403");
        model.addAttribute("errorMessage", "Not authorized");
        return "/error";
    }

    private void clearSession(HttpServletRequest request) {
        request.getSession().removeAttribute("loggedInUser");
    }
}
