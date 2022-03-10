package it.polimi.telcowebemployee.controllers;

import it.polimi.telcoejb.services.ServiceService;
import it.polimi.telcoejb.utils.ServiceType;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "NewServiceController", value = "/new-service")
public class NewServiceController extends HttpServlet {

    @EJB
    private ServiceService serviceService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String serviceTypeStr = req.getParameter("service_type");

        if(serviceTypeStr == null){
            resp.sendError(400);
            return;
        }

        try{
            ServiceType serviceType = ServiceType.valueOf(serviceTypeStr);

            if(serviceType == ServiceType.MOBILE_INTERNET || serviceType == ServiceType.FIXED_INTERNET){
                int gb = Integer.parseInt(req.getParameter("gigabytes"));
                float gbFee = Float.parseFloat(req.getParameter("extra_gigabytes_fee"));
                serviceService.createInternetService(serviceType, gb , gbFee);
            }
            else if(serviceType == ServiceType.MOBILE_PHONE){
                int minutes = Integer.parseInt(req.getParameter("minutes"));
                int sms = Integer.parseInt(req.getParameter("sms"));
                float minutesFee = Float.parseFloat(req.getParameter("extra_minutes_fee"));
                float smsFee = Float.parseFloat(req.getParameter("extra_sms_fee"));
                serviceService.createPhoneService(serviceType, minutes, sms, minutesFee, smsFee);
            }
            else{
                resp.sendError(400, "Invalid service type.");
            }
        }
        catch(NullPointerException | NumberFormatException e){
            resp.sendError(400);
        }
        catch (IllegalArgumentException e1){
            resp.sendError(400, "Invalid service type");
        }

        resp.sendRedirect(req.getContextPath()+"/");

    }
}
