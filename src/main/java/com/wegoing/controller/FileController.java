package com.wegoing.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.DocumentDTO;
import com.wegoing.dto.FileDTO;
import com.wegoing.service.ClubService;
import com.wegoing.service.DocumentService;
import com.wegoing.service.FileService;
import com.wegoing.util.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileController   {
	private final FileService fileService;
	private final ClubService clubService;
	
	public FileController(FileService fileService, ClubService clubService) {
		this.fileService =fileService;
		this.clubService = clubService;
	}
	private String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
	

	


	@GetMapping("/club/{clno}/file")
	public String clubDocumentlist(@PathVariable("clno") int clno,Model model) {
		model.addAttribute("file",fileService.getFileList(clno));

		ClubDTO cdto = clubService.getOne(clno);
		model.addAttribute("club", cdto);

		return "club/files";
	}

	@PostMapping("/club/{clno}/file")
	public String upload(@RequestParam MultipartFile[] uploadfile, Model model,RedirectAttributes ratt
			,@PathVariable("clno") Integer clno,@RequestParam String filedes) throws IllegalStateException, IOException {
		System.out.println(filedes);
		for (MultipartFile file : uploadfile) {
			System.out.println("file = "+file.getResource().getDescription());
			if (!file.isEmpty()) {
				FileDTO dto = FileDTO.builder().fname(file.getOriginalFilename()).clno(clno)
						.uuid(UUID.randomUUID().toString()).fpath("").description(filedes).build();
				
				
				fileService.uploadFile(dto);
				File newFileName = new File(projectPath,dto.getUuid() + "_" + dto.getFname());
				file.transferTo(newFileName);
				
			}
		}
		
		ratt.addFlashAttribute("msg","성공적으로 등록");
		model.addAttribute("file",fileService.getFileList(1));
		

		return "redirect:file";

	}
	@GetMapping("/download")
	public ResponseEntity<Resource> download(@ModelAttribute FileDTO dto)throws
	IOException{
		
		Path path = Paths.get(projectPath+"/"+dto.getUuid()+"_"+dto.getFname());
		String contentType = Files.probeContentType(path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").
				filename(dto.getFname(),StandardCharsets.UTF_8).build());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);

		InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
	
		return new ResponseEntity(resource,headers,HttpStatus.OK);
		
	}

	
	
}
