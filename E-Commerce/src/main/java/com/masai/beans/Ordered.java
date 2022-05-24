package com.masai.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Orders")
public class Ordered {
	
	//WITH CUSTOMER
	//Order has ManyToOne relation with customer
	//One order can have one customer
	//One user can have multiple orders
	//Unidirectional from customer
	
	//WITH ITEM
	//Order can have multiple items
	//one item can only be in one order
	//Order has oneToMany unidirectional with item
	
	//WITH SHIPMENT
	//Order has one to one with shipment
	//One order can have one shipment
	//Uni directional with shipment as well
	
	//CHANGES TO MAKE WHEN ORDER IS PLACED
	//To Trigger --> Call the payment method
	
	//Once the payment is made 
	
	//--> Empty the cart and give the reference to the items
	//	  from this class object
	
	//--> Reduce the quantity of the products 
	
	//--> Add the placed order in the order list of the customer
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Item> orderedItems;
	
	@Column(name = "items_cost")
	private Double itemsCost;
	
	@Column(name = "gst")
	private Double gst;
	
	@Column(name = "delivery_charge")
	private Double deliveryCharge;
	
	@Column(name = "total")
	private Double totalAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Shipment shipment;

	//Last 4 digits of card used to make the payment
	@Column(name = "payment_details")
	private String cardUsedForPayment;
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
}
