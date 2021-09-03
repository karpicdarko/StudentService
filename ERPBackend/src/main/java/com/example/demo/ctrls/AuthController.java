package com.example.demo.ctrls;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.ERole;
import com.example.demo.jpa.Registrar;
import com.example.demo.jpa.Role;
import com.example.demo.jpa.Student;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.request.SignupRequestRegistrar;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.jwt.JwtUtilsRegistrar;
import com.example.demo.security.services.RegistrarDetailsImpl;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.service.RoleService;
import com.example.demo.service.StudentService;
import com.example.demo.service.RegistrarService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	StudentService studentService;
	
	@Autowired
	RegistrarService registrarService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	JwtUtilsRegistrar jwtUtilsRegistrar;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	
	@PostMapping("/registrar/signin")
	public ResponseEntity<?> authenticateRegistrar(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtilsRegistrar.generateJwtToken(authentication);
		
		RegistrarDetailsImpl userDetails = (RegistrarDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (studentService.existsByIndexNumber(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (studentService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		
		Student user = new Student(signUpRequest.getAddress(), 
				signUpRequest.getBirth_date(), signUpRequest.getCellphone(), 
				signUpRequest.getCity(), signUpRequest.getEmail(), signUpRequest.getGender(),
				signUpRequest.getHigh_school(), signUpRequest.getUsername(), 
				signUpRequest.getLandline(), signUpRequest.getName(), signUpRequest.getParents_name(), 
				encoder.encode(signUpRequest.getPassword()),signUpRequest.getSurname(), signUpRequest.getSyllabus(), signUpRequest.getEnroll_date());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleService.findByName(ERole.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "registrar":
					Role regisRole = roleService.findByName(ERole.ROLE_REGISTRAR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(regisRole);

					break;
				default:
					Role studentRole = roleService.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(studentRole);
				}
			});
		}

		user.setRole(roles);
		studentService.saveStudent(user);

		return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
	}
	
	@PostMapping("/registrar/signup")
	public ResponseEntity<?> registerRegistrar(@Valid @RequestBody SignupRequestRegistrar signUpRequest) {
//		if (studentService.existsByIndexNumber(signUpRequest.getUsername())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Username is already taken!"));
//		}

		if (registrarService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		
		Registrar user = new Registrar(signUpRequest.getAddress(), 
				signUpRequest.getBirth_date(), signUpRequest.getCellphone(), 
				signUpRequest.getCity(), signUpRequest.getEmail(), signUpRequest.getGender(),
				signUpRequest.getLandline(), signUpRequest.getName(), 
				encoder.encode(signUpRequest.getPassword()),signUpRequest.getSurname());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleService.findByName(ERole.ROLE_REGISTRAR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "registrar":
					Role regisRole = roleService.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(regisRole);

					break;
				default:
					Role studentRole = roleService.findByName(ERole.ROLE_REGISTRAR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(studentRole);
				}
			});
		}

		user.setRole(roles);
		registrarService.saveRegistrar(user);

		return ResponseEntity.ok(new MessageResponse("Registrar registered successfully!"));
	}
}
