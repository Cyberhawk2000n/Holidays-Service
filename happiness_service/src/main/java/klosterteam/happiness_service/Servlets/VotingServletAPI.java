package klosterteam.happiness_service.Servlets;

import java.util.List;
import klosterteam.happiness_service.HappyHibernate;
import klosterteam.happiness_service.Pack;
import klosterteam.hibernate.Events;
import klosterteam.hibernate.Gift_history;
import klosterteam.hibernate.Preferences;
import klosterteam.hibernate.Users;
import klosterteam.hibernate.Vote;
/**
 * Created by Stanislav on 10.12.2016.
 * stas33553377@yandex.ru
 */
public class VotingServletAPI {

    public static boolean voted_flag=false;
    // returns the voting variants
    public static String[] getVotingOptions(long id){
        //String[] res = new String[2];
        //res[0]="test gift 1";
        //res[1]="test gift 2";
        HappyHibernate hHibernate = new HappyHibernate();
        Events event = hHibernate.selectEventById(id);
        List<Vote> list = hHibernate.selectVoteByEvent(event);
        if (list == null)
            return null;
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i).getGiftId().getName();
        return res;
    }
    //returns the voting progress
    public static int[] getVotingProgress(long id){
        //int[] res = new int[2];
        //res[0]=6;
        //res[1]=5;
        HappyHibernate hHibernate = new HappyHibernate();
        Events event = hHibernate.selectEventById(id);
        List<Vote> list = hHibernate.selectVoteByEvent(event);
        if (list == null)
            return null;
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i).getCount();
        return res;
    }
    // returns user's preferences + "about me" content
    public static String getPreference(long id){
        //String res ="sub_category1, sub_category2 \n About" + "Test name" + "\n ";
        HappyHibernate hHibernate = new HappyHibernate();
        Events event = hHibernate.selectEventById(id);
        Users user = event.getUserId();
        List<Preferences> prefs = hHibernate.selectPreferencesByUser(user);
        if (prefs == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (prefs.size() - 1); i++)
            sb.append(prefs.get(i)).append(", ");
        sb.append(prefs.get(prefs.size() - 1)).append("\nAbout \n")
                .append(user.getEmail()).append("\n").append(user.getAbout());
        return sb.toString();
    }
    public static boolean getVotedFlag(){
        return voted_flag;
    }
    public static void Vote(int i){
        voted_flag=true;
    }
    // returns gift history
    public static String[] gGetGiftHistory(long id){
        //String[] res = new String[1];
        //res[0] = "test gift";
        HappyHibernate hHibernate = new HappyHibernate();
        Events event = hHibernate.selectEventById(id);
        Users user = event.getUserId();
        List<Gift_history> list = hHibernate.selectGiftHistoryByUser(user);
        if (list == null)
            return null;
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i).getGiftId().getName();
        return res;
    }
    //returns list of categories
    public static Pack[] getCategoriesGifts(){
        //Pack[] packs = new Pack[2];
        //packs[0] = new Pack("scat1", new String[]{"gift1_cat1", "gift2_cat1"});
        //packs[0] = new Pack("cat2", new String[]{"gift1_cat2", "gift2_cat2"});
        HappyHibernate hHibernate = new HappyHibernate();
        return (Pack[])hHibernate.getCategoriesAndGifts().toArray();
    }

}
