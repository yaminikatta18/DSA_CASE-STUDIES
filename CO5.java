import java.util.*;

class Pet {
    int petId;
    String petName;
    String ownerName;
    String petType;
    int age;

    Pet(int petId, String petName, String ownerName, String petType, int age) {
        this.petId = petId;
        this.petName = petName;
        this.ownerName = ownerName;
        this.petType = petType;
        this.age = age;
    }

    void displayPet() {
        System.out.println("Pet ID       : " + petId);
        System.out.println("Pet Name     : " + petName);
        System.out.println("Owner Name   : " + ownerName);
        System.out.println("Pet Type     : " + petType);
        System.out.println("Pet Age      : " + age + " years");
    }
}

class Doctor {
    int doctorId;
    String doctorName;
    String specialization;
    String availableTime;

    Doctor(int doctorId, String doctorName, String specialization, String availableTime) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.availableTime = availableTime;
    }

    void displayDoctor() {
        System.out.println("Doctor ID       : " + doctorId);
        System.out.println("Doctor Name     : " + doctorName);
        System.out.println("Specialization  : " + specialization);
        System.out.println("Available Time  : " + availableTime);
    }
}

class Appointment {
    int appointmentId;
    int petId;
    int doctorId;
    String date;
    String reason;

    Appointment(int appointmentId, int petId, int doctorId, String date, String reason) {
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.doctorId = doctorId;
        this.date = date;
        this.reason = reason;
    }

    void displayAppointment() {
        System.out.println("Appointment ID : " + appointmentId);
        System.out.println("Pet ID         : " + petId);
        System.out.println("Doctor ID      : " + doctorId);
        System.out.println("Date           : " + date);
        System.out.println("Reason         : " + reason);
    }
}

class Vaccination {
    int petId;
    String vaccineName;
    String dueDate;

    Vaccination(int petId, String vaccineName, String dueDate) {
        this.petId = petId;
        this.vaccineName = vaccineName;
        this.dueDate = dueDate;
    }

    void displayVaccination() {
        System.out.println("Pet ID        : " + petId);
        System.out.println("Vaccine Name  : " + vaccineName);
        System.out.println("Due Date      : " + dueDate);
    }
}

class Treatment {
    int petId;
    String treatmentName;
    double cost;

    Treatment(int petId, String treatmentName, double cost) {
        this.petId = petId;
        this.treatmentName = treatmentName;
        this.cost = cost;
    }

    void displayTreatment() {
        System.out.println("Pet ID          : " + petId);
        System.out.println("Treatment Name  : " + treatmentName);
        System.out.println("Cost            : ₹" + cost);
    }
}

public class CO5 {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Pet> pets = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<Vaccination> vaccinations = new ArrayList<>();
    static ArrayList<Treatment> treatments = new ArrayList<>();

    // Add Pet
    static void addPet() {
        System.out.print("Enter Pet ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Pet Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Owner Name: ");
        String owner = sc.nextLine();

        System.out.print("Enter Pet Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Pet Age: ");
        int age = sc.nextInt();

        pets.add(new Pet(id, name, owner, type, age));
        System.out.println("Pet registered successfully.\n");
    }

    // Display Pets
    static void displayPets() {
        if (pets.isEmpty()) {
            System.out.println("No pet records found.\n");
            return;
        }

        System.out.println("\n--- PET DETAILS ---");
        for (Pet p : pets) {
            p.displayPet();
            System.out.println("---------------------------");
        }
    }

    // Add Doctor
    static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Specialization: ");
        String spec = sc.nextLine();

        System.out.print("Enter Available Time: ");
        String time = sc.nextLine();

        doctors.add(new Doctor(id, name, spec, time));
        System.out.println("Doctor added successfully.\n");
    }

    // Display Doctors
    static void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctor records found.\n");
            return;
        }

        System.out.println("\n--- DOCTOR DETAILS ---");
        for (Doctor d : doctors) {
            d.displayDoctor();
            System.out.println("---------------------------");
        }
    }

    // Book Appointment
    static void bookAppointment() {
        System.out.print("Enter Appointment ID: ");
        int appId = sc.nextInt();

        System.out.print("Enter Pet ID: ");
        int petId = sc.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Appointment Date: ");
        String date = sc.nextLine();

        System.out.print("Enter Reason for Visit: ");
        String reason = sc.nextLine();

        appointments.add(new Appointment(appId, petId, doctorId, date, reason));
        System.out.println("Appointment booked successfully.\n");
    }

    // Display Appointments
    static void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointment records found.\n");
            return;
        }

        System.out.println("\n--- APPOINTMENT DETAILS ---");
        for (Appointment a : appointments) {
            a.displayAppointment();
            System.out.println("---------------------------");
        }
    }

    // Add Vaccination
    static void addVaccination() {
        System.out.print("Enter Pet ID: ");
        int petId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Vaccine Name: ");
        String vaccine = sc.nextLine();

        System.out.print("Enter Due Date: ");
        String dueDate = sc.nextLine();

        vaccinations.add(new Vaccination(petId, vaccine, dueDate));
        System.out.println("Vaccination record added successfully.\n");
    }

    // Display Vaccinations
    static void displayVaccinations() {
        if (vaccinations.isEmpty()) {
            System.out.println("No vaccination records found.\n");
            return;
        }

        System.out.println("\n--- VACCINATION DETAILS ---");
        for (Vaccination v : vaccinations) {
            v.displayVaccination();
            System.out.println("---------------------------");
        }
    }

    // Add Treatment
    static void addTreatment() {
        System.out.print("Enter Pet ID: ");
        int petId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Treatment Name: ");
        String treatment = sc.nextLine();

        System.out.print("Enter Treatment Cost: ");
        double cost = sc.nextDouble();

        treatments.add(new Treatment(petId, treatment, cost));
        System.out.println("Treatment record added successfully.\n");
    }

    // Display Treatments
    static void displayTreatments() {
        if (treatments.isEmpty()) {
            System.out.println("No treatment records found.\n");
            return;
        }

        System.out.println("\n--- TREATMENT DETAILS ---");
        for (Treatment t : treatments) {
            t.displayTreatment();
            System.out.println("---------------------------");
        }
    }

    // Generate Bill
    static void generateBill() {
        System.out.print("Enter Pet ID for bill generation: ");
        int petId = sc.nextInt();

        double total = 0;
        boolean found = false;

        System.out.println("\n--- BILL DETAILS ---");
        for (Treatment t : treatments) {
            if (t.petId == petId) {
                System.out.println(t.treatmentName + " - ₹" + t.cost);
                total += t.cost;
                found = true;
            }
        }

        if (!found) {
            System.out.println("No treatment records found for this pet.");
        } else {
            System.out.println("Total Bill = ₹" + total);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("========================================");
            System.out.println(" SMART VETERINARY CLINIC SYSTEM ");
            System.out.println("========================================");
            System.out.println("1. Register Pet");
            System.out.println("2. Display Pets");
            System.out.println("3. Add Doctor");
            System.out.println("4. Display Doctors");
            System.out.println("5. Book Appointment");
            System.out.println("6. Display Appointments");
            System.out.println("7. Add Vaccination Record");
            System.out.println("8. Display Vaccination Records");
            System.out.println("9. Add Treatment Record");
            System.out.println("10. Display Treatment Records");
            System.out.println("11. Generate Bill");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    displayPets();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    displayDoctors();
                    break;
                case 5:
                    bookAppointment();
                    break;
                case 6:
                    displayAppointments();
                    break;
                case 7:
                    addVaccination();
                    break;
                case 8:
                    displayVaccinations();
                    break;
                case 9:
                    addTreatment();
                    break;
                case 10:
                    displayTreatments();
                    break;
                case 11:
                    generateBill();
                    break;
                case 12:
                    System.out.println("Exiting System...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.\n");
            }

        } while (choice != 12);
    }
}
