package olgababeeva.homework.elegion.myapplication;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Olga on 16.10.2015.
 */
public class LeaseDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "olgababeeva.homework.elegion.myapplication.model");

        Entity recipe = schema.addEntity("recipe");
        recipe.addIdProperty();
        recipe.addStringProperty("text");
        recipe.addLongProperty("likes");

        Entity ingredient = schema.addEntity("ingredients");
        ingredient.addIdProperty();
        ingredient.addStringProperty("name");
        ingredient.addStringProperty("recipe_id");

        new DaoGenerator().generateAll(schema,
                "C:\\Users\\Olga\\Downloads\\MaterialViewPager-master\\MyApplication\\app\\src\\main\\java");
}
}
