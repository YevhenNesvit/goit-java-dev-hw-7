package controller.projects;

import config.HibernateProvider;
import model.dao.ProjectDao;
import repositories.ProjectRepository;
import utils.CheckCompanies;
import utils.CheckCustomers;
import utils.CheckProjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createProjectForm")
public class CreateProjectFormController extends HttpServlet {
    ProjectRepository projectRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        projectRepository = new ProjectRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/projects/createProjectForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckProjects checkProjects = new CheckProjects();
        CheckCustomers checkCustomers = new CheckCustomers();
        CheckCompanies checkCompanies = new CheckCompanies();

        try {
            Integer projectId = Integer.parseInt(req.getParameter("projectId"));
            ProjectDao project = new ProjectDao();
            project.setProjectId(projectId);
            project.setName(req.getParameter("projectName"));
            project.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
            project.setCompanyId(Integer.parseInt(req.getParameter("companyId")));
            project.setCost(Integer.parseInt(req.getParameter("cost")));
            project.setCreationDate(java.sql.Date.valueOf(req.getParameter("creationDate")));
            if (checkProjects.IsProjectIdExists(projectId)) {
                req.getRequestDispatcher("/WEB-INF/view/projects/projectIdAlreadyExists.jsp").forward(req, resp);
            } else if (checkCustomers.IsCustomerIdExists(project.getCustomerId())) {
                if (checkCompanies.IsCompanyIdExists(project.getCompanyId())) {
                    projectRepository.create(project);
                    req.getRequestDispatcher("/WEB-INF/view/projects/projectCreated.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/WEB-INF/view/companies/companyIdNotExists.jsp").forward(req, resp);
                }
            } else {
                req.getRequestDispatcher("/WEB-INF/view/customers/customerIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/projects/invalidInputsFormat.jsp").forward(req, resp);
        }
    }
}
