import { Accommodation } from "./Accommodation";
import { Room } from "./Room";
import { Student } from "./Student";

export class RepartitionResult{

    student:Student;
    roommates:Student[];
    room:Room;
    accommodation:Accommodation;
}
