package utils;

import config.HibernateProvider;
import services.DeveloperService;

import java.sql.SQLException;

public class CheckDevelopers {
    private final HibernateProvider provider = new HibernateProvider();
    DeveloperService developerService = new DeveloperService(provider);

    public boolean IsDeveloperIdExists(Integer id) throws SQLException {
        for (int i = 0; i < developerService.developersList().size(); i++) {
            if(developerService.developersList().get(i).getDeveloperId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
