package utils;

import config.HibernateProvider;
import repositories.ProjectRepository;

import java.sql.SQLException;

public class CheckProjects {
    private final HibernateProvider provider = new HibernateProvider();
    ProjectRepository projectRepository = new ProjectRepository(provider);

    public boolean IsProjectIdExists(Integer id) throws SQLException {
        for (int i = 0; i < projectRepository.projectsList().size(); i++) {
            if(projectRepository.projectsList().get(i).getProjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
