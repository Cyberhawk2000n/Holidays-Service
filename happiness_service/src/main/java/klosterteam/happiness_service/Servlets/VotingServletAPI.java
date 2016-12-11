package klosterteam.happiness_service.Servlets;

import klosterteam.happiness_service.Pack;
/**
 * Created by Stanislav on 10.12.2016.
 * stas33553377@yandex.ru
 */
public class VotingServletAPI {

    // returns the voting variants
    public static String[] getVotingOptions(long id){
        String[] res = new String[2];
        res[0]="test gift 1";
        res[1]="test gift 2";
        return res;
    }
    //returns the voting progress
    public static int[] getVotingProgress(long id){
        int[] res = new int[2];
        res[0]=6;
        res[1]=5;
        return res;
    }
    // returns user's preferences + "about me" content
    public static String getPreference(long id){
        String res ="sub_category1, sub_category2 \n About" + "Test name" + "\n ";
        return res;
    }
    // returns gift history
    public static String[] gGetGiftHistory(long id){
        String[] res = new String[1];
        res[0] = "test gift";
        return res;
    }
    //returns list of categories
    public static Pack[] getCategoriesGifts(){
        Pack[] packs = new Pack[2];
        packs[0] = new Pack("scat1", new String[]{"gift1_cat1", "gift2_cat1"});
        packs[0] = new Pack("cat2", new String[]{"gift1_cat2", "gift2_cat2"});
        return packs;
    }

}
