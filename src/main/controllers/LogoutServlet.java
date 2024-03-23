package main.controllers;

import main.dao.UserLoginDAO;
import main.models.UserLogin;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // get session UUID token
        String loginToken = (String) request.getSession().getAttribute("token");
        UserLoginDAO ulDAO = new UserLoginDAO();
        ulDAO.makeConnection();
            List<UserLogin> ulList = ulDAO.findAll();
        ulDAO.closeConnection();

        // find UserLogin to match token?
        UserLogin uLogin = new UserLogin();
        for(UserLogin ul : ulList){
            if(ul.getLoginToken().equals(loginToken)){
                ulDAO.makeConnection();
                    uLogin = ul;
                ulDAO.closeConnection();
            }
        }

        if(uLogin != null){
            // delete UserLogin record
            ulDAO.delete(uLogin);
            //delete session attribute
            HttpSession session = request.getSession();
            session.removeAttribute("token");
            session.invalidate();
        } else {
            // case where token exists but no matching UserLogin exists?!?!?
        }

        response.sendRedirect("home");
    }
}
