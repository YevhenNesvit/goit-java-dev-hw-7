package utils;

import config.HibernateProvider;
import repositories.CompanyRepository;

import java.sql.SQLException;

public class CheckCompanies {
    private final HibernateProvider provider = new HibernateProvider();
    CompanyRepository companyRepository = new CompanyRepository(provider);

    public boolean IsCompanyIdExists(Integer id) throws SQLException {
        for (int i = 0; i < companyRepository.companiesList().size(); i++) {
            if(companyRepository.companiesList().get(i).getCompanyId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
