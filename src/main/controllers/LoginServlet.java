package main.controllers;

import main.dao.UserLoginDAO;
import main.dao.UserDAO;
import main.models.UserLogin;
import main.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {

        // get session UUID token
        String loginToken = (String) request.getSession().getAttribute("token");

        // if token exists ...
        if(loginToken != null) {
            UserLoginDAO ulDAO = new UserLoginDAO();
            ulDAO.makeConnection();
                List<UserLogin> ulList = ulDAO.findAll();
            ulDAO.closeConnection();

            // find UserLogin to match token?
            UserLogin uLogin = null;
            for (UserLogin ul : ulList) {
                if (ul.getLoginToken().equals(loginToken)) {
                    ulDAO.makeConnection();
                        uLogin = ul;
                    ulDAO.closeConnection();
                }
            }

            if (uLogin == null) { // no logins match token
                request.getSession().removeAttribute("token");
                request.setAttribute("message", "TOKEN MISMATCH!");
                getServletContext().getRequestDispatcher("/login.jsp")
                        .forward(request, response);
            } else {
                // redirect to home
                response.sendRedirect("home");
            }
        } else { // no token exists
            request.setAttribute("message", "NO TOKEN!");
            getServletContext().getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        UserDAO uDAO = new UserDAO();
        uDAO.makeConnection();
            List<User> uList = uDAO.findAll();
        uDAO.closeConnection();

        Boolean isSuccessful = false;
        User u = null;
        for(User user : uList){
            if(user.getUserName().equalsIgnoreCase(userName)
            && user.getPassword().equals(password)){
                isSuccessful = true;
                u = user;
            }
        }

        if (isSuccessful) {
            System.out.println("LOGGED IN!!!");
            String token = UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("token", token);

            System.out.println("SAVING TOKEN: " + token);
            UserLoginDAO ulDAO = new UserLoginDAO();
            ulDAO.makeConnection();
                UserLogin newLogin = new UserLogin(u.getId(), token);
                ulDAO.add(newLogin);
            ulDAO.closeConnection();

            response.sendRedirect("home");
        } else {
            request.setAttribute("message", "Invalid Username or Password");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}