package pe.pontificia.proyectorc.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService {

    private final static String STORAGE_LOCATION="mediafiles";

    @PostConstruct
    public void init(){
        try {
            Files.createDirectories(Paths.get(STORAGE_LOCATION));
        }catch (IOException ex){
            throw new RuntimeException("No se pudo crear el almacen de datos " + STORAGE_LOCATION);
        }
    }

    public String store(MultipartFile file){
        String orginalFilename=file.getOriginalFilename();
        String filename= UUID.randomUUID()+"."+ StringUtils.getFilenameExtension(orginalFilename);

        if(file.isEmpty()){
            throw new RuntimeException("No se puede almacenar un Documento vacio" + orginalFilename);
        }

        try {
            InputStream inputStream= file.getInputStream();
            Files.copy(inputStream, Paths.get(STORAGE_LOCATION).resolve(filename),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex){
            throw new RuntimeException("Fallo al almacenar el archivo " +orginalFilename);
        }
        return filename;
    }

    public Resource loadAsResource(String filename){
        try {
            Path path= Paths.get(STORAGE_LOCATION).resolve(filename);
            Resource resource=new UrlResource(path.toUri());
            if(resource.exists()||resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("El documento no ha sido encontrado " +filename);
            }
        }catch (MalformedURLException ex){
            throw new RuntimeException("El documento no ha sido encontrado " +filename);
        }
    }

    public void delete(String filename){
        Path path= Paths.get(STORAGE_LOCATION).resolve(filename);
        try {
            FileSystemUtils.deleteRecursively(path);
        }catch (IOException ex){

        }
    }

}
