package management;

public interface db {

    public void delete_row_by_id(String table_name,int id);
    void insert_row_to_dashboard(String table_name,String day,String name_of_subject,String teacher, String classroom);

    void insert_assignments(String table_name,String name_of_subject,String deeadline);

    void print_table_dashboard(String table_name);

    void print_table_assignments(String table_name);

    void delete_row_by_name(String table_name,String name);
}