package ch.supsi.dti.webapp.blogger.data;

import javax.persistence.Embeddable;

@Embeddable
public class Role {
    private String role;

    public Role(){ }
    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
