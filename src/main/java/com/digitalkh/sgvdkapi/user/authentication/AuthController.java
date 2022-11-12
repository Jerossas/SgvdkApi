package com.digitalkh.sgvdkapi.user.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkh.sgvdkapi.user.authentication.signin.SignInRequest;
import com.digitalkh.sgvdkapi.user.authentication.signin.SignInService;
import com.digitalkh.sgvdkapi.user.authentication.signin.refresh_token.RefreshToken;
import com.digitalkh.sgvdkapi.user.authentication.signin.refresh_token.RefreshTokenService;
import com.digitalkh.sgvdkapi.user.authentication.signout.SignOutService;
import com.digitalkh.sgvdkapi.user.authentication.signup.SignUpRequest;
import com.digitalkh.sgvdkapi.user.authentication.signup.SignUpService;
import com.digitalkh.sgvdkapi.user.exception.TokenRefreshException;
import com.digitalkh.sgvdkapi.user.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private SignUpService signUpService;

	@Autowired
	private SignInService signInService;

	@Autowired
	private SignOutService signOutService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
		return signInService.singIn(request);
	}

	@PostMapping("/signup")
	public String signUp(@RequestBody SignUpRequest signUpRequest) {
		return signUpService.signUp(signUpRequest);
	}

	@GetMapping("confirm")
	public String confirmToken(@RequestParam("token") String token) {
		return signUpService.confirmToken(token);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signOut() {
		return signOutService.signOut();
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
		String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

		if ((refreshToken != null) && (refreshToken.length() > 0)) {
			return refreshTokenService.findByToken(refreshToken)
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshToken::getUser)
					.map(user -> {
						ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

						return ResponseEntity.ok()
								.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
								.header(HttpHeaders.SET_COOKIE, refreshToken)
								.body("Token is refreshed successfully!");
					}).orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
		}

		return ResponseEntity.badRequest().body("Refresh Token is empty!");
	}
}
