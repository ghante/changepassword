package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.SurveyService;
import org.apache.http.auth.BasicUserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SurveyControllerTest {

    @Mock
    SurveyService surveyService;
    @InjectMocks
    SurveyController surveyController;

    Principal principal = new BasicUserPrincipal("john");
    String rating = "10";
    String comment = "comment";

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldSubmitSurveyForUser() {
        surveyController.post(principal, rating, comment);
        verify(surveyService).submitSurvey("john", "10", "comment");
    }

    @Test
    public void shouldShowValidationMessageIfSurveyRatingIsNull() {
        rating = null;
        ModelAndView modelAndView = surveyController.post(principal, rating, comment);
        verifyValidationHappens(modelAndView);
    }

    @Test
    public void shouldShowValidationMessageIfSurveyRatingIsEmpty() {
        rating = "";
        ModelAndView modelAndView = surveyController.post(principal, rating, comment);
        verifyValidationHappens(modelAndView);
    }

    private void verifyValidationHappens(ModelAndView modelAndView) {
        verify(surveyService, never()).submitSurvey(anyString(),anyString(),anyString());
        ModelMap modelMap = (ModelMap) modelAndView.getModelMap().get("validation");
        Boolean mandatoryFieldMissing = (Boolean) modelMap.get("mandatoryFieldMissing");
        assertTrue(mandatoryFieldMissing);
    }
}
