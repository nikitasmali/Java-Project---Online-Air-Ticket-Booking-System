package com.airline.entity;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

@Component
public class TicketGenerationPdf {
	private static final String FILE_NAME = "F://pdf/TicketDetails.pdf";


    public static void TicketGeneration(TicketBooking ticketBooking) {

        Document document = new Document(PageSize.A4, 20, 20, 20, 20);

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open
            document.open();

            Font f1 = new Font();
            f1.setStyle(Font.BOLD);
            f1.setSize(10);
            
            Paragraph p = new Paragraph("",f1);
            p.add(ticketBooking.getAId().getAirlineName()+" "+ "Ticket Id: "+ ticketBooking.getTicketId());
            document.add(p);
            
            Paragraph p1 = new Paragraph();
            p1.add(ticketBooking.getPId().getName());
            p1.setAlignment(Element.ALIGN_LEFT);
            
            
            document.add(p1);
            
            Chunk linebreak = new Chunk(new DottedLineSeparator());
            document.add(linebreak); 
            
            Paragraph p2 = new Paragraph();
            p2.add("From: "+ ticketBooking.getFId().getSource()+"  "+ " To: "+
            		ticketBooking.getFId().getDestination()+" "+" Travel Date: "+ ticketBooking.getFId().getDate().toString()); //no alignment
            document.add(p2);

            Paragraph p3 = new Paragraph();
            p3.add("Flight Id: "+ ticketBooking.getFId().getFlightId()+"  "+"Boarding Time: "+ ticketBooking.getFId().getTime());
            
            
            document.add(p3);
            
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);

            Paragraph p4 = new Paragraph("Gate Closes 20 mins before Departure.",f);
            p4.setAlignment(Element.ALIGN_BOTTOM);
            document.add(p4);

            //close
            document.close();

            System.out.println("Ticket has been downloaded in your system");
         
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}

