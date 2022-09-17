package entities;

public class Customers {

	private int id;
	private String name;
	private String surnarme;
	private String mail;
	private String comercial_name;
	private String addres;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnarme() {
		return surnarme;
	}
	public void setSurnarme(String surnarme) {
		this.surnarme = surnarme;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getComercial_name() {
		return comercial_name;
	}
	public void setComercial_name(String company_name) {
		this.comercial_name = company_name;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	
}
