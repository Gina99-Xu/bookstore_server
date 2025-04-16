package com.bookshop.backend.service;

import com.bookshop.backend.dao.MessageRepository;
import com.bookshop.backend.entity.Message;
import com.bookshop.backend.requestmodels.AdminQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public void postMessage(Message messageRequest, String userEmail) {
		Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
		message.setUserEmail(userEmail);
		messageRepository.save(message);
	}

	public void putMessage(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception {
		Optional<Message> message = messageRepository.findById(adminQuestionRequest.getId());
		if (message.isEmpty()) {
			throw new Error(" no messages found!");
		}

		message.get().setAdminEmail(userEmail);
		message.get().setResponse(adminQuestionRequest.getResponse());
		message.get().setClosed(true);
		messageRepository.save(message.get());
	}
}
