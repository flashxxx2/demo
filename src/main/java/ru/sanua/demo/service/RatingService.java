package ru.sanua.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sanua.demo.dto.AverageDto;
import ru.sanua.demo.dto.RatingDto;
import ru.sanua.demo.entity.RatingEntity;
import ru.sanua.demo.entity.StudentEntity;
import ru.sanua.demo.repository.RatingsRepository;
import ru.sanua.demo.repository.StudentsRepository;
import ru.sanua.demo.repository.SubjectsRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingsRepository ratingsRepository;
    private final StudentsRepository studentsRepository;
    private final SubjectsRepository subjectsRepository;

    public RatingService(RatingsRepository ratingsRepository, StudentsRepository studentsRepository, SubjectsRepository subjectsRepository) {
        this.ratingsRepository = ratingsRepository;
        this.studentsRepository = studentsRepository;
        this.subjectsRepository = subjectsRepository;
    }

    public RatingEntity getRatingByRatingId(Integer ratingId) {
        Optional<RatingEntity> optionalRatingEntity = Optional.of(ratingsRepository.findById(ratingId).orElse(new RatingEntity()));
        return optionalRatingEntity.orElseThrow(RuntimeException::new);
    }

    public void saveRating(RatingDto rating) {
        RatingEntity entity = getRatingByRatingId(rating.getId());
        entity.setValue(rating.getValue());
        entity.setSubjectEntity(subjectsRepository.findById(rating.getSubjectId()).get());
        entity.setStudentEntity(studentsRepository.findById(rating.getStudentId()).get());
        ratingsRepository.save(entity);
    }

    @Transactional
    public void removeRatingByRatingId(Integer ratingId) {
        ratingsRepository.deleteById(ratingId);
    }

    public List<AverageDto> getAvarageDtoList() {
        double summ;
        double avrValue;
        List<StudentEntity> listOfStudents = studentsRepository.findAll();
        List<AverageDto> averageDtoList = new ArrayList();
        for (int i = 0; i < listOfStudents.size(); i++) {
            summ = 0;
            AverageDto averageDto = new AverageDto();
            for (int j = 0; j < listOfStudents.get(i).getRatingEntities().size(); j++) {
                summ = summ + listOfStudents.get(i).getRatingEntities().get(j).getValue();
                avrValue = summ / listOfStudents.get(i).getRatingEntities().size();
                avrValue = Math.round(avrValue * 100);
                avrValue = avrValue / 100;
                averageDto.setAvrValue(avrValue);
                averageDto.setId(getRatingByRatingId(j).getId());
                averageDto.setStudentId(listOfStudents.get(i).getId());
                averageDto.setStudentName(listOfStudents.get(i).getName());
            }
            if (!(averageDto.getAvrValue() == null)) {
                averageDtoList.add(averageDto);
            }
        }
        return averageDtoList;
    }

    public List<AverageDto> getBotansList() {
        List<AverageDto> listBotans = getAvarageDtoList();
        listBotans.sort(new Comparator<AverageDto>() {
            @Override
            public int compare(AverageDto o1, AverageDto o2) {
                if (o1.getAvrValue() == o2.getAvrValue()) return 0;
                else if (o1.getAvrValue() > o2.getAvrValue()) return -1;
                else return 1;
            }
        });
        return listBotans.subList(0, 3);
    }

    public List<AverageDto> getLoosersList() {
        List<AverageDto> listLoosers = getAvarageDtoList();
        listLoosers.sort(new Comparator<AverageDto>() {
            @Override
            public int compare(AverageDto o1, AverageDto o2) {
                if (o1.getAvrValue() == o2.getAvrValue()) return 0;
                else if (o1.getAvrValue() > o2.getAvrValue()) return 1;
                else return -1;
            }
        });
        return listLoosers.subList(0, 3);
    }
}


