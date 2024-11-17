package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/BusReservationServlet") // Ensure this matches the action in the form
public class BusReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        String seats = request.getParameter("seats");

        // Specify the custom directory path for storing the .txt files
        String customPath = "C:\\Users\\sahil\\OneDrive\\Desktop\\TY\\BusReservationSystem_information\\";

        // Create the reservations directory if it doesn't exist
        File reservationsDir = new File(customPath);
        if (!reservationsDir.exists()) {
            boolean dirCreated = reservationsDir.mkdirs();  // Try to create the directory
            if (!dirCreated) {
                response.setContentType("text/html");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<script>alert('Failed to create reservations directory.');</script>");
                response.getWriter().println("</body></html>");
                return;
            }
        }

        // Create the file with the name based on the user's name
        File file = new File(reservationsDir, name + ".txt");

        // Write the data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Phone: " + phone);
            writer.newLine();
            writer.write("Source: " + source);
            writer.newLine();
            writer.write("Destination: " + destination);
            writer.newLine();
            writer.write("Date: " + date);
            writer.newLine();
            writer.write("Seats: " + seats);
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception for debugging
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<script>alert('Error writing to the file.');</script>");
            response.getWriter().println("</body></html>");
            return;
        }

        // Respond with a custom HTML message
        response.setContentType("text/html");
        response.getWriter().println("<html><head><title>Reservation Success</title></head><body>");
        response.getWriter().println("<script>alert('Thank you for your contribution. Have a great journey!');</script>");
        response.getWriter().println("<h1>Thank You!</h1>");
        response.getWriter().println("<p>Thank you for registering with us. Your journey details have been saved successfully.</p>");
        response.getWriter().println("<p>We hope you have a safe and enjoyable trip!</p>");
        response.getWriter().println("</body></html>");
    }
}
