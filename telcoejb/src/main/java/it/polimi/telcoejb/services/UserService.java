package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.Role;
import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.exception.UserAlreadyExistsException;
import it.polimi.telcoejb.exception.UserNotFoundException;
import it.polimi.telcoejb.utils.PasswordCipher;
import it.polimi.telcoejb.utils.UserRole;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    @EJB
    private PasswordCipher passwordCipher;

    public void register(String username, String email, String password, Set<UserRole> roles) throws UserAlreadyExistsException {
        try{
            Set<Role> roleSet = new HashSet<>(em.createNamedQuery("Role.findByNames", Role.class)
                    .setParameter("roles", roles)
                    .getResultList());

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordCipher.hash(password));
            user.setRoles(roleSet);
            em.persist(user);
            em.flush();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        catch (EntityExistsException e){
            throw new UserAlreadyExistsException();
        }
    }

    /**
     * Check the credential of the user
     * @param username the username of the user
     * @param password the password of the user
     * @return the entity of the user if the credential are correct, otherwise, return null
     * @throws UserNotFoundException if the user not exist
     */
    public User login(String username, String password) throws UserNotFoundException {
        try{
            User user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getSingleResult();
            if(!passwordCipher.check(password, user.getPassword())) return null;
            return user;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        catch (NoResultException e){
            throw new UserNotFoundException();
        }
    }

    public User findByUsername(String username){
        return em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getSingleResult();
    }

    public void save(User user){
        em.merge(user);
    }

}
