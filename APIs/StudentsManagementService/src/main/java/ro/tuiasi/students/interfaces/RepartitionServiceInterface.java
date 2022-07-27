package ro.tuiasi.students.interfaces;

import ro.tuiasi.students.models.RegistrationStatus;
import ro.tuiasi.students.models.Room;

import java.util.List;

public interface RepartitionServiceInterface {

    void repartitionAlgorithm(List<Room> roomList);
    RegistrationStatus getRepartitionStatus();
    void changeRepartitionStatus(boolean status);
}
