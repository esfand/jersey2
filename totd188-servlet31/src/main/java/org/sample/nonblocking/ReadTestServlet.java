package org.sample.nonblocking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Arun Gupta
 */
@WebServlet(urlPatterns = {"/ReadTestServlet"}, asyncSupported=true)
public class ReadTestServlet extends HttpServlet {

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
        try (PrintWriter output = response.getWriter()) {
            output.println("<html>");
            output.println("<head>");
            output.println("<title>Servlet ReadTestServlet</title>");
            output.println("</head>");
            output.println("<body>");
            output.println("<h1>Servlet ReadTestServlet at " + request.getContextPath() + "</h1>");

            AsyncContext context = request.startAsync();

            ServletInputStream input = request.getInputStream();

            input.setReadListener(new MyReadListener(input, context));

            // TODO: This is redundant code and should only be 
            // specified once in MyReadListener. For now, all the data from
            // InputStream need to be read before ReadListener is engaged.
            // EG has already agreed on simplification and the implementation
            // will be updated to reflect that. This sample will be updated 
            // after that.
            int b = -1;
            while (input.isReady() && ((b = input.read()) != -1)) {
                System.out.println("-->" + (char) b);
                output.write(b);
            }

            output.println("</body>");
            output.println("</html>");
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
