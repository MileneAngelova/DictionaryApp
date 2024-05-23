package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAllByAddedBy(Long id);

    List<Word> findAllByAddedByAndLanguage_Name(Long id, LanguageNameEnum languageNameEnum);

//    Optional<Word> findByLanguage_Name(LanguageNameEnum nameEnum);
}
