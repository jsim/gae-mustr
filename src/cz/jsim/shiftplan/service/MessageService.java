package cz.jsim.shiftplan.service;

import java.util.List;

import cz.jsim.shiftplan.domain.Message;

public interface MessageService {

    Message saveMessage(Message message);

    List<Message> getAllMessages();
    
    Message getMessage(String key);

    void remove(String key);
}
