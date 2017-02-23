package authdemo.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "MESSAGE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID")})
public class MessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	
	@Column(name = "SENDER", unique = false, nullable = false)
	private String sender;
	
	@Column(name = "RECEIVER", unique = false, nullable = false)
	private String receiver;
	
	@Column(name = "TEXT", unique = false, nullable = false)
	private String text;
	
	@Column(name = "DATE", unique = false, nullable = false)
	private String date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getMessage() {
		return text;
	}

	public void setMessage(String text) {
		this.text = text;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
