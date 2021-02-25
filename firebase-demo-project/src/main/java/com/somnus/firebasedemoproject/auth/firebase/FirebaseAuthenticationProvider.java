package com.somnus.firebasedemoproject.auth.firebase;

import com.somnus.firebasedemoproject.auth.exceptions.SomnusDemoException;
import com.somnus.firebasedemoproject.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	public boolean supports(Class<?> authentication) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
		UserDetails userDetails = userService.loadUserByUsername(authenticationToken.getName());
		if (userDetails == null) {
			throw new SomnusDemoException("Firebase user does not exist");
		}

		authenticationToken = new FirebaseAuthenticationToken(userDetails, authentication.getCredentials(),
				userDetails.getAuthorities());

		return authenticationToken;
	}
}
