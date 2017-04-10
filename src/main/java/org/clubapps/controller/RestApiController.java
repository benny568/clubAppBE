package org.clubapps.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.clubapps.dao.RoleDao;
import org.clubapps.model.NewsStory;
import org.clubapps.model.Team;
import org.clubapps.model.User;
import org.clubapps.model.security.UserRole;
import org.clubapps.service.NewsService;
import org.clubapps.service.TeamService;
import org.clubapps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RestApiController {
	java.sql.Timestamp currentTimestamp = null;
	private final Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	public RestApiController(){}
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private TeamService teamService;	
	@Autowired
	private UserService userService;	
	@Autowired
	private RoleDao roleDao;
	
	 @RequestMapping(value="/stories",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<NewsStory> getNewsStories() {
		 log.debug("##                        |-> getNewsStories()..");
		 List<NewsStory> stories = newsService.findAll();
		 log.debug("##                        |<- getNewsStories()..");
		 
		// Get current size of heap in bytes
			long heapSize = Runtime.getRuntime().totalMemory(); 

			// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
			long heapMaxSize = Runtime.getRuntime().maxMemory();

			 // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
			long heapFreeSize = Runtime.getRuntime().freeMemory();
			
			log.debug("*****************************************");
			log.debug("************** heapSize: " + heapSize/1048576 + "Mb **************");
			log.debug("************** heapMaxSize: " + heapMaxSize/1048576 + "Mb **************");
			log.debug("************** heapFreeSize: " + heapFreeSize/1048576 + "Mb **************");
			log.debug("*****************************************");
		 return stories;
	
	 }
	 
	 @RequestMapping(value="/teams",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<Team> getAllTeams() {
		 log.debug("## [RestApiController]->getTeams()..");
		 List<Team> teams = teamService.findAll();
		 return teams;
	
	 }
	 
	 
	 @RequestMapping(value="/user",method = RequestMethod.POST,headers="Accept=application/json")
	public String createUser(@ModelAttribute("user") User user,  Model model) {
		 log.debug("## [RestApiController]->createUser()..");
		 Set<UserRole> userRoles = new HashSet<>();
         userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

         userService.createUser(user, userRoles);
		return "home";
	
	 }
	 
	 @RequestMapping(value="/user",method = RequestMethod.GET,headers="Accept=application/json")
	 public List<User> getAllUsers() {
		 log.debug("## [RestApiController]->getAllUsers()..");
		 List<User> users = userService.findAll();
		 return users;
	
	 }
	 
}
