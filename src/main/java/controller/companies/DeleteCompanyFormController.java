package controller.companies;

import config.HibernateProvider;
import repositories.CompanyRepository;
import utils.CheckCompanies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteCompanyForm")
public class DeleteCompanyFormController extends HttpServlet {
    CompanyRepository companyRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        companyRepository = new CompanyRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/companies/deleteCompanyForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckCompanies checkCompanies = new CheckCompanies();

        try {
            Integer companyId = Integer.parseInt(req.getParameter("companyId"));
            if (checkCompanies.IsCompanyIdExists(companyId)) {
                companyRepository.delete(companyId);
                req.getRequestDispatcher("/WEB-INF/view/companies/companyDeleted.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/companies/companyIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/companies/invalidCompanyIdFormat.jsp").forward(req, resp);
        }
    }
}
