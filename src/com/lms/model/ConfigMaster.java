package com.lms.model;

import java.util.Date;

public class ConfigMaster {

	private Integer id;

	private String name;

	private String value;

	private String link1;

	private String link2;
	
	private String DEFAULT_ALTERNATOR_MODEL;
	private String DEFAULT_HZ;
	private String DEFAULT_VOLTAGE;
	private String DEFAULT_COOLING_SYSTEM;
	
	private String create_by;

	private Date created_date;
	private String DEFAULT_ALTERNATOR_MAKE;
	private String DG_MODEL;
	private String ENGINE_MODEL;
	private String ENGINE_SERIES;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLink1() {
		return link1;
	}
	public void setLink1(String link1) {
		this.link1 = link1;
	}
	public String getLink2() {
		return link2;
	}
	public void setLink2(String link2) {
		this.link2 = link2;
	}
	public String getDEFAULT_ALTERNATOR_MODEL() {
		return DEFAULT_ALTERNATOR_MODEL;
	}
	public void setDEFAULT_ALTERNATORE_MODEL(String dEFAULT_ALTERNATOR_MODEL) {
		DEFAULT_ALTERNATOR_MODEL = dEFAULT_ALTERNATOR_MODEL;
	}
	public String getDEFAULT_HZ() {
		return DEFAULT_HZ;
	}
	public void setDEFAULT_HZ(String dEFAULT_HZ) {
		DEFAULT_HZ = dEFAULT_HZ;
	}
	public String getDEFAULT_VOLTAGE() {
		return DEFAULT_VOLTAGE;
	}
	public void setDEFAULT_VOLTAGE(String dEFAULT_VOLTAGE) {
		DEFAULT_VOLTAGE = dEFAULT_VOLTAGE;
	}
	public String getDEFAULT_COOLING_SYSTEM() {
		return DEFAULT_COOLING_SYSTEM;
	}
	public void setDEFAULT_COOLING_SYSTEM(String dEFAULT_COOLING_SYSTEM) {
		DEFAULT_COOLING_SYSTEM = dEFAULT_COOLING_SYSTEM;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getDEFAULT_ALTERNATOR_MAKE() {
		return DEFAULT_ALTERNATOR_MAKE;
	}
	public void setDEFAULT_ALTERNATOR_MAKE(String dEFAULT_ALTERNATOR_MAKE) {
		DEFAULT_ALTERNATOR_MAKE = dEFAULT_ALTERNATOR_MAKE;
	}
	public String getDG_MODEL() {
		return DG_MODEL;
	}
	public void setDG_MODEL(String dG_MODEL) {
		DG_MODEL = dG_MODEL;
	}
	public String getENGINE_MODEL() {
		return ENGINE_MODEL;
	}
	public void setENGINE_MODEL(String eNGINE_MODEL) {
		ENGINE_MODEL = eNGINE_MODEL;
	}
	public String getENGINE_SERIES() {
		return ENGINE_SERIES;
	}
	public void setENGINE_SERIES(String eNGINE_SERIES) {
		ENGINE_SERIES = eNGINE_SERIES;
	}
	@Override
	public String toString() {
		return "ConfigMaster [id=" + id + ", name=" + name + ", value=" + value
				+ ", link1=" + link1 + ", link2=" + link2
				+ ", DEFAULT_ALTERNATOR_MODEL=" + DEFAULT_ALTERNATOR_MODEL
				+ ", DEFAULT_HZ=" + DEFAULT_HZ + ", DEFAULT_VOLTAGE="
				+ DEFAULT_VOLTAGE + ", DEFAULT_COOLING_SYSTEM="
				+ DEFAULT_COOLING_SYSTEM + ", create_by=" + create_by
				+ ", created_date=" + created_date
				+ ", DEFAULT_ALTERNATOR_MAKE=" + DEFAULT_ALTERNATOR_MAKE
				+ ", DG_MODEL=" + DG_MODEL + ", ENGINE_MODEL=" + ENGINE_MODEL
				+ ", ENGINE_SERIES=" + ENGINE_SERIES + "]";
	}

	
	

}
