package estate.entity.display;

import java.io.File;
import java.util.Set;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class DoorRecord
{
    private int Id;
    private String userName;
    private String doorLocation;
    private String role;
    private String status;//进出状态
    private String doTime;
    private String openWay;//开门方式
    private String faceURL;
    
    
	public String getFaceURL() {
		return faceURL;
	}
	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}
	public String getOpenWay() {
		return openWay;
	}
	public void setOpenWay(String openWay) {
		this.openWay = openWay;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDoorLocation() {
		return doorLocation;
	}
	public void setDoorLocation(String doorLocation) {
		this.doorLocation = doorLocation;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDoTime() {
		return doTime;
	}
	public void setDoTime(String doTime) {
		this.doTime = doTime;
	}
    
}
