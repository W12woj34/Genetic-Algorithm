package geneticOperators.selection;

import geneticOperators.Individual;

import java.util.ArrayList;
import java.util.List;


import static constances.AlgorithmParams.TOURNAMENT_SIZE;

public class TournamentSelection extends Selection {

    public TournamentSelection() {
        super();
    }

    @Override
    public Individual makeSelection(List<Individual> population) {

        List<Individual> participants = new ArrayList<>();

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {

            Individual participant;
            do {
                int participantPos = random.nextInt(population.size());
                participant = population.get(participantPos);
            } while (participants.contains(participant));
            participants.add(participant);

        }

        int bestParticipantPos = 0;
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getValue() < participants.get(bestParticipantPos).getValue()) {
                bestParticipantPos = i;
            }
        }

        return participants.get(bestParticipantPos);
    }

}
