package brere.nat.mydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brere.nat.mydb.model.ReferenceData;

@Repository
public interface ReferenceDataRepository extends JpaRepository<ReferenceData, Integer> {

	ReferenceData findWithName(String name);
	
}
