package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.Order;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "view_suspended_orders")
@IdClass(UserSuspendedOrder.PK.class)
@NamedQuery(name = "UserSuspendedOrder.findByUserId", query = "select o from UserSuspendedOrder o where o.userId = :userId")
public class UserSuspendedOrder {

    static class PK implements Serializable{

        @Column(name = "user_id")
        private int userId;

        @Column(name = "order_id")
        private int orderId;

        public PK(){

        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof PK)) return false;
            return userId == ((PK) o).userId && orderId == ((PK) o).orderId;
        }

        @Override
        public int hashCode() {
            int result = userId;
            result = 31 * result + orderId;
            return result;
        }
    }

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "order_id")
    private int orderId;

    @PrimaryKeyJoinColumn
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public Order getOrder(){
        return order;
    }
}
