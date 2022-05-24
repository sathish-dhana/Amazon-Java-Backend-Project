package com.masai.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Column(name = "order_id")
	private Integer orderId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(name = "items")
	private List<Item> orderedItems;
	
	@Column(name = "products_cost")
	private Double itemsCost;
	
	@Column(name = "gst")
	private Double gst;
	
	@Column(name = "delivery_charge")
	private Double deliveryCharge;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "shippedFrom", column = @Column(name = "shipped_from_address")),
		  @AttributeOverride( name = "shippedTo", column = @Column(name = "shipped_to_address")),
		  @AttributeOverride( name = "expectedDate", column = @Column(name = "expected_delivery_date"))
		})
	private Shipment shipment;

	//Last 4 digits of card used to make the payment
	@Column(name = "card_used_for_payment")
	private String cardUsedForPayment;
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
}
