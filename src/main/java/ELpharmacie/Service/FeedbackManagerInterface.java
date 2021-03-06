package ELpharmacie.Service;

import java.util.List;

import ELpharmacie.entities.Feedback;

public interface FeedbackManagerInterface {

	List<Feedback> retrieveAllFeedback();

	Feedback addFeedback(Feedback M);

	void deleteFeedback(Long id);

	void deleteFeedback(String id);

	Feedback updateFeedback(Feedback M);

	Feedback FindFeedback(Long id);

	Feedback FindFeedback(String id);

}
