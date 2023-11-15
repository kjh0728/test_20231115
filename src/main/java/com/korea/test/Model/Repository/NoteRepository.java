package com.korea.test.Model.Repository;

import com.korea.test.Model.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
