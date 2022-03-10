package xyz.jocn.chat.message.repo.thread_message;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import xyz.jocn.chat.chat_space.repo.room.RoomRepository;

//@Rollback(false)
//@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
class ThreadMessageRepositoryImplTest {

	@Autowired
	EntityManager em;

	@Autowired
	ThreadMessageRepository repo;

	@BeforeEach
	void setUp() {
	}
}