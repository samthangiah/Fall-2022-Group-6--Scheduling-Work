package sru.groupsix.workOrder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import sru.groupsix.workOrder.User.Userdetail;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Userdetail userDetails = (Userdetail) authentication.getPrincipal();
		
		String directURL = request.getContextPath();
		
		if (userDetails.getRole().equals("USER")) {
			directURL = "/index";
		}
		if (userDetails.getRole().equals("ADMIN")) {
			directURL = "/admin";
		}
		if (userDetails.getRole().equals("TECHASSIST")) {
			directURL = "/techAssist";
		}
		response.sendRedirect(directURL);
		
	}
	

}
