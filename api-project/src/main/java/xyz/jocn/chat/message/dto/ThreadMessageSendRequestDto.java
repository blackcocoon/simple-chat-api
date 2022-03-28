package xyz.jocn.chat.message.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import xyz.jocn.chat.message.enums.ChatMessageType;

@Data
public class ThreadMessageSendRequestDto {

	private ChatMessageType type;
	private List<MultipartFile> files;
	private String message;
}