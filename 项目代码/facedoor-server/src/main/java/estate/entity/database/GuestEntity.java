package estate.entity.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by 应泽林 on 18-1-16.
 */
@Entity
@Table(name="guest")
public class GuestEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int guestId;
	private String name;
	private String sex;
	private String relativeOwner;
	private String phone;
	private String validTime;
	private String faceURL;
	private String source;
	private String visitLimit;
	
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getVisitLimit() {
		return visitLimit;
	}
	public void setVisitLimit(String visitLimit) {
		this.visitLimit = visitLimit;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRelativeOwner() {
		return relativeOwner;
	}
	public void setRelativeOwner(String relativeOwner) {
		this.relativeOwner = relativeOwner;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public String getFaceURL() {
		return faceURL;
	}
	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}
	

	
	
	
}
