package com.example.homework28proper2.services;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.dataIngredients.file}")
    private String dataFilesPath;
    @Value("${name.of.dataIngredients.file}")
    private String ingredientsFileName;
    @Value("${name.of.dataRecipes.file}")
    private String recipesFileName;

    @Override
    public boolean saveIngredientsToFile(String json) {

        try {
            Files.writeString(Path.of(dataFilesPath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readIngredientsFromFile() {

        try {
            return Files.readString(Path.of(dataFilesPath, ingredientsFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveRecipesToFile(String json) {

        try {
            Files.writeString(Path.of(dataFilesPath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readRecipesFromFile() {

        try {
            return Files.readString(Path.of(dataFilesPath, recipesFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFileIngredients() {

        return new File(dataFilesPath + "/" + ingredientsFileName);

    }

    @Override
    public File getDataFileRecipes() {

        return new File(dataFilesPath + "/" + recipesFileName);

    }

    @Override
    public boolean cleanDataFileIngredients() {
        try {
            Path path = Path.of(dataFilesPath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cleanDataFileRecipes() {
        try {
            Path path = Path.of(dataFilesPath, recipesFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean uploadIngredients(MultipartFile file) {

        cleanDataFileIngredients();
        File dataFile = getDataFileIngredients();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {

            IOUtils.copy(file.getInputStream(), fos);
            return true;

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean uploadRecipes(MultipartFile file) {

        cleanDataFileRecipes();
        File dataFile = getDataFileRecipes();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {

            IOUtils.copy(file.getInputStream(), fos);
            return true;

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Path addTempFile(String suffix) {

        try {
        return Files.createTempFile(Path.of(dataFilesPath), "tempFile", suffix);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }





}
