package com.margin.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class DefaultController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("redirect:/internal/ui/v1");
    }
}
