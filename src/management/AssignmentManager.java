package management;

public class AssignmentManager {
    private static DbFunctions dbFunctions;

    public AssignmentManager(DbFunctions dbFunctions) {
        AssignmentManager.dbFunctions = dbFunctions;
    }

    public static void insertAssignment(String tableName, String nameOfSubject, String deadline) {
        dbFunctions.insert_assignments(tableName, nameOfSubject, deadline);
    }

    public static void printAssignments(String tableName) {
        dbFunctions.print_table_assignments(tableName);
    }

    public static void deleteAssignmentById(String tableName, int id) {
        dbFunctions.delete_row_by_id(tableName, id);
    }
}
