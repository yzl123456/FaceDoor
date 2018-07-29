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
@Table(name="door")
public class DoorEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int doorId;
	String location;
	String status;
	String allowOwner;
	String allowGuest;
	String allowWorker;
	String allowOther;
	String autoDelete;
	String totalLimit;//4位0,1二进制 分别 表示 业主、访客、工作人员、其他人员 是否允许进出
	
	public String getTotalLimit() {
		return totalLimit;
	}
	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}
	public String getAutoDelete() {
		return autoDelete;
	}
	public void setAutoDelete(String autoDelete) {
		this.autoDelete = autoDelete;
	}
	public int getDoorId() {
		return doorId;
	}
	public void setDoorId(int doorId) {
		this.doorId = doorId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAllowOwner() {
		return allowOwner;
	}
	public void setAllowOwner(String allowOwner) {
		this.allowOwner = allowOwner;
	}
	public String getAllowGuest() {
		return allowGuest;
	}
	public void setAllowGuest(String allowGuest) {
		this.allowGuest = allowGuest;
	}
	public String getAllowWorker() {
		return allowWorker;
	}
	public void setAllowWorker(String allowWorker) {
		this.allowWorker = allowWorker;
	}
	public String getAllowOther() {
		return allowOther;
	}
	public void setAllowOther(String allowOther) {
		this.allowOther = allowOther;
	}
	
	
	
	
	
}
