package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.SurveyService;
import org.apache.http.auth.BasicUserPrincipal;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.Principal;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SurveyControllerTest {

    @Mock
    SurveyService surveyService;
    @InjectMocks
    SurveyController surveyController;

    @Test
    public void shouldSubmitSurveyForUser() {
        initMocks(this);

        Principal principal = new BasicUserPrincipal("john");
        String rating = "10";
        String comment = "comment";

        surveyController.post(principal, rating, comment);

        verify(surveyService).submitSurvey("john", "10", "comment");
    }
}
