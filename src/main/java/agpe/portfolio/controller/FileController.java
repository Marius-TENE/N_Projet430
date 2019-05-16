package agpe.portfolio.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;
import agpe.portfolio.payload.UploadFileResponse;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private AgpeMetier agpeMetier;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    	
    	Optional<Categorie> cat = agpeMetier.retournerCategorie(1);
		Optional<Utilisateur> ut = agpeMetier.chercherUtilisateurAvecLogin("15Y511");
        Piece dbFile = agpeMetier.enregistrerPiece(file,ut.get(),cat.get());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/telechargement/")
                .path(dbFile.getNomPiece())
                .toUriString();

        return new UploadFileResponse(dbFile.getNomPiece(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/telechargement/{nomPiece}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long idPiece) {
        // Load file from database
        Piece dbFile = agpeMetier.chercherPiece(idPiece);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getExtensionPiece()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getNomPiece() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
