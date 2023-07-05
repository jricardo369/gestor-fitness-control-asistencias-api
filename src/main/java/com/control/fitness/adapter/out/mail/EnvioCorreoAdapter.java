package com.control.fitness.adapter.out.mail;

import java.io.IOException;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

//import java.util.Collections;
//import java.util.Properties;
//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import com.google.api.client.googleapis.json.GoogleJsonError;
//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import org.apache.commons.codec.binary.Base64;
//import com.google.api.services.gmail.model.Message;
//import com.google.auth.http.HttpCredentialsAdapter;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.auth.oauth2.ServiceAccountCredentials;
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.GmailScopes;

@Component
public class EnvioCorreoAdapter {

	@Autowired
	private JavaMailSender javaMailSender;

	Logger log = LoggerFactory.getLogger(EnvioCorreoAdapter.class);

	public void enviarCorreo(String email, String subject, String html) {

		log.info("Envio de correo");
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		try {
			mailMessage.setSubject(subject, "UTF-8");
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");
			helper.setTo(email);
			System.out.println(html);
			System.out.println("Enviando correo....");
			helper.setText(html.trim(), true);
			javaMailSender.send(mailMessage);
			System.out.println("Se envió correo....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void enviarAsync(String email, String subject, String html) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				enviarCorreo(email, subject, html);
			}
		}).start();
	}

	public void enviarCorreoCalendar(String email, String subject, String html) throws IOException {
		log.info("Envio de correo");
		MimeMessage mailMessage = javaMailSender.createMimeMessage();

		try {
			mailMessage.setSubject(subject, "UTF-8");
			BodyPart mp = new MimeBodyPart();
			StringBuffer buffer = new StringBuffer();
			buffer.append("BEGIN:VCALENDAR\n" + "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n"
					+ "VERSION:2.0\n" + "METHOD:REQUEST\n" + "BEGIN:VEVENT\n"
					+ "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:jricardo369@gmai.com\n"
					+ "ORGANIZER:MAILTO:jricardo369@gmail.com\n" + "DTSTART:20120302T060000Z\n"
					+ "DTEND:20120302T070000Z\n" + "LOCATION:Conference room\n" + "UID:24324324 \n" // Si
																									// la
																									// identificación
																									// es
																									// la
																									// misma,
																									// Outlook
																									// pensará
																									// que
																									// es
																									// la
																									// misma
																									// convocatoria
																									// de
																									// reunión,
																									// así
																									// que
																									// use
																									// uuid.
					+ "CATEGORIES:SuccessCentral Reminder\n"
					+ "DESCRIPTION:This the description of the meeting.<br>asd;flkjasdpfi\n\n"
					+ "SUMMARY:Test meeting request\n" + "PRIORITY:5\n" + "CLASS:PUBLIC\n" + "BEGIN:VALARM\n"
					+ "TRIGGER:-PT15M\n" + "ACTION:DISPLAY\n" + "DESCRIPTION:Reminder\n" + "END:VALARM\n"
					+ "END:VEVENT\n" + "END:VCALENDAR");

			mp.setDataHandler(new DataHandler(
					new ByteArrayDataSource(buffer.toString(), "text/calendar;method=REQUEST;charset=\"UTF-8\"")));
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mp);
			// mailMessage.setContent(multipart);
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");
			helper.setTo(email);
			// log.info(html);
			// helper.setText(html.trim(),true);
			javaMailSender.send(mailMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
//		EnvioCorreoAdapter e = new EnvioCorreoAdapter();
//		try {
//			e.sendEmail("servicios.jv.ma@gmail.com", "jose.vazquezj@agilethought.com");
//		} catch (MessagingException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
	}

}
