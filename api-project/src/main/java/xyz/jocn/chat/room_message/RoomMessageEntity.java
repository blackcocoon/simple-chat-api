package xyz.jocn.chat.room_message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomMessageEntity {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
}