package it.polimi.telcoejb.entities;

import it.polimi.telcoejb.utils.UserRole;
import jakarta.persistence.*;

@NamedQuery(name = "Role.findByNames",
            query = "SELECT r FROM Role r WHERE r.role in :roles")
@Table(name = "role")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}