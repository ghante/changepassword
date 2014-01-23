package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @RequestMapping(method = RequestMethod.GET)
    public void get() {
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(Principal principal,
                             @RequestParam(value = "survey_rating", required = false) String surveyRating,
                             @RequestParam(value = "survey_comment", required = false) String surveyComment) {
        String username = principal.getName();
        if (surveyRating == null || surveyRating.isEmpty()) {
            return showValidationError();
        }
        surveyService.submitSurvey(username, surveyRating, surveyComment);
        return new ModelAndView();
    }

    private ModelAndView showValidationError() {
        ModelMap model = new ModelMap();
        model.addAttribute("mandatoryFieldMissing", true);
        return new ModelAndView("survey","validation",model);
    }
}
