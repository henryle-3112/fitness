package henry.greenwich.fitness.service.training;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.training.Training;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.training.TrainingRepository;
import henry.greenwich.fitness.service.coach.CoachService;
import henry.greenwich.fitness.service.user.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {
    private TrainingRepository trainingRepository;
    private UserProfileService userProfileService;
    private CoachService coachService;

    /**
     * @param trainingRepository - inject trainingRepository
     * @param userProfileService - inject userProfileService
     * @param coachService       - inject coachService
     */
    public TrainingService(TrainingRepository trainingRepository,
                           UserProfileService userProfileService,
                           CoachService coachService) {
        this.trainingRepository = trainingRepository;
        this.userProfileService = userProfileService;
        this.coachService = coachService;
    }

    /**
     * @param coachId       - coach's id that user want to get list of trainings
     *                      (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get list of
     *                      trainings (this parameter could be optional)
     * @param startIndex    - start index (for pagination) (this parameter could be
     *                      optional)
     * @return list of trainings
     */
    public List<Training> getTrainingsPaging(Integer coachId, Integer userProfileId, Integer startIndex) {
        List<Object> trainingsObjectList = this.trainingRepository.getTrainingsPaging(coachId, userProfileId,
                startIndex);
        return this.createTrainingsFromObjectList(trainingsObjectList);
    }

    /**
     * @param coachId       - coach's id that user want to get list of trainings
     *                      (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get list of
     *                      trainings (this parameter could be optional)
     * @return list of trainings
     */
    public List<Training> getTrainings(Integer coachId, Integer userProfileId) {
        List<Object> trainingsObjectList = this.trainingRepository.getTrainings(coachId, userProfileId);
        return this.createTrainingsFromObjectList(trainingsObjectList);
    }

    /**
     * @param trainingDate  - training's date that user want to get list of
     *                      trainings
     * @param userProfileId - user's profile's id that user want to get list of
     *                      trainings
     * @param coachId       - coach's id that user want to get list of trainings
     * @return list of trainings
     */
    public List<Training> getTrainings(Integer userProfileId, Integer coachId, String trainingDate) {
        UserProfile userProfile = this.getUserProfile((long) userProfileId);
        Coach coach = this.getCoach(coachId, null);
        return this.trainingRepository.findTrainingsByTrainingDateAndUserProfileAndCoach(trainingDate, userProfile,
                coach);
    }

    /**
     * @param trainingsObjectList - trainings object list that user want to convert
     *                            to trainings list
     * @return list of trainings
     */
    private List<Training> createTrainingsFromObjectList(List<Object> trainingsObjectList) {
        List<Training> trainings = new ArrayList<>();
        for (Object o : trainingsObjectList) {
            String trainingDate = (String) o;
            Training training = new Training();
            training.setTrainingDate(trainingDate);
            trainings.add(training);
        }
        return trainings;
    }

    /**
     * @param coachId       - coach's id that user want to get number of trainings
     *                      (this parameter could be optional)
     * @param userProfileId - user's profile's id that user want to get number of
     *                      trainings (this parameter could be optional)
     * @return number of trainings
     */
    public int getNumberOfTrainings(Integer coachId, Integer userProfileId) {
        List<Object> nTrainingsObjectList = this.trainingRepository.getNumberOfTrainings(coachId, userProfileId);
        if (nTrainingsObjectList.size() > 0) {
            return Integer.valueOf(nTrainingsObjectList.get(0).toString());
        }
        return 0;
    }

    /**
     * @param trainings - trainings
     * @return list of trainings
     */
    public List<Training> addTrainings(List<Training> trainings) {
        if (trainings.size() > 0) {
            // delete trainings by training date if trainings existed in the database before
            // adding to the database
            String selectedTrainingDate = trainings.get(0).getTrainingDate();
            this.deleteTrainingsByTrainingDate(selectedTrainingDate);
            return this.addTrainingsToServer(trainings);
        }
        return null;
    }

    /**
     * @param training - training that user want to update
     * @return updated training
     */
    public Training updateTraining(Training training) {
        return this.trainingRepository.saveAndFlush(training);
    }

    /**
     * @param trainingDate - training's date that user want to delete all trainings
     */
    private void deleteTrainingsByTrainingDate(String trainingDate) {
        this.trainingRepository.deleteAllTrainingsByTrainingDate(trainingDate);
    }

    /**
     * @param trainings - trainings that user want to add to the database
     * @return inserted trainings
     */
    private List<Training> addTrainingsToServer(List<Training> trainings) {
        int nInsertedTrainings = 0;
        for (Training eachTraining : trainings) {
            this.trainingRepository.saveAndFlush(eachTraining);
            nInsertedTrainings++;
        }
        if (nInsertedTrainings == trainings.size()) {
            return trainings;
        }
        return null;
    }

    /**
     * @param userProfileId - user's profile's id that user want to get selected
     *                      user's profile
     * @return selected user's profile
     */
    private UserProfile getUserProfile(long userProfileId) {
        return this.userProfileService.getUserProfile(userProfileId);
    }

    /**
     * @param coachId     - coach's id that user want to get selected coach
     * @param coachStatus - coach's status that user want to get selected coach
     * @return selected coach
     */
    private Coach getCoach(Integer coachId, Integer coachStatus) {
        return this.coachService.getCoach(coachId, coachStatus);
    }
}
