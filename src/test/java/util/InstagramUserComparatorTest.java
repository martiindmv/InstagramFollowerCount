package util;

import com.example.InstagramFollowerCount.util.InstagramUserComparator;
import com.example.InstagramFollowerCount.util.UserRelationshipData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class InstagramUserComparatorTest {
    @Spy
    UserRelationshipData userRelationshipData;
    @Spy
    private InstagramUserComparator instagramUserComparator;

    @Test
    @DisplayName("Test the creation of a Map when the size of the list of followers and following is equal.")
    public void findNonFollowingBackUsersEqualFollowingAndFollowersTest() {
        userRelationshipData.setArrayFollowers(loadJSON("followers"));
        userRelationshipData.setArrayFollowing(loadJSON("following"));

        Set<String> notFollowingBack = instagramUserComparator.findNonFollowingBackUsers(userRelationshipData);

        assertTrue(notFollowingBack.isEmpty());
    }

    @Test
    @DisplayName("Test the creation of a Map when the size of the list of followers and following is different.")
    public void findNonFollowingBackUsersDifferentFollowingAndFollowersTest() {
        userRelationshipData.setArrayFollowers(loadJSON("followers_with_one_removed"));
        userRelationshipData.setArrayFollowing(loadJSON("following"));

        Set<String> notFollowingBack = instagramUserComparator.findNonFollowingBackUsers(userRelationshipData);

        assertEquals(1, notFollowingBack.size());
        assertTrue(notFollowingBack.contains("iamazing_kunal"));
        assertFalse(notFollowingBack.contains("kristian_d99"));

    }

    @Test
    @DisplayName("Test the logic of the extraction of the values from the JSON files and putting them in a set.")
    public void extractUsernamesFromJsonArrayTest() {
        JSONArray jsonArray = loadJSON("followers");

        Set<String> followersSet = instagramUserComparator.extractUsernamesFromJsonArray(jsonArray);

        assertEquals(2, followersSet.size());
        assertTrue(followersSet.contains("kristian_d99"));
        assertTrue(followersSet.contains("iamazing_kunal"));

    }

    public JSONArray loadJSON(String arrayName) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/JSON/" + arrayName + ".json")));
            if(arrayName.startsWith("following")) {
                JSONObject jsonObject = new JSONObject(jsonContent);
                return jsonObject.getJSONArray("relationships_following");
            }
            else {
                return new JSONArray(jsonContent);

            }
        } catch (IOException e) {
            System.out.println("Incorrect JSON file name!");
            return null;
        }
    }
}
