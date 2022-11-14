package pe.pontificia.proyectorc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.pontificia.proyectorc.service.FileSystemStorageService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final FileSystemStorageService storageService;

    public AssetController(FileSystemStorageService storageService){
        this.storageService=storageService;
    }

    @PostMapping("/upload")
    String upload(@RequestParam MultipartFile file){
        String filename=storageService.store(file);
        return filename;
    }
}
