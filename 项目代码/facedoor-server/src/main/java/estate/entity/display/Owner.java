package estate.entity.display;

import java.io.File;
import java.util.Set;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class Owner
{
    private int ownerId;
    private String phone;
    private String name;
    private String sex;
    private String birthday;
    private String urgentName;
    private String urgentPhone;
    private String identityType;
    private String identityCode;
    private String vehicleIdIst;
    private String propertyIdList;
    private String authenticationTime;
    private String room;
    private String source;
    
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private Set<Property> propertyEntities;

    private Set<Family> families;

    private String faceURL;


	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getFaceURL() {
		return faceURL;
	}

	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}

	public String getAuthenticationTime()
    {
        return authenticationTime;
    }

    public void setAuthenticationTime(String authenticationTime)
    {
        this.authenticationTime = authenticationTime;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getIdentityCode()
    {
        return identityCode;
    }

    public void setIdentityCode(String identityCode)
    {
        this.identityCode = identityCode;
    }

    public String getIdentityType()
    {
        return identityType;
    }

    public void setIdentityType(String identityType)
    {
        this.identityType = identityType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(int ownerId)
    {
        this.ownerId = ownerId;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPropertyIdList()
    {
        return propertyIdList;
    }

    public void setPropertyIdList(String propertyIdList)
    {
        this.propertyIdList = propertyIdList;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getUrgentName()
    {
        return urgentName;
    }

    public void setUrgentName(String urgentName)
    {
        this.urgentName = urgentName;
    }

    public String getUrgentPhone()
    {
        return urgentPhone;
    }

    public void setUrgentPhone(String urgentPhone)
    {
        this.urgentPhone = urgentPhone;
    }

    public String getVehicleIdIst()
    {
        return vehicleIdIst;
    }

    public void setVehicleIdIst(String vehicleIdIst)
    {
        this.vehicleIdIst = vehicleIdIst;
    }

    public Set<Property> getPropertyEntities()
    {
        return propertyEntities;
    }

    public void setPropertyEntities(Set<Property> propertyEntities)
    {
        this.propertyEntities = propertyEntities;
    }

    public Set<Family> getFamilies()
    {
        return families;
    }

    public void setFamilies(Set<Family> families)
    {
        this.families = families;
    }
}
