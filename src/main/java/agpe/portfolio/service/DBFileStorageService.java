package agpe.portfolio.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.portfolio.exception.FileStorageException;
import agpe.portfolio.exception.MyFileNotFoundException;
import agpe.portfolio.modele.Piece;
import agpe.repository.PieceRepository;

@Service
public class DBFileStorageService {

    @Autowired
    private PieceRepository dbFileRepository;

    public Piece storeFile(MultipartFile file,Utilisateur user,Categorie categorie) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Piece dbFile = new Piece(new Date(), file.getName(),file.getContentType(),file.getBytes(), categorie, user);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Piece getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    
    public Collection<Piece> chercherPieceUtilisateurAvecCategorie(String matricule,int idCategorie){
		return dbFileRepository.chercherPieceUtilisateurAvecCategorie(matricule, idCategorie);
    }
    
    public void deletePiece(Piece piece) {
    	dbFileRepository.delete(piece);
    }
    
    public int nbrePiecesUtilisateurCategorie(String matricule,int idCategorie) {
		return dbFileRepository.nbrePiecesUtilisateurCategorie(matricule, idCategorie);
    }
    public int nbrePIeceUtilisateur(String matricule) {
		return dbFileRepository.nbrePiecesUtilisateur(matricule);
    }
}
