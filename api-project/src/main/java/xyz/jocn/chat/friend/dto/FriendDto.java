package xyz.jocn.chat.friend.dto;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FriendDto {

	@Size(min = 1, max = 20)
	private String name;

	private Boolean favorite;

	private Boolean hidden;
}
