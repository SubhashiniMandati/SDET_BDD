package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import java.util.List;
import java.util.Map;

public class DataTableSteps {
    @Given("user enters registration details")
    public void user_enters_registration_details(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);

        List<String> row = data.get(0); // first row

        String firstName = row.get(0);
        String lastName  = row.get(1);
        String email     = row.get(2);
        String phone     = row.get(3);

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phone);
    }

    @Given("user enters registration details in tab")
    public void user_enters_registration_details_in_tab(DataTable dataTable) {

        List<Map<String, String>> users = dataTable.asMaps();

        for (Map<String, String> user : users) {
            System.out.println(user.get("firstName"));
        }
    }

}
