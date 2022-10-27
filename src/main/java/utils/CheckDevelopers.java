package utils;

import config.HibernateProvider;
import repositories.DeveloperRepository;

import java.sql.SQLException;

public class CheckDevelopers {
    private final HibernateProvider provider = new HibernateProvider();
    DeveloperRepository developerRepository = new DeveloperRepository(provider);

    public boolean IsDeveloperIdExists(Integer id) throws SQLException {
        for (int i = 0; i < developerRepository.developersList().size(); i++) {
            if(developerRepository.developersList().get(i).getDeveloperId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
