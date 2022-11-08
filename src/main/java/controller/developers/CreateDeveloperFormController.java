package controller.developers;

import config.HibernateProvider;
import model.dao.DeveloperDao;
import repositories.DeveloperRepository;
import utils.CheckCompanies;
import utils.CheckDevelopers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createDeveloperForm")
public class CreateDeveloperFormController extends HttpServlet {
    DeveloperRepository developerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        developerRepository = new DeveloperRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/developers/createDeveloperForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckDevelopers checkDevelopers = new CheckDevelopers();
        CheckCompanies checkCompanies = new CheckCompanies();

        try {
            DeveloperDao developer = new DeveloperDao();
            developer.setDeveloperId(Integer.parseInt(req.getParameter("developerId")));
            developer.setFirstName(req.getParameter("firstName"));
            developer.setLastName(req.getParameter("lastName"));
            developer.setGender(req.getParameter("gender"));
            developer.setAge(Integer.parseInt(req.getParameter("age")));
            developer.setCompanyId(Integer.parseInt(req.getParameter("companyId")));
            developer.setSalary(Integer.parseInt(req.getParameter("salary")));
            if (checkDevelopers.IsDeveloperIdExists(developer.getDeveloperId())) {
                req.getRequestDispatcher("/WEB-INF/view/developers/developerIdAlreadyExists.jsp").forward(req, resp);
            } else if (checkCompanies.IsCompanyIdExists(developer.getCompanyId())) {
                developerRepository.create(developer);
                req.getRequestDispatcher("/WEB-INF/view/developers/developerCreated.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/companies/companyIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/developers/invalidInputsFormat.jsp").forward(req, resp);
        }
    }
}
