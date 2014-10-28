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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iphoneservice.iintel.EthnicityManagerService;
import com.iphoneservice.iintel.entity.Ethnicity;
import com.iphoneservice.iintel.entity.User;

@Named
@RequestScoped
public class EthnicityManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EthnicityManagerService ems;

	public EthnicityManagerService getEms() {
		return ems;
	}

	public void setEms(EthnicityManagerService ems) {
		this.ems = ems;
	}

	public String respondToRequest() {
		HttpServletRequest httpServletRequest = ((HttpServletRequest) (FacesContext
				.getCurrentInstance().getExternalContext().getRequest()));
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		HttpServletResponse httpServletResponse = ((HttpServletResponse) (FacesContext
				.getCurrentInstance().getExternalContext().getResponse()));

		String searchWildCard = (String) httpServletRequest
				.getParameter("term");
		ObjectMapper mapper = new ObjectMapper(); // from
		// org.codeahaus.jackson.map
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		User user = new User();
		String returnString = "";
		// try {
		String locationsListAsString = prepareJSONObjectList(searchWildCard);
		printOutput(httpServletResponse, locationsListAsString);
		// user = mapper.readValue(jsonObj.toString(), User.class);

		/*
		 * } catch (JsonGenerationException e1) { // TODO Auto-generated catch
		 * block e1.printStackTrace(); } catch (JsonMappingException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); } catch
		 * (IOException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

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

	public String prepareJSONObjectList(String searchWildCard) {
		Ethnicity ethnicity = getEms().findEthnicity(searchWildCard);

		JSONObject jsonObj = prepareJSONObject(ethnicity);
		JSONArray jsonList = new JSONArray();
		jsonList.add(jsonObj);

		JSONObject finalJSONObject = new JSONObject();
		finalJSONObject.put("ethnicities", jsonList);
		return finalJSONObject.toString();

	}

	public JSONObject prepareJSONObject(Ethnicity ethnicity) {
		JSONObject jsonObj = JSONObject.fromObject(ethnicity);

		return jsonObj;
	}
}
