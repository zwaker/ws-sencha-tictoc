package com.iphonewebservice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iphoneservice.iintel.LoginManagerService;
import com.iphoneservice.iintel.RegistrationManagerService;
import com.iphoneservice.iintel.UserManagerService;
import com.iphoneservice.iintel.entity.Ethnicity;
import com.iphoneservice.iintel.entity.Location;
import com.iphoneservice.iintel.entity.TTUsers;

@Named
@RequestScoped
public class UserManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserManagerService userMangerService;

	@Inject
	private RegistrationManagerService registrationMangerService;

	@Inject
	private LoginManagerService loginManagerService;
	
	
	
	public RegistrationManagerService getRegistrationMangerService() {
		return registrationMangerService;
	}

	public void setRegistrationMangerService(
			RegistrationManagerService registrationMangerService) {
		this.registrationMangerService = registrationMangerService;
	}

	public UserManagerService getIintelMangerService() {
		return userMangerService;
	}

	public void setIintelMangerService(UserManagerService userMangerService) {
		this.userMangerService = userMangerService;
	}
	
	

	public UserManagerService getUserMangerService() {
		return userMangerService;
	}

	public void setUserMangerService(UserManagerService userMangerService) {
		this.userMangerService = userMangerService;
	}

	public LoginManagerService getLoginManagerService() {
		return loginManagerService;
	}

	public void setLoginManagerService(LoginManagerService loginManagerService) {
		this.loginManagerService = loginManagerService;
	}

	public String respondToRequest() {
		HttpServletRequest httpServletRequest = ((HttpServletRequest) (FacesContext
				.getCurrentInstance().getExternalContext().getRequest()));
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		HttpServletResponse httpServletResponse = ((HttpServletResponse) (FacesContext
				.getCurrentInstance().getExternalContext().getResponse()));

		String type = (String) httpServletRequest.getParameter("type");
		ObjectMapper mapper = new ObjectMapper(); // from
		// org.codeahaus.jackson.map
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		TTUsers user = new TTUsers();
		Location location = new Location();
		Ethnicity ethnicity = new Ethnicity();
		String returnString = "";
		try {
			JSONObject jsonObj = requestParamsToJSON(httpServletRequest);

			user = mapper.readValue(jsonObj.toString(), TTUsers.class);
			location = mapper.readValue(jsonObj.toString(), Location.class);
			ethnicity = mapper.readValue(jsonObj.toString(), Ethnicity.class);

		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (type.equals("registration")) {
			returnString = registrationMangerService.registerUser(user, location, ethnicity);
			System.out.println("reg in");
		}
		if (type.equals("login")) {
			returnString = loginManagerService.login(user);
			// System.out.println("Logged in");
		}
		if (type.equals("sendForgottenPassword")) {
			returnString = loginManagerService.sendForgottenPassword(user);
			// System.out.println("Logged in");
		}

		if (user.getUsername() != null) {

			httpServletResponse.setContentType("text/json");

			// mapper.writeValue();
			PrintWriter out = null;
			try {
				out = httpServletResponse.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			out.println(returnString);
			FacesContext.getCurrentInstance().responseComplete();
		}
		return "";

	}

	public JSONObject requestParamsToJSON(ServletRequest req) {
		JSONObject jsonObj = new JSONObject();
		Map<String, String[]> params = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String v[] = entry.getValue();
			Object o = (v.length == 1) ? v[0] : v;
			jsonObj.put(entry.getKey(), o);
		}
		return jsonObj;
	}
}
