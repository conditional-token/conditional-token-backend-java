package com.matching.bet.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matching.bet.domain.exception.BusinessException;
import com.matching.bet.domain.exception.UserNotFoundException;
import com.matching.bet.domain.model.Grouping;
import com.matching.bet.domain.model.SocialLogins;
import com.matching.bet.domain.model.Users;
import com.matching.bet.domain.repository.SocialLoginsRepository;
import com.matching.bet.domain.repository.UsersRepository;

@Service
public class UserRegistrationService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private SocialLoginsRepository socialLoginsRepository;
	
	@Autowired
	private GroupingResgistrationService groupingResgistrationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;  
	
	@Transactional
    public Users save(Users user) {//se vier o campo media, tem que salvar o login social tambem com dados derivados		
		usersRepository.detach(user);
    	
    	Optional<Users> existingUser = usersRepository.findByUserEmail(user.getUserEmail());
    	
    	if(existingUser.isPresent() && !existingUser.get().equals(user)) {
    		throw new BusinessException(
    				String.format("Já existe um usuário cadastrado com o e-mail %s", user.getUserEmail()));
    	}
    	
    	Users result = null;
    	if(user.isNew()) {
    		user.setCryptedPass(passwordEncoder.encode(user.getCryptedPass()));
        	user.setStatus((short) 1);
			result = usersRepository.save(user);
    		
    	}else {

        	result = usersRepository.save(user);

    	}
    	
    	if(user.getMedia() != null) {
			SocialLogins socialLogin = SocialLogins.from(result);
			socialLogin = socialLoginsRepository.save(socialLogin);
			result.addSocialLogin(socialLogin);
			return result;
		}else if(result.hasSocial()) {
    		List<SocialLogins> socialLogins = result.getSocialLogins(user.getMedia());
    		for (SocialLogins socialLogin : socialLogins) {
    			socialLogin.setHash(passwordEncoder.encode(result.getCryptedPass()));
    			socialLogin = socialLoginsRepository.save(socialLogin);
    			return socialLogin.getUsers();
			}
    	}
    	
    	return result;
    	
    }
	
	@Transactional
	public void updateCryptedPass(Long userId, String actualCyptedPass, String newCyptedPass) {
		updateCryptedPass(userId, actualCyptedPass, newCyptedPass, null);
 
	}
	
	@Transactional
	public void updateCryptedPass(Long userId, String actualCyptedPass, String newCyptedPass, Short media) {
		Users user = seekOrFail(userId);
		
		if(!passwordEncoder.matches(actualCyptedPass, user.getCryptedPass())) {
			throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		user.setCryptedPass(passwordEncoder.encode(newCyptedPass));
		
    	if(media != null && user.hasSocial(media)) {
    		user.setMedia(media);
    		List<SocialLogins> socialLogins = user.getSocialLogins(media);
    		for (SocialLogins socialLogin : socialLogins) {
    			socialLogin.setHash(passwordEncoder.encode(user.getCryptedPass()));
			}
    	}
 
	}
	
	public Users seekOrFail(Long userId) {
		return usersRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Transactional
	public void disassociateGrouping(Long userId, Long groupingId) {
		Users user = seekOrFail(userId);
		Grouping grouping = groupingResgistrationService.seekOrFail(groupingId);
		
		user.removeGroup(grouping);		
	}
	
	@Transactional
	public void associateGrouping(Long userId, Long groupingId) {
		Users user = seekOrFail(userId);
		Grouping grouping = groupingResgistrationService.seekOrFail(groupingId);
		
		user.addGroup(grouping);
	}
	
}
