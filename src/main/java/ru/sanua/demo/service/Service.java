//package ru.sanua.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import ru.sanua.demo.dto.*;
//import ru.sanua.demo.entity.*;
//import ru.sanua.demo.repository.*;
//
//import java.util.*;
//
//
//@org.springframework.stereotype.Service
//
//public class Service {
//    @Autowired
//    private final GroupsRepository groupsRepository;
//    private final RatingsRepository ratingsRepository;
//    private final StudentsRepository studentsRepository;
//    private final TeachersRepository teachersRepository;
//    private final SubjectsRepository subjectsRepository;
//    private final GroupSubjectRepository groupSubjectRepository;
//
//
//    public Service(GroupsRepository groupsRepository, RatingsRepository ratingRepository, StudentsRepository studentsRepository, TeachersRepository teachersRepository, SubjectsRepository subjectRepository, GroupSubjectRepository groupSubjectRepository) {
//        this.groupsRepository = groupsRepository;
//        this.ratingsRepository = ratingRepository;
//        this.studentsRepository = studentsRepository;
//        this.teachersRepository = teachersRepository;
//        this.subjectsRepository = subjectRepository;
//        this.groupSubjectRepository = groupSubjectRepository;
//    }
//    public StudentEntity getByIdOrEmpty(Integer id) {
//        Optional<StudentEntity> optionalStudentsEntity = Optional.of(studentsRepository.findById(id).orElse(new StudentEntity()));
//        return optionalStudentsEntity.orElseThrow(RuntimeException::new);
//    }
//
//    public GroupEntity getByIdGroupsOrEmpty(Integer id) {
//        Optional<GroupEntity> optionalGroupsEntity = Optional.of(groupsRepository.findById(id).orElse(new GroupEntity()));
//        return optionalGroupsEntity.orElseThrow(RuntimeException::new);
//    }
//
//    public TeacherEntity getByIdTeachersOrEmpty(Integer id) {
//        Optional<TeacherEntity> optionalTeachersEntity = Optional.of(teachersRepository.findById(id).orElse(new TeacherEntity()));
//        return optionalTeachersEntity.orElseThrow(RuntimeException::new);
//    }
//
//    public RatingEntity getByIdRatingOrEmpty(Integer id) {
//        Optional<RatingEntity> optionalRatingEntity = Optional.of(ratingsRepository.findById(id).orElse(new RatingEntity()));
//        return optionalRatingEntity.orElseThrow(RuntimeException::new);
//    }
//
//    public SubjectEntity getByIdSubjectOrEmpty(Integer id) {
//        Optional<SubjectEntity> optionalSubjectEntity = Optional.of(subjectsRepository.findById(id).orElse(new SubjectEntity()));
//        return optionalSubjectEntity.orElseThrow(RuntimeException::new);
//    }
//
//    public GroupSubjectEntity getByIdGroupSubjectOrEmpty(Integer id) {
//        Optional<GroupSubjectEntity> optionalGroupSubjectEntity = Optional.of(groupSubjectRepository.findById(id).orElse(new GroupSubjectEntity()));
//        return optionalGroupSubjectEntity.orElseThrow(RuntimeException::new);
//    }
//
//    public List<StudentEntity> findAllStudents() {
//        return studentsRepository.findAll();
//
//    }
//
//    public List<SubjectEntity> findAllSubjects() {
//        return subjectsRepository.findAll();
//    }
//
//    public List<RatingEntity> findAllRatings() {
//        return ratingsRepository.findAll();
//    }
//
//    public List<GroupEntity> findAllGroups() {
//        return groupsRepository.findAll();
//
//    }
//
//    public List<TeacherEntity> findAllTeacher() {
//        return teachersRepository.findAll();
//
//    }
//
//    public List<GroupSubjectEntity> findAllGroupSubject() {
//        return groupSubjectRepository.findAll();
//    }
//
//    public void saveStudent(StudentDto student) {
//        StudentEntity entity = getByIdOrEmpty(student.getId());
//        entity.setName(student.getName());
//        entity.setDateOfBirth(student.getDateOfBirth());
//        entity.setGroupEntity(groupsRepository.findById(student.getGroupId()).get());
//        entity.setId(student.getId());
//
//        studentsRepository.save(entity);
//
//    }
//
//    public void saveTeacher(TeacherDto teacher) {
//        TeacherEntity entity = getByIdTeachersOrEmpty(teacher.getId());
//        entity.setName(teacher.getName());
//        entity.setSubjectEntity(subjectsRepository.findById(teacher.getSubjectId()).get());
//        entity.setGroupId(teacher.getGroupId());
//
//        teachersRepository.save(entity);
//    }
//
//    public void saveRaiting(RatingDto rating) {
//        RatingEntity entity = getByIdRatingOrEmpty(rating.getId());
//        entity.setValue(rating.getValue());
//        entity.setSubjectEntity(subjectsRepository.findById(rating.getSubjectId()).get());
//        entity.setStudentEntity(studentsRepository.findById(rating.getStudentId()).get());
//        ratingsRepository.save(entity);
//    }
//
//    public void saveGroup(GroupDto group) {
//        GroupEntity entity = getByIdGroupsOrEmpty(group.getId());
//        entity.setNumber(group.getNumber());
//
//        groupsRepository.save(entity);
//    }
//
//    public void saveGroupSubject(GroupSubjectDto groupSubjectDto) {
//        GroupSubjectEntity entity = getByIdGroupSubjectOrEmpty(groupSubjectDto.getId());
//        entity.setGroupEntity(groupsRepository.findById(groupSubjectDto.getGroupId()).get());
//        entity.setSubjectEntity(subjectsRepository.findById(groupSubjectDto.getSubjectId()).get());
//        groupSubjectRepository.save(entity);
//    }
//
//    @Transactional
//    public void removeStudentById(Integer id) {
//
//        List<RatingEntity> allRatings = findAllRatings();
//        for (int i = 0; i < allRatings.size(); i++) {
//            StudentEntity student = allRatings.get(i).getStudentEntity();
//
//            if (!(student == null) && student.getId().equals(id)) {
//                allRatings.get(i).setStudentEntity(null);
//            }
//
//        }
//
//        studentsRepository.deleteById(id);
//    }
//
//
//    @Transactional
//    public void removeTeacherById(Integer id) {
//        teachersRepository.deleteById(id);
//
//    }
//
//    @Transactional
//    public void removeGroupById(Integer id) {
//        List<StudentEntity> allStudents = findAllStudents();
//        List<GroupSubjectEntity> allGroupSubject = findAllGroupSubject();
//        for (int i = 0; i < allStudents.size(); i++) {
//            GroupEntity group = allStudents.get(i).getGroupEntity();
//
//            if (!(group == (null)) && group.getId().equals(id)) {
//                allStudents.
//                        get(i).
//                        setGroupEntity(null);
//            }
//        }
//        for (int j = 0; j < allGroupSubject.size(); j++) {
//            GroupEntity groupEntity = allGroupSubject.get(j).getGroupEntity();
//
//            if (!(groupEntity == (null)) && groupEntity.getId().equals(id)) {
//                allGroupSubject.
//                        get(j).
//                        setGroupEntity(null);
//
//            }
//        }
//        groupsRepository.deleteById(id);
//    }
//
//    @Transactional
//    public void removeRatingById(Integer id) {
//        ratingsRepository.deleteById(id);
//    }
//
//    public List<AverageDto> getListAvarageDto() {
//        double summ;
//        double avrValue;
//        List<StudentEntity> listOfStudents = studentsRepository.findAll();
//        List<AverageDto> averageDtoList = new ArrayList();
//        for (int i = 0; i < listOfStudents.size(); i++) {
//            summ = 0;
//            AverageDto averageDto = new AverageDto();
//            for (int j = 0; j < listOfStudents.get(i).getRatingEntities().size(); j++) {
//                summ = summ + listOfStudents.get(i).getRatingEntities().get(j).getValue();
//                avrValue = summ / listOfStudents.get(i).getRatingEntities().size();
//                avrValue = Math.round(avrValue * 100);
//                avrValue = avrValue / 100;
//                averageDto.setAvrValue(avrValue);
//                averageDto.setId(getByIdRatingOrEmpty(j).getId());
//                averageDto.setStudentId(listOfStudents.get(i).getId());
//                averageDto.setStudentName(listOfStudents.get(i).getName());
//            }
//            if (!(averageDto.getAvrValue() == null)) {
//                averageDtoList.add(averageDto);
//            }
//        }
//        return averageDtoList;
//    }
//
//    public List<GroupSubjectEntity> getListSubjectEntityByIdGroup(Integer id) {
//        List<GroupSubjectEntity> allGroupSubject = findAllGroupSubject();
//        List<GroupSubjectEntity> groupSubjectEntities = new ArrayList<>();
//        for (int i = 0; i < allGroupSubject.size(); i++) {
//            GroupSubjectEntity groupSubjectEntity = allGroupSubject.get(i);
//            if (!(groupSubjectEntity.getGroupEntity() == (null)) && groupSubjectEntity.getGroupEntity().getId().equals(id)) {
//
//                groupSubjectEntities.add(groupSubjectEntity);
//
//            }
//        }
//        return groupSubjectEntities;
//    }
//
//
//    public List<AverageDto> getListBotans() {
//        List<AverageDto> listBotans = getListAvarageDto();
//        listBotans.sort(new Comparator<AverageDto>() {
//            @Override
//            public int compare(AverageDto o1, AverageDto o2) {
//                if (o1.getAvrValue() == o2.getAvrValue()) return 0;
//                else if (o1.getAvrValue() > o2.getAvrValue()) return -1;
//                else return 1;
//            }
//
//
//        });
//        return listBotans.subList(0, 3);
//    }
//
//    public List<AverageDto> getListLoosers() {
//        List<AverageDto> listLoosers = getListAvarageDto();
//        listLoosers.sort(new Comparator<AverageDto>() {
//            @Override
//            public int compare(AverageDto o1, AverageDto o2) {
//                if (o1.getAvrValue() == o2.getAvrValue()) return 0;
//                else if (o1.getAvrValue() > o2.getAvrValue()) return 1;
//                else return -1;
//            }
//
//
//        });
//        return listLoosers.subList(0, 3);
//    }
//}

