package utils;

import config.HibernateProvider;
import services.CompanyService;

import java.sql.SQLException;

public class CheckCompanies {
    HibernateProvider provider = new HibernateProvider();
    CompanyService companyService = new CompanyService(provider);

    public boolean IsCompanyIdExists(Integer id) throws SQLException {
        for (int i = 0; i < companyService.companiesList().size(); i++) {
            if(companyService.companiesList().get(i).getCompanyId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
