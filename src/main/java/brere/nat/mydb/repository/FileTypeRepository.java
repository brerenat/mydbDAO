package brere.nat.mydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brere.nat.mydb.model.FileType;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {

}
