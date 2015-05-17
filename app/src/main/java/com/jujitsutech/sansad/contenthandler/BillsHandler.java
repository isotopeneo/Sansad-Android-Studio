package com.jujitsutech.sansad.contenthandler;

import com.jujitsutech.sansad.bean.ParliamentBills;
import com.jujitsutech.sansad.util.LoggerClass;
import com.jujitsutech.sansad.util.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BillsHandler {

	private static final String RESULTS = "results";
	private static final String BILL_ID = "bill_id";
	private static final String TITLE = "title";
	private static final String STATUS = "status";
	private static final String MINISTRY = "ministry";
	private static final String INTRODUCED_ON = "introduced_on";
	private static final String LS_STATUS = "ls_status";
	private static final String RS_STATUS = "rs_status";
	private static final String COM_REF = "com_ref";
	private static final String COM_REP = "com_rep";
	private static final String LAST_ACTION = "last_action";
	private static final String LAST_ACTION_AT = "last_action_at";
	private static final String INTRODUCED_BY = "introduced_by";
	private static final String SUMMARY = "summary";
	private static final String URL = "url";
    private static final String COUNT = "count";
    private static final String PAGE = "page";
	
	public List<ParliamentBills> parseContent(String input) {
		LoggerClass.log(" json is " + input);
		if (!(input.equals(""))) {
			try {
				JSONObject json = new JSONObject(input);
                Singleton.countOfResults = json.getInt(COUNT);
                JSONObject pageDetails = json.getJSONObject(PAGE);
                Singleton.currentPageNumber = pageDetails.getInt(PAGE);
				JSONArray results = json.getJSONArray(RESULTS);
				List<ParliamentBills> bills = new ArrayList<ParliamentBills>();
				for (int i = 0; i < results.length(); i++) {
					try {
						ParliamentBills bill = new ParliamentBills();
						bill.setBillId(((JSONObject)results.get(i)).getString(BILL_ID));
						bill.setTitle(((JSONObject)results.get(i)).getString(TITLE));
						bill.setMinistry(((JSONObject)results.get(i)).getString(MINISTRY));
						bill.setIntroduced_on(((JSONObject)results.get(i)).getString(INTRODUCED_ON));
						bill.setStatus(((JSONObject)results.get(i)).getString(STATUS));
						bill.setLs_status(((JSONObject)results.get(i)).getString(LS_STATUS));
						bill.setRs_status(((JSONObject)results.get(i)).getString(RS_STATUS));
						bill.setCom_ref(((JSONObject)results.get(i)).getString(COM_REF));
						bill.setCom_rep(((JSONObject)results.get(i)).getString(COM_REP));
						bill.setLast_action(((JSONObject)results.get(i)).getString(LAST_ACTION));
						bill.setLast_action_at(((JSONObject)results.get(i)).getString(LAST_ACTION_AT));
						bill.setIntroduced_by(((JSONObject)results.get(i)).getString(INTRODUCED_BY));
						bill.setSummary(((JSONObject)results.get(i)).getString(SUMMARY));
						bill.setUrl(((JSONObject)results.get(i)).getString(URL));
						
						bills.add(bill);
					} catch (Exception e) {
						continue;
					}
				}
				return bills;
			} catch (Exception e) {
				LoggerClass.log(e.toString());
			}
		}
		return null;
	}
}
