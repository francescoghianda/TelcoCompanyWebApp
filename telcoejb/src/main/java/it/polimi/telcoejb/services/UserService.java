package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.exception.UserAlreadyExistsException;
import it.polimi.telcoejb.exception.UserNotFoundException;
import it.polimi.telcoejb.utils.PasswordCipher;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.ws.rs.InternalServerErrorException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    @EJB
    private PasswordCipher passwordCipher;

    public void register(String username, String email, String password) throws UserAlreadyExistsException {
        try{
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordCipher.hash(password));
            em.persist(user);
            em.flush();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
        catch (EntityExistsException e){
            throw new UserAlreadyExistsException();
        }
    }

    public User login(String username, String password) throws UserNotFoundException {
        try{
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).setParameter("username", username).getSingleResult();
            if(!passwordCipher.check(password, user.getPassword())) return null;
            return user;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
        catch (NoResultException e){
            throw new UserNotFoundException();
        }
    }

}
