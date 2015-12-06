package br.com.simpif.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tb_user")
@NamedQuery(name = "User.getAll", query = "from User")
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Integer id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "is_delivered", insertable = false, updatable = true)
	private Boolean isDelivered;
	
	@Column(name = "type_inscription")
	private String typeInscription;
	
	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public Boolean getIsDelivered() {
		return isDelivered;
	}
	
	public void setIsDelivered(Boolean isDelivered) {
		this.isDelivered = isDelivered;
	}
	
	@XmlElement
	public String getTypeInscription() {
		return typeInscription;
	}
	
	public void setTypeInscription(String typeInscription) {
		this.typeInscription = typeInscription;
	}	
}
