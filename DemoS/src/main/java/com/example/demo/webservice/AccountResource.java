package com.example.demo.webservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtil;
import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.UserService;

import lombok.Builder;
import lombok.Data;

@RestController
@CrossOrigin
@RequestMapping("/api/account")
public class AccountResource {
	@Autowired 
	private UserService userService;
	
	@Autowired
	private UserRepository userRp;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/getname")
	public String getName() {
		return "hihi";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest request) throws Exception {
		try {
			UserDetails userDetails = userService.loadUserByUsername(request.getUserName());
			SecurityContextHolder.getContext().getAuthentication();
			if (userDetails.getPassword().equals(request.getPassWord())) {
				String token = jwtUtil.generateToken((CustomUserDetails) userDetails);
				return ResponseEntity.ok(AuthResponse.builder().jwt(token).build());
			}
			throw new UsernameNotFoundException("inavalid username/password");
		} catch (Exception e) {
			throw new Exception("inavalid username/password");
		}
	}

	@GetMapping("/byToken")
	public ResponseEntity<AccountDto> getAccountDetailByToken() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return null;
		}
		String username = auth.getName();
		UserEntity account = userRp.getUserByUserName(username);
		if (account == null) {

			return null;
		}
		AccountDto accDto = AccountDto.builder()
				.id(account.getId())
				.email(account.getEmail())
				.role(account.getRoles().size() > 1 ? "ADMIN" : "USER")
				.build();
		return ResponseEntity.ok(accDto);
	}
}

@Data
class AuthRequest {
	private String userName;
	private String passWord;
}

@Data
@Builder
class AccountDto {
	Integer id;
	String email;
	String role;
}
