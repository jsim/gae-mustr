package cz.jsim.shiftplan.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

import java.util.Date;

@Entity
@Unindex
@Cache(expirationSeconds = 120)
public class Message {

    @Id
    private String key;

    private String text;

    private Date dateCreated;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
	public String toString() {
		return "Message [text=" + text + ", dateCreated=" + dateCreated + "]";
	}

    public Key<Message> createOfyKey() {
        return createOfyKey(key);
    }

    public static Key<Message> createOfyKey(String key) {
        return Key.create(Message.class, key);
    }
}