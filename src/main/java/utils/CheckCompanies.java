package utils;

import config.HibernateProvider;
import repositories.CompanyRepository;

import java.sql.SQLException;

public class CheckCompanies {
    private final HibernateProvider provider = new HibernateProvider();
    CompanyRepository companyRepository = new CompanyRepository(provider);

    public boolean IsCompanyIdExists(Integer id) throws SQLException {
        for (int i = 0; i < companyRepository.findAll().size(); i++) {
            if(companyRepository.findAll().get(i).getCompanyId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
