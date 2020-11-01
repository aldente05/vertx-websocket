package id.aldente.socket.model.payload;

/**
 * Created by f.putra on 10/25/20.
 */
public class DepartementPayload {

    private Integer id;
    private String code;
    private String departement;
    private String account;

    public DepartementPayload() {
        this.id = id;
        this.code = code;
        this.departement = departement;
        this.account = account;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "DepartementPayload{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", departement='" + departement + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
