package xyz.jocn.chat.file;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import xyz.jocn.chat.file.repo.meta.FileMetaRepository;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

	@Mock
	FileMetaRepository fileRepository;
	@InjectMocks
	FileService service;
}