package cz.jsim.shiftplan.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

import cz.jsim.shiftplan.domain.Message;
import cz.jsim.shiftplan.service.MessageService;

public class MessageServiceImpl implements MessageService {

	public MessageServiceImpl() {
		ObjectifyService.register(Message.class);
	}

	@Override
	public Message saveMessage(final Message message) {
		return ofy().transact(new Work<Message>() {
			@Override
			public Message run() {

				Message saved = ofy().load().key(message.createOfyKey()).now();

				if (saved != null) {
					saved.setText(message.getText());

					ofy().save().entity(saved);
					return saved;

				} else {
					message.setDateCreated(new Date());

					ofy().save().entity(message);
					return message;
				}
			}
		});
	}

	@Override
	public List<Message> getAllMessages() {
		return ofy().load().type(Message.class).list();
	}

	@Override
	public Message getMessage(String key) {
		return ofy().load().key(Message.createOfyKey(key)).now();
	}

	@Override
	public void remove(String key) {
		ofy().delete().key(Message.createOfyKey(key)).now();
	}

}
