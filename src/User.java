/*TODO: add login code for user
 * 
 */
class User {
    private String userID;
    private String userEmail;
    private String password;

    public User(String userID, String userEmail, String password) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    public void login() {
        System.out.println(userEmail + " logged in.");
    }

    public void logout() {
        System.out.println(userEmail + " logged out.");
    }
}
