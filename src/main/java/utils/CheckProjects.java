package utils;

import config.HibernateProvider;
import repositories.ProjectRepository;

import java.sql.SQLException;

public class CheckProjects {
    private final HibernateProvider provider = new HibernateProvider();
    ProjectRepository projectRepository = new ProjectRepository(provider);

    public boolean IsProjectIdExists(Integer id) throws SQLException {
        for (int i = 0; i < projectRepository.findAll().size(); i++) {
            if(projectRepository.findAll().get(i).getProjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
