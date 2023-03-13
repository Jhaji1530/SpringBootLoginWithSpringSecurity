package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customers {
	@Id
	private String customerId;
	private String customerName;
	private String customerMail;
	private String customerPhone;

	// @OneToMany(targetEntity=Orders.class)
	// @ElementCollection(targetClass=Orders.class)

	@OneToMany(targetEntity = Orders.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_fk", referencedColumnName = "customerId")
	private List<Orders> orders;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public Customers(String customerId, String customerName, String customerMail, String customerPhone,
			List<Orders> orders) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMail = customerMail;
		this.customerPhone = customerPhone;
		this.orders = orders;
	}

	public Customers() {
		super();
	}

	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", customerName=" + customerName + ", customerMail="
				+ customerMail + ", customerPhone=" + customerPhone + ", orders=" + orders + "]";
	}

}
