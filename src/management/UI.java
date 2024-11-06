package management;

import java.util.Scanner;

public class UI {
    private final DbFunctions db;
    private final AssignmentManager assignmentManager;
    public UI() {
        this.db = new DbFunctions();
        this.assignmentManager = new AssignmentManager(db);
    }

    public void menu() {
        Scanner in = new Scanner(System.in);
        boolean menuExit = false;

        while (!menuExit) {
            printMenuOptions();
            int userInput = in.nextInt();

            switch (userInput) {
                case 1:
                    db.print_table_dashboard("dashboard");
                    break;
                case 2:
                    addSubjectToDashboard(in);
                    break;
                case 3:
                    AssignmentManager.printAssignments("Assignments");
                    break;
                case 4:
                    manageAssignments(in);
                    break;
                case 5:
                    deleteLessonFromDashboard(in);
                    break;
                case 6:
                    changeLesson(in);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    menuExit = true;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void printMenuOptions() {
        System.out.println("Choose an option:");
        System.out.println("1. View timetable");
        System.out.println("2. Add subject to dashboard");
        System.out.println("3. View assignment table");
        System.out.println("4. Change assignments");
        System.out.println("5. Delete lesson from dashboard");
        System.out.println("6. Change lesson");
        System.out.println("7. Exit");
    }

    private void addSubjectToDashboard(Scanner in) {
        boolean addMore = true;

        while (addMore) {
            System.out.println("Which day?");
            System.out.println("1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday");

            int choose = in.nextInt();
            String day = null;
            switch (choose) {
                case 1:
                    day = "Monday";
                    break;
                case 2:
                    day = "Tuesday";
                    break;
                case 3:
                    day = "Wednesday";
                    break;
                case 4:
                    day = "Thursday";
                    break;
                case 5:
                    day = "Friday";
                    break;
                default:
                    System.out.println("Invalid input.");
            }

            if (day != null) {
                System.out.println("Subject name:");
                String subName = in.next();
                System.out.println("Teacher name:");
                String teacherName = in.next();
                System.out.println("Classroom:");
                String classroom = in.next();

                db.insert_row_to_dashboard("dashboard", day, subName, teacherName, classroom);

                System.out.println("Add more?\n1. Yes\n2. No");
                addMore = in.nextInt() == 1;
            }
        }
    }

    private void manageAssignments(Scanner in) {
        System.out.println("Do you want to:\n1. Finish an assignment\n2. Add a new assignment");
        int choice = in.nextInt();
        in.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter the ID of the assignment to finish:");
                int index = in.nextInt();
                AssignmentManager.deleteAssignmentById("Assignments", index);
                System.out.println("Assignment deleted");
                break;
            case 2:
                System.out.println("Enter the subject name:");
                String subName = in.nextLine();
                System.out.println("Enter the deadline:");
                String deadline = in.nextLine();
                AssignmentManager.insertAssignment("Assignments", subName, deadline);
                System.out.println("Assignment added");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void deleteLessonFromDashboard(Scanner in) {
        boolean deleteMore = true;

        while (deleteMore) {
            System.out.println("Enter the subject name to delete:");
            String subName = in.next();
            db.delete_row_by_name("dashboard", subName);

            System.out.println("Delete another?\n1. Yes\n2. No");
            deleteMore = in.nextInt() == 1;
        }
    }

    private void changeLesson(Scanner in) {
        System.out.println("Enter the subject name to replace:");
        String subName = in.next();
        db.delete_row_by_name("dashboard", subName);
        System.out.println("Lesson changed");
    }
}