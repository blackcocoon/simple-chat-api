package xyz.jocn.chat.friend;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;
import static xyz.jocn.chat.common.AppConstants.*;
import static xyz.jocn.chat.common.dto.ApiResponseDto.*;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.jocn.chat.common.dto.ApiResponseDto;
import xyz.jocn.chat.friend.dto.FriendDto;
import xyz.jocn.chat.friend.dto.FriendRequestDto;
import xyz.jocn.chat.friend.dto.FriendSearchDto;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping
@RestController
public class FriendController {

	private final String UID = JWT_CLAIM_FIELD_NAME_USER_KEY;
	private final String JSON = MediaType.APPLICATION_JSON_VALUE;

	private final FriendService friendService;

	@PostMapping(value = "/friends", consumes = JSON, produces = JSON)
	public ResponseEntity<ApiResponseDto> addFriend(
		@RequestBody @Valid FriendRequestDto dto,
		@AuthenticationPrincipal(expression = UID) String uid
	) {
		Long id = friendService.addFriend(Long.parseLong(uid), dto);
		URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return created(uri).body(success());
	}

	@GetMapping(value = "/friends/{friendId}", produces = JSON)
	public ResponseEntity<ApiResponseDto> fetchOne(
		@PathVariable Long friendId,
		@AuthenticationPrincipal(expression = UID) String uid
	) {
		return ok(success(friendService.fetchOne(Long.parseLong(uid), friendId)));
	}

	@GetMapping(value = "/friends", produces = JSON)
	public ResponseEntity<ApiResponseDto> fetchFriends(
		@RequestParam(required = false, defaultValue = "false") Boolean hidden,
		@RequestParam(required = false, defaultValue = "false") Boolean favorite,
		@AuthenticationPrincipal(expression = UID) String uid
	) {
		FriendSearchDto dto = new FriendSearchDto();
		dto.setFavorite(favorite);
		dto.setHidden(hidden);
		return ok(success(friendService.fetchFriends(Long.parseLong(uid), dto)));
	}

	@PatchMapping(value = "/friends/{friendId}", consumes = JSON, produces = JSON)
	public ResponseEntity<ApiResponseDto> updateFriend(
		@PathVariable Long friendId,
		@RequestBody @Valid FriendDto dto,
		@AuthenticationPrincipal(expression = UID) String uid
	) {
		return ok(success(friendService.updateFriend(Long.parseLong(uid), friendId, dto)));
	}

	@DeleteMapping(value = "/friends/{friendId}", produces = JSON)
	public ResponseEntity<ApiResponseDto> deleteFriend(
		@PathVariable Long friendId,
		@AuthenticationPrincipal(expression = UID) String uid
	) {
		friendService.deleteFriend(Long.parseLong(uid), friendId);
		return ok(success());
	}

}
