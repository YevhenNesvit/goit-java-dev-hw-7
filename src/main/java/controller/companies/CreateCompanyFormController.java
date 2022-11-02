package controller.companies;

import config.HibernateProvider;
import model.dao.CompanyDao;
import repositories.CompanyRepository;
import utils.CheckCompanies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createCompanyForm")
public class CreateCompanyFormController extends HttpServlet {

    CompanyRepository companyRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        companyRepository = new CompanyRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/companies/createCompanyForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckCompanies checkCompanies = new CheckCompanies();

        try {
            Integer companyId = Integer.parseInt(req.getParameter("companyId"));
            CompanyDao company = new CompanyDao();
            company.setCompanyId(companyId);
            company.setName(req.getParameter("companyName"));
            company.setCountry(req.getParameter("country"));
            if (checkCompanies.IsCompanyIdExists(companyId)) {
                req.getRequestDispatcher("/WEB-INF/view/companies/companyIdAlreadyExists.jsp").forward(req, resp);
            } else {
                companyRepository.create(company);
                req.getRequestDispatcher("/WEB-INF/view/companies/companyCreated.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/companies/invalidCompanyIdFormat.jsp").forward(req, resp);
        }
    }
}
