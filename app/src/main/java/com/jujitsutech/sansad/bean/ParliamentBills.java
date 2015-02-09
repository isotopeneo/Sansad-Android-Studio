package com.jujitsutech.sansad.bean;

public class ParliamentBills {

	private String billId;
	private String title;
	private String status;
	private String ministry;
	private String introduced_on;
	private String ls_status;
	private String rs_status;
	/*
	 * com_ref:: The date on which this bill was referred to a committee for
	 * further discussion.
	 */
	private String com_ref;
	/*
	 * com_rep: The date on which a report was presented by the committee to which this
	 * bill was earlier referred to.
	 */
	private String com_rep;
	private String last_action;
	private String last_action_at;
	private String introduced_by;
	private String summary;
	private String url;
	
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMinistry() {
		return ministry;
	}
	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}
	public String getIntroduced_on() {
		return introduced_on;
	}
	public void setIntroduced_on(String introduced_on) {
		this.introduced_on = introduced_on;
	}
	public String getLs_status() {
		return ls_status;
	}
	public void setLs_status(String ls_status) {
		this.ls_status = ls_status;
	}
	public String getRs_status() {
		return rs_status;
	}
	public void setRs_status(String rs_status) {
		this.rs_status = rs_status;
	}
	public String getCom_ref() {
		return com_ref;
	}
	public void setCom_ref(String com_ref) {
		this.com_ref = com_ref;
	}
	public String getCom_rep() {
		return com_rep;
	}
	public void setCom_rep(String com_rep) {
		this.com_rep = com_rep;
	}
	public String getLast_action() {
		return last_action;
	}
	public void setLast_action(String last_action) {
		this.last_action = last_action;
	}
	public String getLast_action_at() {
		return last_action_at;
	}
	public void setLast_action_at(String last_action_at) {
		this.last_action_at = last_action_at;
	}
	public String getIntroduced_by() {
		return introduced_by;
	}
	public void setIntroduced_by(String introduced_by) {
		this.introduced_by = introduced_by;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
