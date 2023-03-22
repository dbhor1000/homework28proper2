package com.example.homework28proper2.controllers;

import com.example.homework28proper2.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Контроллер для управления файлами баз данных")
public class FilesController {

    private FilesService filesService;

    public FilesController(FilesService filesService){

        this.filesService = filesService;

    }

    @GetMapping("/exportIngredients")
    @Operation(
            summary = "Сохранение списка ингредиентов",
            description = "Загрузить базу данных ингредиентов"
    )
    public ResponseEntity<InputStreamResource> downloadIngredientsFile() throws FileNotFoundException {
        File file = filesService.getDataFileIngredients();
        if(file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"IngredientsLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/exportRecipes")
    @Operation(
            summary = "Сохранение списка ингредиентов",
            description = "Загрузить базу данных ингредиентов"
    )
    public ResponseEntity<InputStreamResource> downloadRecipesFile() throws FileNotFoundException {
        File file = filesService.getDataFileRecipes();
        if(file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //@PostMapping(value = "/uploadIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@Operation(
    //        summary = "Добавить файл списка ингредиентов",
    //        description = "Закачать файл списка ингредиентов на сервер"
    //)
    //public ResponseEntity<Void> uploadIngredientsFile(@RequestParam MultipartFile file){
    //
    //    filesService.cleanDataFileIngredients();
    //    File dataFile = filesService.getDataFileIngredients();
    //    try(FileOutputStream fos = new FileOutputStream(dataFile)) {
    //
    //        IOUtils.copy(file.getInputStream(), fos);
    //        return ResponseEntity.ok().build();
    //
    //    } catch (IOException e) {
    //
    //        e.printStackTrace();
    //    }
    //
    //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //
    //}

    @PostMapping(value = "/uploadIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавить файл списка ингредиентов",
            description = "Закачать файл списка ингредиентов на сервер"
    )
    public ResponseEntity<Void> uploadIngredientsFile(@RequestParam MultipartFile file){

        if (filesService.uploadIngredients(file)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    //@PostMapping(value = "/uploadRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@Operation(
    //        summary = "Добавить файл списка рецептов",
    //        description = "Закачать файл списка рецептов на сервер"
    //)
    //public ResponseEntity<Void> uploadRecipesFile(@RequestParam MultipartFile file){
    //
    //    filesService.cleanDataFileRecipes();
    //    File dataFile = filesService.getDataFileRecipes();
    //    try(FileOutputStream fos = new FileOutputStream(dataFile)) {
    //
    //        IOUtils.copy(file.getInputStream(), fos);
    //        return ResponseEntity.ok().build();
    //
    //    } catch (IOException e) {
    //
    //        e.printStackTrace();
    //    }
    //
    //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //
    //}

    @PostMapping(value = "/uploadRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавить файл списка рецептов",
            description = "Закачать файл списка рецептов на сервер"
    )
    public ResponseEntity<Void> uploadRecipesFile(@RequestParam MultipartFile file){

        if (filesService.uploadRecipes(file)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
