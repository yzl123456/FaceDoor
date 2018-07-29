package estate.entity.database;

/**
 * Created by 应泽林 on 18-1-16.
 */
public class OwnerEntity
{
    private Integer id;
    private String phone;
    private String name;
    private Byte sex;
    private Long birthday;
    private String urgentName;
    private String urgentPhone;
    private Byte identityType;
    private String identityCode;
    private String vehicleIdIst;
    private String propertyIdList;
    private Long authenticationTime;
    private String faceURL;
    private String age;
    private String role;
    private String room;
    private String source;
    
    
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFaceURL() {
		return faceURL;
	}

	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}

	public OwnerEntity(){}

    public OwnerEntity(String phone, String name)
    {
        this.name=name;
        this.phone=phone;
    }


    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Byte getSex()
    {
        return sex;
    }

    public void setSex(Byte sex)
    {
        this.sex = sex;
    }

    public Long getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Long birthday)
    {
        this.birthday = birthday;
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

    public Byte getIdentityType()
    {
        return identityType;
    }

    public void setIdentityType(Byte identityType)
    {
        this.identityType = identityType;
    }

    public String getIdentityCode()
    {
        return identityCode;
    }

    public void setIdentityCode(String identityCode)
    {
        this.identityCode = identityCode;
    }

    public String getVehicleIdIst()
    {
        return vehicleIdIst;
    }

    public void setVehicleIdIst(String vehicleIdIst)
    {
        this.vehicleIdIst = vehicleIdIst;
    }

    public String getPropertyIdList()
    {
        return propertyIdList;
    }

    public void setPropertyIdList(String propertyIdList)
    {
        this.propertyIdList = propertyIdList;
    }

    public Long getAuthenticationTime()
    {
        return authenticationTime;
    }

    public void setAuthenticationTime(Long authenticationTime)
    {
        this.authenticationTime = authenticationTime;
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
