package id.aldente.socket.model.payload;

/**
 * Created by f.putra on 10/25/20.
 */
public class DepartementPayload {

    private String senderId;
    private String method;
    private Integer id;
    private String code;
    private String departement;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "DepartementPayload{" +
                "senderId='" + senderId + '\'' +
                ", method='" + method + '\'' +
                ", id=" + id +
                ", code='" + code + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }
}
