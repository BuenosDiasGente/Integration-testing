package ru.hogwarts.magic_school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.magic_school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {


    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;
    List<Avatar> getAvatar(int pageNumber, int pageSize);

    Avatar findAvatar(Long studentId);
}
