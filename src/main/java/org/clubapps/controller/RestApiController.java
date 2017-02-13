package org.clubapps.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.clubapps.dao.*;
import org.clubapps.exceptions.*;
import org.clubapps.model.Booking;
import org.clubapps.model.EmailMessage;
import org.clubapps.model.Member;
import org.clubapps.model.NewsStory;
import org.clubapps.model.SessionPlan;
import org.clubapps.model.SessionRecord;
import org.clubapps.model.Team;
import org.clubapps.model.Worker;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@CrossOrigin
@RestController
public class RestApiController {
	java.sql.Timestamp currentTimestamp = null;
	private final Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	public RestApiController(){}
	
	MySqlDAO dao=new MySqlDAO();
	
	/*
	 * Used during authentication. If Spring Security has authenticated the user, then
	 * the user will be passed back to the client, if not a 401 response will be returned.
	 */
	@RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }

	 @RequestMapping(value="/admin/team/{teamId}",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Member> getMembersByTeam(@PathVariable int teamId) {
		 log.debug("## [RestApiController]->getMembersByTeam(" + teamId + ")");
		 List<Member> members=dao.getMembersByTeam(teamId);
		 return members;
	
	 }
	 
	 @RequestMapping(value="/admin/teams",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Team> getTeams() {
		 log.debug("## [RestApiController]->getTeams()..");
		 List<Team> teams=dao.getAllTeams();
		 return teams;
	
	 }
	 
	 @RequestMapping(value="/teams",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Team> getAllTeams() {
		 log.debug("## [RestApiController]->getTeams()..");
		 List<Team> teams=dao.getAllTeams();
		 return teams;
	
	 }
	 
	 @RequestMapping(value="/stories",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<NewsStory> getNewsStories() {
		 log.debug("##                        |-> getNewsStories()..");
		 List<NewsStory> stories=dao.getNewsStories();
		 log.debug("##                        |<- getNewsStories()..");
		 return stories;
	
	 }
	 
	 @RequestMapping(value="/team/{teamName}",method = RequestMethod.GET,headers="Accept=application/json")
	 public Team getTeamDetails(@PathVariable String teamName) {
		 log.debug("## [RestApiController]->getTeamDetails(" + teamName + ")..");
		 Team team=dao.getTeamDetails(teamName);
		 return team;
	
	 }
	 
	 @RequestMapping(value="/teammembers/{teamName}",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Member> getMembersByTeam(@PathVariable String teamName) {
		 log.debug("## [RestApiController]->getMembersByTeam(" + teamName + ")");
		 List<Member> members=dao.getMembersByTeam(dao.getTeamId(teamName));
		 return members;
	
	 }
	 
	 @RequestMapping(value="/academy/news",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<NewsStory> getAcademyNews() {	 
	  List<NewsStory> news=dao.getAcademyNews();
	  return news;
	
	 }
	 
	 @RequestMapping(value="/admin/submitNews",method = RequestMethod.POST)
	 public void submitNewsStory(@RequestBody NewsStory newsStory) {	 
		 dao.submitNewsStory( newsStory );	
	 }
	 
	 @RequestMapping(value="/admin/story",method = RequestMethod.DELETE)
	 public void deleteNewsStory(@RequestBody NewsStory newsStory) {	 
		 dao.deleteNewsStory( newsStory );	
	 }
	 
	 @RequestMapping(value="/admin/story",method = RequestMethod.PUT)
	 public void updateNewsStory(@RequestBody NewsStory newsStory) {	 
		 dao.updateNewsStory( newsStory );	
	 }
	 
	 @RequestMapping(value="/admin/members",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Member> getAllTasks() {
		 log.debug("## [RestApiController]->getAllTasks()..");
	  List<Member> members=dao.getAllMembers();
	  return members;
	
	 }
	 
	 @RequestMapping(value="/admin/member",method = RequestMethod.POST)
	 public void addMember(@RequestBody Member member) {	 
		 dao.addMember( member );	
	 }
	 
	 @RequestMapping(value="/admin/member",method = RequestMethod.PUT)
	 public void updateMemberDetails(@RequestBody Member member) {	 
		 dao.updateMemberDetails( member );
	 }
	 
	 @RequestMapping(value="/admin/member/{memberId}",method = RequestMethod.DELETE)
	 public void deleteMemberDetails(@RequestBody int memberId) {	 
		 dao.deleteMemberDetails( memberId );	
	 }	 

	 
	 @RequestMapping(value="/admin/upload",method = RequestMethod.POST)
	 public void uploadNewsPic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	 {
		 currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		 System.out.println(currentTimestamp + ": ## [RestApiController]->uploadNewsPic()");
		 
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ^^^^^^^^^^^^^^^^^^^^ NEWS UPLOAD RECEIVED ^^^^^^^^^^^^^^^^^^^^ ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ^^^^^^^^^^^^^^^^^^^^ NEWS UPLOAD RECEIVED ^^^^^^^^^^^^^^^^^^^^ ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ^^^^^^^^^^^^^^^^^^^^ NEWS UPLOAD RECEIVED ^^^^^^^^^^^^^^^^^^^^ ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ^^^^^^^^^^^^^^^^^^^^ NEWS UPLOAD RECEIVED ^^^^^^^^^^^^^^^^^^^^ ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ^^^^^^^^^^^^^^^^^^^^ NEWS UPLOAD RECEIVED ^^^^^^^^^^^^^^^^^^^^ ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ############################################################## ##");
		 System.out.println("## ^^^^^^^^^^^^^^^^^^^^ NEWS UPLOAD RECEIVED ^^^^^^^^^^^^^^^^^^^^ ##");
		 System.out.println("## ############################################################## ##");
		 
		 dao.uploadNews(req, res);
	 }
	 
	 @RequestMapping(value="/admin/team",method = RequestMethod.POST)
	 public void addTeam(@RequestBody Team team) {	 
		 dao.addTeam( team );
	 }
	 
	 @RequestMapping(value="/admin/team",method = RequestMethod.PUT)
	 public void updateTeam(@RequestBody Team team) {	 
		 dao.updateTeam( team );
	 }
	 
	 @RequestMapping(value="/admin/team",method = RequestMethod.DELETE)
	 public void deleteTeam(@RequestBody int teamId) {	 
		 dao.deleteTeam( teamId );
	 }
	 
	 @RequestMapping(value="/admin/sessionplan/{teamId}",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<SessionPlan> getTrainingSessions(@PathVariable int teamId) {	 
	  List<SessionPlan> sessions=dao.getTrainingSessionsForTeam(teamId);
	  return sessions;	
	 }
	 
	 @RequestMapping(value="/admin/session",method = RequestMethod.POST)
	 public void addTrainingSession(@RequestBody SessionPlan session)
	 {
		 System.out.println("## [RestApiController]->addTrainingSession(): " + session);
		 dao.addTrainingSessionPlan(session);
	 }
	 
	 @RequestMapping(value="/admin/sessionrec",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<SessionRecord> getSessionRecords() {	 
	  List<SessionRecord> sessions=dao.getSessionRecords();
	  return sessions;	
	 }
	 
	 @RequestMapping(value="/admin/sessionrec/{teamid}",method = RequestMethod.GET,headers="Accept=application/json")
	 public  List<SessionRecord> getSessionRecordsForTeam(@PathVariable int teamid) {	 
		 List<SessionRecord> sessions=dao.getSessionRecordsForTeam(teamid);
		 return sessions;	
	 }
	 
	 @RequestMapping(value="/admin/sessionrec/{sessionid}/{teamid}/{userid}",method = RequestMethod.GET,headers="Accept=application/json")
	 public  SessionRecord getSessionRecordsForTeam(@PathVariable int sessionid, @PathVariable int teamid, @PathVariable int memberid) {	 
		 SessionRecord session=dao.getSessionRecordForMember(sessionid,teamid,memberid);
		 return session;	
	 }
	 
	 @RequestMapping(value="/admin/sessionrec",method = RequestMethod.PUT)
	 public void setSessionRecord(@RequestBody SessionRecord sr) {	 
		 dao.setSessionRecordForMember(sr);	
	 }
	 
	 @RequestMapping(value="/admin/sessionrec",method = RequestMethod.POST,headers="Accept=application/json")
	 public void insertSessionRecord(@RequestBody SessionRecord sr) {	 
		 dao.insertSessionRecordForMember(sr);	
	 }
	 
	 
	 @RequestMapping(value="/admin/user",method = RequestMethod.GET,headers="Accept=application/json")
	 public Worker getUserName(Principal principal) {
		 User activeUser = (User) ((Authentication) principal).getPrincipal();
		 String username = activeUser.getUsername();
		 Worker thisUser = dao.getUserByName(username);
		 return thisUser;	
	 }
	 
	 @RequestMapping(value="/admin/user",method = RequestMethod.PUT)
	 public void updateUser(@RequestBody Worker user) {	 
		 dao.updateUser( user );
	 }
	 
	 @RequestMapping(value="/admin/user",method = RequestMethod.POST)
	 public void addUser(@RequestBody Worker user) {	 
		 dao.addUser( user );
	 }
	 
	 @RequestMapping(value="/admin/password",method = RequestMethod.PUT)
	 public void updateUserPassword(@RequestBody Worker user) {	 
		 dao.updateUserPassword( user );
	 }
	 
	 @RequestMapping(value="/admin/user",method = RequestMethod.DELETE)
	 public void deleteUser(@RequestBody Worker user) {	 
		 dao.deleteUser( user );
	 }
	 
	 @RequestMapping(value="/admin/users",method = RequestMethod.GET,headers="Accept=application/json")
	 public  List<Worker> getAllUsers() 
	 {
		 log.debug("## ->getAllUsers()");
		 List<Worker> users = dao.getAllUsers();
		 log.debug("## <-getAllUsers(): " + users);
		 return users;	
	 }
	 
	 @RequestMapping(value="/mail",method = RequestMethod.POST)
	 public boolean messageUs(@RequestBody EmailMessage msg) 
	 {	 
	      String destination = "secretary@avenueunited.ie";

	      final String username = "secretary@avenueunited.ie";
	      final String password = "UpThe@venue83";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "mail.avenueunited.ie");//"mocha6004.mochahost.com");
	      props.put("mail.smtp.port", "25");
			
	      //props.setProperty("mail.pop3.host","mocha6004.mochahost.com");//"mail.avenueunited.ie");
	     // props.setProperty("mail.pop3.port", "110");
	      //properties.setProperty("mail.smpt.port", "25");
	      //properties.setProperty("mail.imap.port", "143");

	      // Get the default Session object.
	      Session session = Session.getInstance(props,
	    		  new javax.mail.Authenticator() {
	    			protected PasswordAuthentication getPasswordAuthentication() {
	    				return new PasswordAuthentication(username, password);
	    			}
	    		  });
	      
		 try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(msg.getSenderAddress()));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));

	         // Set Subject: header field
	         message.setSubject(msg.getSubject());

	         // Now set the actual message
	         message.setText(msg.getMessage());

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	         return true;
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	      }
	 }
	 
	 @RequestMapping(value="/confirmbooking",method = RequestMethod.POST)
	 public boolean confirmEmail(@RequestBody Booking booking) 
	 {	 
		 EmailMessage msg = new EmailMessage();
		 String destination = booking.getEmail();
		 msg.setSubject("Avenue United: Booking Confirmation - Automated message, do not reply");
		 msg.setMessage("Confirmation of your booking: \n" +
				 		"Name: " + booking.getFirstname() + " " + booking.getSurname() + "\n" +
				 		"Phone: " + booking.getPhone() + "\n" +
				 		"Arrival: " + booking.getArrivalDate() + "\n" +
				 		"Departure: " + booking.getDepartureDate() + "\n" +
				 		"Number of nights: " + booking.getNumberOfNights() + "\n" +
				 		"Number of people: " + booking.getNumberOfPeople() + "\n" +
				 		"Deposit: " + booking.getDeposit() +  "\n" +
				 		"Total Due: " + booking.getTotalCharge()
				 		);
		 msg.setSenderAddress("booking@avenueunited.ie");

	      final String username = "booking@avenueunited.ie";
	      final String password = "UpThe@venue83";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "mail.avenueunited.ie");
	      props.put("mail.smtp.port", "25");

	      // Get the default Session object.
	      Session session = Session.getInstance(props,
	    		  new javax.mail.Authenticator() {
	    			protected PasswordAuthentication getPasswordAuthentication() {
	    				return new PasswordAuthentication(username, password);
	    			}
	    		  });
	      
		 try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(msg.getSenderAddress()));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));

	         // Set Subject: header field
	         message.setSubject(msg.getSubject());

	         // Now set the actual message
	         message.setText(msg.getMessage());

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	         return true;
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	      }
	 }
	 
	 @RequestMapping(value="/confirmregistration",method = RequestMethod.POST)
	 public void confirmRegistrationEmail(@RequestBody Member member) 
	 {	 
		 log.debug("## ->confirmRegistrationEmail("+member.getName()+")");
		 dao.sendRegistrationDetailsEmail(member);
		 log.debug("## <-confirmRegistrationEmail()");
		 return;
	 }

	 
	 @RequestMapping(value="/admin/manager/{name}",method = RequestMethod.GET,headers="Accept=application/json")
	 public  Worker getManagerDetails(@PathVariable String name) 
	 {
		 log.debug("## ->getManagerDetails()");
		 Worker user = dao.getUserByName(name);
		 log.debug("## <-getManagerDetails(): " + user);
		 return user;	
	 }
	 
	 @RequestMapping(value="/photos/{cat1}/{cat2}",method = RequestMethod.GET,headers="Accept=application/json")
	 public  List<String> getPhotoMedia(@PathVariable String cat1, @PathVariable String cat2) 
	 {
		 log.debug("## ->getPhotoMedia("+cat1+","+cat2+")");
		 List<String> photos = dao.getPhotoMedia(cat1, cat2);
		 log.debug("## <-getPhotoMedia(): " + photos);
		 return photos;	
	 }
	 
	 @RequestMapping(value="/photos/{cat1}/{cat2}/{cat3}",method = RequestMethod.GET,headers="Accept=application/json")
	 public  List<String> getPhotoMedia(@PathVariable String cat1, @PathVariable String cat2, @PathVariable String cat3) 
	 {
		 log.debug("## ->getPhotoMedia("+cat1+","+cat2+","+cat3+")");
		 List<String> photos = dao.getPhotoMedia(cat1, cat2, cat3);
		 log.debug("## <-getPhotoMedia(): " + photos);
		 return photos;	
	 }
	 
	 @RequestMapping(value="/booking",method = RequestMethod.POST)
	 public void addBookingDetails(@RequestBody Booking booking) {	
		 log.debug("## ->addBookingDetails("+booking+")");
		 dao.addBookingDetails( booking );
		 log.debug("## <-addBookingDetails()");
	 }
	 
	 @RequestMapping(value="/academyregistration",method = RequestMethod.POST)
	 public void addAcademyRegistration(@RequestBody Member member) {	
		 log.debug("## ->addAcademyRegistration("+member+")");
		 dao.addMember( member );
		 logMemberDetails(member);
		 log.debug("## <-addAcademyRegistration()");
	 }
	 
	 @RequestMapping(value="/ipn",method = RequestMethod.POST)
	 public void paypalIPNlistener(HttpServletRequest req, HttpServletResponse res) {	
		 log.debug("## ->paypalIPNlistener()");
		 try {
			dao.paypalIPNhandler( req, res );
		} catch (IpnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 log.debug("## <-paypalIPNlistener()");
	 }
	 
	 public void logMemberDetails( Member member )
	 {
		 log.debug("*************************************************");
		 log.debug("***           MEMBER DETAILS                  ***");
		 log.debug("*************************************************");
		 log.debug("**    Name: " + member.getName());
		 log.debug("**    Address: " + member.getAddress());
		 log.debug("**    Phone: " + member.getPhone());
		 log.debug("**    Phone2: " + member.getPhone2());
		 log.debug("**    email: " + member.getEmail());
		 log.debug("**    dob: " + member.getDob());
		 log.debug("**    Payment: " + member.getAmount());
		 log.debug("**    Receipt id: " + member.getReceiptid());
		 log.debug("**    Team: " + member.getTeam());
		 log.debug("**    Team2: " + member.getTeam2());
		 log.debug("**    Team3: " + member.getTeam3());
		 log.debug("**    Position: " + member.getPosition());
		 log.debug("**    Position2: " + member.getPosition2());
		 log.debug("**    Position3: " + member.getPosition3());
		 log.debug("**    LeagueId: " + member.getLid());
		 log.debug("**    Fav team: " + member.getFavteam());
		 log.debug("**    Fav Player: " + member.getFavplayer());
		 log.debug("**    Appearances: " + member.getSappears());
		 log.debug("**    Assists: " + member.getSassists());
		 log.debug("**    Goals: " + member.getSgoals());
		 log.debug("**    Photo Loc: " + member.getPhoto());
		 log.debug("**    Achievements: " + member.getAchievements());
		 log.debug("**    Status: " + member.getStatus());
		 
		 
		 log.debug("*************************************************");
	 }
}
