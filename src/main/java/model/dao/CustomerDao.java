package model.dao;

import jakarta.persistence.*;

@Entity(name = "Customer")
@Table(name = "customers")
public class CustomerDao {
    private Integer customerId;
    private String name;
    private String country;


    public CustomerDao() {

    }

    @Id
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
