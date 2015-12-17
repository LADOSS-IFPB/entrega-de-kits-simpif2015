package br.com.simpif.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusDelivery {

	private long delivered;
	
	private long notDelivered;

	public StatusDelivery(long delivered, long notDelivered) {
		this.delivered = delivered;
		this.notDelivered = notDelivered;
	}
	
	@XmlElement
	public long getDelivered() {
		return delivered;
	}

	public void setDelivered(long delivered) {
		this.delivered = delivered;
	}

	@XmlElement
	public long getNotDelivered() {
		return notDelivered;
	}

	public void setNotDelivered(long notDelivered) {
		this.notDelivered = notDelivered;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Status [delivered: " + this.delivered + ", notDelivered: " 
				+ notDelivered +"]";
	}
}
