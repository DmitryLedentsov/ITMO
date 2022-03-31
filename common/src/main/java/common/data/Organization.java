package common.data;

import java.io.Serializable;

/**
 * Organization class
 */
public class Organization implements Validatable, Serializable {
    private final String fullName; //Длина строки не должна быть больше 1237, Строка не может быть пустой, Поле может быть null
    private final OrganizationType type; //Поле не может быть null

    public Organization(String name, OrganizationType t) {
        fullName = name;
        type = t;
    }


    /**
     * @return String
     */
    public String getFullName() {
        return fullName;
    }


    /**
     * @return OrganizationType
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        String s = "";
        s += "{";
        if (fullName != null) s += "\"fullName\" : " + "\"" + getFullName() + "\"" + ", ";
        s += "\"type\" : " + "\"" + getType().toString() + "\"" + "}";
        return s;
    }

    public boolean validate() {
        return (
                (fullName == null || (!fullName.equals("") && !(fullName.length() > 1237))) &&
                        type != null
        );
    }
}
