package it.polimi.telcoejb.entities;

import it.polimi.telcoejb.utils.UserRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
@NamedQuery(name = "User.findInsolvent", query = "SELECT u FROM User u WHERE u.insolvent = true")
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    private String password;

    private boolean insolvent;

    @Column(name = "failed_payments")
    private int failedPayments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;


    public void setFailedPayments(int failedPayments){
        this.failedPayments = failedPayments;
    }

    public int incrementAndGetFailedPayments(){
        this.failedPayments++;
        return failedPayments;
    }

    public int decrementAndGetFailedPayments(){
        this.failedPayments--;
        if(this.failedPayments < 0) this.failedPayments = 0;
        return failedPayments;
    }

    public boolean isInsolvent() {
        return insolvent;
    }

    public void setInsolvent(boolean insolvent) {
        this.insolvent = insolvent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public boolean hasRole(UserRole role){
        return roles.stream().map((Function<Role, Object>) Role::getRole).anyMatch(r -> r == role);
    }
}