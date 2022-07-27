
export class Student {
    // student-management-service/accommodations/students/{id}
    id:number;
    firstname:string;
    lastname:string;
    phone_number:string;
    year_grade:number;
    rooms_id:number;

    // student-management-service/accommodations/students/{id}/students_details
    university:string;
    faculty:string;
    specialization:string;
    
    // student-management-service/accommodations/students/{id}/addresses
    city:string;
    county:string;
    country:string;
    street_name:string;
    postal_code:string;
}
