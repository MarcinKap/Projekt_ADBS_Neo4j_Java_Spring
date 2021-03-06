package guru.springframework.repositories.RealationshipsRepositories;

import guru.springframework.models.Relationships.PersonToMovie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PersonToMovieRelationshipRepository extends Neo4jRepository<PersonToMovie, Long> {

    @Query("MATCH (n:Person)-[z:VIEWED]-(m:Movie) " +
            "WHERE id(m)= {movie_id} AND id(n)= {person_id}" +
            "DELETE z")
    PersonToMovie deleteByPersonIdANDMovieId(@Param("movie_id") Long movie_id, @Param("person_id") Long person_id);

    @Query("MATCH (n:Person)-[z:INTERESTED]-(m:Movie) " +
            "WHERE id(m)= {movie_id} AND id(n)= {person_id}" +
            "DELETE z")
    PersonToMovie deleteInteresingMovieByPersonIdANDMovieId(@Param("movie_id") Long movie_id, @Param("person_id") Long person_id);




    @Query("MATCH (n), (m) " +
            "WHERE id(n)={id_n} AND id(m)={id_m} " +
            "CREATE (n)-[:VIEWED{weight: 2}]->(m)")
    void saveViewedMovie(@Param("id_n") Long person_id, @Param("id_m") Long movie_id);


    @Query("MATCH (n), (m) " +
            "WHERE id(n)={id_n} AND id(m)={id_m} " +
            "CREATE (n)-[:INTERESTED{weight: 1}]->(m)")
    void saveInterestedMovie(@Param("id_n") Long person_id, @Param("id_m") Long movie_id);






}
