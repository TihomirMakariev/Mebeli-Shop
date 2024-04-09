package bg.tihomir.mebeli.model.dto.view;

public class UserProfileView {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public UserProfileView() {
    }

    public Long getId() {
        return id;
    }

    public UserProfileView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserProfileView setPassword(String password) {
        this.password = password;
        return this;
    }
}
