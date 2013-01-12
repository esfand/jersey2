package org.sample.endpoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * @author Arun Gupta
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            Client client = ClientFactory.newClient();
            client.configuration().register(new LoggingFilter(Logger.getAnonymousLogger(), true));
            WebTarget target = client.target("http://" 
                    + request.getServerName() 
                    + ":"
                    + request.getServerPort()
                    + request.getContextPath()
                    + "/webresources/fruit");

            // POST
            out.print("POSTing...<br>");
            target.request().post(Entity.text("apple"));
            out.format("POSTed %1$s ...<br>", "apple");

            // PUT
            out.print("<br>PUTing...<br>");
            target.request().put(Entity.text("banana"));
            out.format("PUTed %1$s ...<br>", "banana");

            // GET (all)
            out.print("<br>GETing...<br>");
            String r = target.request().get(String.class);
            out.format("GETed %1$s items ...<br>", r);

            // GET (one)
            out.print("<br>GETing...<br>");
            r = target.path("apple").request().get(String.class);
            out.format("GETed %1$s items ...<br>", r);

            // DELETE
            out.print("<br>DELETEing...<br>");
            target.path("banana").request().delete();
            out.format("DELETEed %1$s items ...<br>", "banana");

            // GET (all)
            out.print("<br>GETing...<br>");
            r = target.request().get(String.class);
            out.format("GETed %1$s items ...<br>", r);
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
