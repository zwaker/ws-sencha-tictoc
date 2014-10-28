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
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iphoneservice.iintel.TapManagerService;
import com.iphoneservice.iintel.entity.TTUsers;
import com.iphoneservice.iintel.entity.TtuserConnection;

@Named
@RequestScoped
public class TocManager implements Serializable {

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
		TtuserConnection ttuserConnection = new TtuserConnection();

		ObjectMapper mapper = new ObjectMapper(); // from
		// org.codeahaus.jackson.map
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);

		if (type.equals("confirm")) {

			JSONObject jsonObj = requestParamsToJSON(httpServletRequest);

			try {
				ttuserConnection = mapper.readValue(jsonObj.toString(),
						TtuserConnection.class);
				boolean isConfirmSuccessful = tapManagerService
						.isConfirmSuccessful(ttuserConnection);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("isConfirmSuccessful", isConfirmSuccessful);
				printOutput(httpServletResponse, jsonObject.toString());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type.equals("reject")) {

			JSONObject jsonObj = requestParamsToJSON(httpServletRequest);

			try {
				ttuserConnection = mapper.readValue(jsonObj.toString(),
						TtuserConnection.class);
				boolean isRejectSuccessful = tapManagerService
						.isRejectSuccessful(ttuserConnection);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("isRejectSuccessful", isRejectSuccessful);
				printOutput(httpServletResponse, jsonObject.toString());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type.equals("retrieveTocRequests")) {

			// org.codeahaus.jackson.map
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			TTUsers user = new TTUsers();
			user.setId(32);
			user.setUsername("abc1@abc.com");
			user.setEthnicityId("2");
			user.setLocationId("2");
			user.setBirthyear("1981");
			List<TtuserConnection> userConnectionList = tapManagerService
					.retrieveTocRequests(user, 10, 1);
			String userConnectionString = prepareJSONObjectList(userConnectionList);
			// try {

			printOutput(httpServletResponse, userConnectionString);
		}

		if (type.equals("userCanMakeTapRequest")) {
			TTUsers user = new TTUsers();
			user.setId(21);
			user.setUsername("abc1@abc.com");
			user.setEthnicityId("2");
			user.setLocationId("2");
			user.setBirthyear("1981");
			boolean userCanMakeTapRequest = tapManagerService
					.userCanMakeTapRequest(user);
			JSONObject jsonObject = new JSONObject();
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
		finalJSONObject.put("connectionRequestsReceived", jsonList);
		return finalJSONObject.toString();

	}

	public JSONObject prepareJSONObject(TtuserConnection ttuserConnection) {
		JSONObject jsonObj = JSONObject.fromObject(ttuserConnection);

		return jsonObj;
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
