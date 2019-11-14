package entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
public class Route implements TableListable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Column (name = "truck")
    private String truck;
    @Column (name = "driver")
    private String driver;
    @Column (name = "start_hour")
    private String startHr;
    @Column (name = "day")
    private String day;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "route_id")
    private List<Order> orderList;

    public Route(){
        orderList = new ArrayList<>();
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getStartHr() {
        return startHr;
    }

    public void setStartHr(String startHr) {
        this.startHr = startHr;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
