package henry.greenwich.fitness.service.training;

import henry.greenwich.fitness.model.coach.Coach;
import henry.greenwich.fitness.model.training.Training;
import henry.greenwich.fitness.model.user.UserProfile;
import henry.greenwich.fitness.repository.training.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainingService {
    /**
     * trainingRepository - interact with training data
     */
    private TrainingRepository trainingRepository;

    /**
     * @param trainingRepository - inject trainingRepository
     */
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    /**
     * @param startIndex    - start's index
     * @param userProfileId - user's profile's id
     * @param coachId       - coach's id
     * @return list of trainings
     */
    public List<Object> findTrainingsByUserProfileIdAndCoachIdAndPage(int coachId, int userProfileId, int startIndex) {
        return this.trainingRepository.findTrainingsByUserProfileIdAndCoachIdAndPage(coachId, userProfileId, startIndex);
    }

    /**
     * @param coachId       - coach's id
     * @param userProfileId - user's profile's id
     * @return number of trainings
     */
    public List<Object> countTrainingsByUserProfileIdAndCoachId(int coachId, int userProfileId) {
        return this.trainingRepository.countTrainingsByUserProfileIdAndCoachId(coachId, userProfileId);
    }

    /**
     * @param trainings - trainings
     * @return list of trainings
     */
    public List<Training> addTrainings(List<Training> trainings) {
        if (trainings.size() > 0) {
            // delete trainings by training date if trainings existed in the database before adding to the database
            this.trainingRepository.deleteAllTrainingsByTrainingDate(trainings.get(0).getTrainingDate());
            int count = 0;
            for (Training eachTraining : trainings) {
                this.trainingRepository.saveAndFlush(eachTraining);
                count++;
            }
            if (count == trainings.size()) {
                return trainings;
            }
        }
        return null;
    }

    /**
     *
     * @param trainingDate - training's date
     * @return list of trainings
     */
    public List<Training> findTrainingsByTrainingDateAndUserProfileAndCoach(String trainingDate, UserProfile userProfile, Coach coach) {
        return this.trainingRepository.findTrainingsByTrainingDateAndUserProfileAndCoach(trainingDate, userProfile, coach);
    }

    /**
     *
     * @param training - training
     * @return updated training
     */
    public Training updateTraining(Training training) {
        return this.trainingRepository.saveAndFlush(training);
    }
}
