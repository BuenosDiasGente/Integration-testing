package ru.hogwarts.magic_school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.magic_school.model.Avatar;

import java.io.IOException;

public interface AvatarService {


    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long studentId);
}
