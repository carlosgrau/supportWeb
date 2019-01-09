/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.bean.ReplyBean;
import com.constant.ConfigurationConstants;
import com.factory.ServiceFactory;
import com.helper.JsonHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a021792876p
 */
public class json extends HttpServlet {
   private static final long serialVersionUID = 1L;

    public json() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        response.setContentType("application/json;charset=UTF-8");

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, x-requested-with, Content-Type");

        String strJson = "";
        JsonHelper json = new JsonHelper();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            strJson = "{\"status\":500,\"msg\":\"jdbc driver not found\"}";
        }
        try {
            ReplyBean oReplyBean = ServiceFactory.executeService(request);
            strJson = json.strJson(oReplyBean.getStatus(), oReplyBean.getJson());
        } catch (Exception e) {
            response.setStatus(500);
            strJson = json.strJson(500, "Server Error"+"----"+e);
            if (ConfigurationConstants.environment == ConfigurationConstants.EnvironmentConstans.Debug) {
                PrintWriter out = response.getWriter();
                out.println(e.getMessage());
                e.printStackTrace(out);
            }
        }
        response.getWriter().append(strJson).close();
    }
}
