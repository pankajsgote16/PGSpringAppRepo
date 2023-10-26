package com.blogapp.services.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.blogapp.services.FileService;
@Service
public class fileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
		// file Name
		String fileName = multipartFile.getOriginalFilename();

		// random name generate
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

		// fullPath
		String filePath = path + File.separator + fileName1;

		// create folder if does not exists
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}

		// File copy
		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream iStream = new FileInputStream(fullPath);
		return iStream;
	}

}
