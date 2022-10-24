package model.dao;

import jakarta.persistence.*;

@Entity(name = "Company")
@Table(name = "companies")
public class CompanyDao {
    Integer companyId;
    String name;
    String country;

    public CompanyDao() {

    }

    @Id
    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
