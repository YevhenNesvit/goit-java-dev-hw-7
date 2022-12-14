package controller.companies;

import config.HibernateProvider;
import model.dto.CompanyDto;
import repositories.CompanyRepository;
import utils.CheckCompanies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/getCompanyByIdForm")
public class GetCompanyByIdFormController extends HttpServlet {
    CompanyRepository companyRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        companyRepository = new CompanyRepository(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/companies/getCompanyByIdForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CheckCompanies checkCompanies = new CheckCompanies();

        try {
            List<CompanyDto> companies = new ArrayList<>();
            Integer companyId = Integer.parseInt(req.getParameter("companyId"));
            if (checkCompanies.IsCompanyIdExists(companyId)) {
                companies.add(companyRepository.findById(companyId));
                req.setAttribute("companies", companies);
                req.getRequestDispatcher("/WEB-INF/view/companies/companyById.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/companies/companyIdNotExists.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            req.getRequestDispatcher("/WEB-INF/view/companies/invalidCompanyIdFormat.jsp").forward(req, resp);
        }
    }
}
