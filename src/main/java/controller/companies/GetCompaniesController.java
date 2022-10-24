package controller.companies;

import config.HibernateProvider;
import model.dto.CompanyDto;
import services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/getCompanies")
public class GetCompaniesController extends HttpServlet {
    CompanyService companyService;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        companyService = new CompanyService(provider);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<CompanyDto> companies = companyService.companiesList();
            req.setAttribute("companies", companies);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/view/companies/getCompanies.jsp").forward(req, resp);
    }
}
