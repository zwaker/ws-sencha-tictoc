package com.iphonewebservice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iphoneservice.iintel.TapManagerService;
import com.iphoneservice.iintel.entity.Ethnicity;
import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.TtuserConnection;
import com.iphoneservice.iintel.entity.User;

@Named
@RequestScoped
public class TapManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TapManagerService tapManagerService;

	public TapManagerService getTapManagerService() {
		return tapManagerService;
	}

	public void setTapManagerService(TapManagerService tapManagerService) {
		this.tapManagerService = tapManagerService;
	}

	public String respondToRequest() {
		HttpServletRequest httpServletRequest = ((HttpServletRequest) (FacesContext
				.getCurrentInstance().getExternalContext().getRequest()));
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		HttpServletResponse httpServletResponse = ((HttpServletResponse) (FacesContext
				.getCurrentInstance().getExternalContext().getResponse()));

		String type = (String) httpServletRequest.getParameter("type");
		if (type.equals("retrieveTapRequests")) {
			ObjectMapper mapper = new ObjectMapper(); // from
			// org.codeahaus.jackson.map
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			TTUsers user = new TTUsers();
			user.setId(21);
			user.setUsername("abc1@abc.com");
			user.setEthnicityId("2");
			user.setLocationId("2");
			user.setBirthyear("1981");
			List<TtuserConnection> userConnectionList = tapManagerService
					.retrieveTapRequests(user, 10, 1);
			String userConnectionString = prepareJSONObjectList(userConnectionList);
			// try {

			printOutput(httpServletResponse, userConnectionString);
		}
		if (type.equals("performTapRequests")){
			TTUsers user = new TTUsers();
			user.setId(21);
			user.setUsername("abc1@abc.com");
			user.setEthnicityId("2");
			user.setLocationId("2");
			user.setBirthyear("1981");
			tapManagerService.performTapRequest(user);
			List<TtuserConnection> userConnectionList = tapManagerService
					.retrieveTapRequests(user, 10, 1);
			String userConnectionString = prepareJSONObjectList(userConnectionList);
			// try {

			printOutput(httpServletResponse, userConnectionString);
		}
		if (type.equals("userCanMakeTapRequest")){
			TTUsers user = new TTUsers();
			user.setId(21);
			user.setUsername("abc1@abc.com");
			user.setEthnicityId("2");
			user.setLocationId("2");
			user.setBirthyear("1981");
			boolean userCanMakeTapRequest=tapManagerService.userCanMakeTapRequest(user);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("userCanMakeTapRequest", userCanMakeTapRequest);
			printOutput(httpServletResponse, jsonObject.toString());
		}
		return "";

	}

	public void printOutput(HttpServletResponse httpServletResponse,
			String outputString) {

		httpServletResponse.setContentType("text/json");

		// mapper.writeValue();
		PrintWriter out = null;
		try {
			out = httpServletResponse.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		out.println(outputString);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public String prepareJSONObjectList(
			List<TtuserConnection> userConnectionList) {

		JSONArray jsonList = new JSONArray();
		for (Iterator iterator = userConnectionList.iterator(); iterator
				.hasNext();) {
			TtuserConnection ttuserConnection = (TtuserConnection) iterator
					.next();
			JSONObject jsonObj = prepareJSONObject(ttuserConnection);
			jsonList.add(jsonObj);
		}

		JSONObject finalJSONObject = new JSONObject();
		finalJSONObject.put("connectionRequestsSent", jsonList);
		return finalJSONObject.toString();

	}

	public JSONObject prepareJSONObject(TtuserConnection ttuserConnection) {
		JSONObject jsonObj = JSONObject.fromObject(ttuserConnection);

		return jsonObj;
	}
}
