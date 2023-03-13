package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.SplittableRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.CommonApiResponseDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepostory;

	@Autowired
	private JavaMailSender javaMailSender;

	// User Registration
	public CommonApiResponseDto saveUser(User user) {
		System.out.println("Inside Userservice, Save user Method is executing");
		try {
			if (user != null) {
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String encPass = bc.encode(user.getPassword());
				user.setPassword(encPass);
				String regx = user.getEmail();
				String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
						+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				Pattern pattern = Pattern.compile(regexPattern);
				Matcher emailCheck = pattern.matcher(regx);
				if (emailCheck.matches()) {
					User userFrmDb = userRepostory.save(user);
					if (userFrmDb != null) {
						return new CommonApiResponseDto(1, "User Registration Successful");
					} else {
						return new CommonApiResponseDto(0, "Fail to Register User");
					}
				} else {
					return new CommonApiResponseDto(0, "Please Enter correct Email");
				}

			} else {
				return new CommonApiResponseDto(0, "Received Invalid Data");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonApiResponseDto(0, "Operation Failed");
		}
	}

	public Optional<User> getDetailsById(String id) {
		return userRepostory.findById(id);
	}
	
//	public String findUserId(String username) {
//		User users = userRepostory.findByUsername(username);
//		return users.getUserId();
//	}
	
	public User findUserId(String username) {
		User users = userRepostory.findByUsername(username);
		return users;
	}
	
	public User findUserDetail(String username) {
		User users = userRepostory.findByUsername(username);
		return users;
	}
	// User LogIn
//	public LoginResponseDto userLogin(User user) {
//		System.out.println("Inside LogIn method!!");
//		try {
//			if (user != null) {
//				User ud = userRepostory.findByUserId(user.getUserId());
//				if (ud != null) {
//					// ud.getPassWord().equals(user.getPassWord())
//					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
//					if (bc.matches(user.getPassword(), ud.getPassword())) {
//						return new LoginResponseDto(1, "Login successful", ud);
//					} else {
//						return new LoginResponseDto(0, "Invalid password", null);
//					}
//				} else {
//					return new LoginResponseDto(0, "User not found", null);
//				}
//			} else {
//				return new LoginResponseDto(0, "Received invalid data", null);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new LoginResponseDto(0, "Operation failed", null);
//		}
//	}

	// User password update
	public String updatePassword(User user) {
		System.out.println("Inside Password update method");
		try {
			if (user != null) {
				User ud = userRepostory.findByUserId(user.getUserId());
				if (ud != null) {
					// ud.getPassWord().equals(user.getPassWord())
					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
					if (bc.matches(user.getPassword(), ud.getPassword())) {
						ud.setEmail(user.getEmail());
						ud.setPassword(user.getPassword());
						User usr = userRepostory.save(ud);
						return "Password reset succssful for user " + usr.getUserId();
					} else {
						return "Invalid password";
					}
				} else {
					return "User Id not found";
				}
			} else {
				return "Invalid data";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Unable to update password";
		}
	}

	// Mail sending
	public void EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	// Random number generator
	public static String otpGenerator(int otpLength) {
		SplittableRandom splittableRandom = new SplittableRandom();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < otpLength; i++) {
			sb.append(splittableRandom.nextInt(0, 10));
		}
		return sb.toString();
	}

	public String forgetPwd(User user) {
		String otp = otpGenerator(6);
		System.out.println("I am inside forgetPwd method");
		User ur = userRepostory.getReferenceById(user.getUserId());
		try {
			if (user.getUserId().matches(ur.getUserId())) {
				if (user.getEmail().matches(ur.getEmail())) {
					System.out.println("Please wait trying to send mail");
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setTo(ur.getEmail());
					mailMessage.setSubject("Password Recovery mail");
					mailMessage.setText("Your new Password is: \n\n" + otp);
					mailMessage.setFrom("austin.smith6970@gmail.com");
					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
					String encPass = bc.encode(otp);
					user.setEmail(ur.getEmail());
					user.setPassword(encPass);
					userRepostory.save(user);
					javaMailSender.send(mailMessage);
					System.out.println("Email sent please check your mail!!!");
					return "Email sent successfully!!!!";
				} else {
					return "Email does not match!!";
				}
			} else {
				return "Id does not match with records!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "opreation failed";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepostory.findByUsername(username);
		//System.out.println("Current user detail "+user);
		// return new User(User.getUserId(), User.getPassword(), new ArrayList<>());

		if (user != null) {
			System.out.println(user);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		}

		else {
			throw new UsernameNotFoundException("user not found");
		}

	}

}
