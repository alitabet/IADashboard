package org.kuali.kd2013.dataobject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.web.controller.UifControllerBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/facultySearch")
public class FacultyController extends UifControllerBase {
	
	@Override
    protected UifFormBase createInitialForm(HttpServletRequest request) {
        return new FacultyForm();
    }
	
	@RequestMapping(method=RequestMethod.POST, params="methodToCall=navigateTab")
    public ModelAndView navigateTab(@ModelAttribute("KualiForm") FacultyForm uifForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		
		View tempView = uifForm.getView();
		String currentPageId = uifForm.getActionParamaterValue("navigateToPageId");
		tempView.setCurrentPageId(currentPageId);
		uifForm.setView(tempView);
		return super.navigate(uifForm, result, request, response);
		
        /*Properties props = new Properties();
        props.put(UifParameters.METHOD_TO_CALL, "refresh");
        props.put(UifParameters.VIEW_ID, uifForm.getViewId());
        props.put(UifParameters.FORM_KEY, uifForm.getFormKey());
        props.put(UifParameters.AJAX_REQUEST, "false");

        return performRedirect(uifForm, uifForm.getFormPostUrl(), props);*/
    }
}
