

/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        //// Replace the following statement with your code
        for(int i = 0; i < userCount; i++ ){
            if (users[i].getName().equals(name)){
            return users[i];
            }
        }    
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        //// Replace the following statement with your code
        if ( userCount >= users.length ) {
            return false;
        }
        if (getUser(name) != null) {
            return false;
        }
        users[userCount] = new User(name);
        userCount++;
        return true;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        //// Replace the following statement with your code
        if (name1 == name2) {
            return false;
        }
        User user1 = getUser(name1);
        User user2 = getUser(name2);
        return user1 != null && user2 != null && user1.addFollowee(name2);
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        //// Replace the following statement with your code
        User targetUser = getUser(name);
        if (targetUser == null) {
            return null; // If the user does not exist, return null
        }
    
        String recommendUser = null;
        int maxMutuals = -1; // Correct spelling of 'maxMutuals'
    
        for (int i = 0; i < this.userCount; i++) {
            User currentUser = this.users[i];
    
            // Skip the target user and users already followed
            if (targetUser.follows(currentUser.getName()) || currentUser.getName().equals(name)) {
                continue;
            }
    
            // Calculate mutual count
            int mutualCount = 0;
            for (int j = 0; j < currentUser.getfCount(); j++) {
                if (targetUser.follows(currentUser.getfFollows()[j])) {
                    mutualCount++;
                }
            }
    
            // Update recommendation if mutualCount is greater
            if (mutualCount > maxMutuals) {
                maxMutuals = mutualCount;
                recommendUser = currentUser.getName();
            }
        }
    
        return recommendUser; // Return the recommended user's name
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        //// Replace the following statement with your code
        String most_user = null;
        int max_folowers = -1;
        for( int i = 0 ; i < this.userCount; i++ ){
            String temp_user = users[i].getName(); 
            int followeeCount = followeeCount(temp_user) ;    
            if (followeeCount> max_folowers) {
                max_folowers = followeeCount;
                most_user = temp_user;
            }

        }
        return most_user;
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        //// Replace the following statement with your code
        int counter = 0 ;
          for(int i = 0 ; i < this.userCount; i++){
            User currentUser = this.users[i];
            
                if (currentUser.follows(name)) {
                    counter++;
                }
            } 
          
        return counter;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       //// Replace the following statement with your code
       StringBuilder result = new StringBuilder("Network:");
    
       for (int i = 0; i < userCount; i++) {
           User user = users[i];
           result.append("\n").append(user.toString());
       }
       
       return result.toString(); 
   }
}
