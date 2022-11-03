package controller.projects;

import config.HibernateProvider;
import model.dto.ProjectDto;
import repositories.ProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/getProjects")
public class GetProjectsController extends HttpServlet {
    ProjectRepository projectRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        projectRepository = new ProjectRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<ProjectDto> projects = projectRepository.findAll();
            req.setAttribute("projects", projects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/view/projects/getProjects.jsp").forward(req, resp);
    }
}
