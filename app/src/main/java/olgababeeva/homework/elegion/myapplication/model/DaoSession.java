package olgababeeva.homework.elegion.myapplication.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import olgababeeva.homework.elegion.myapplication.model.Recipe;
import olgababeeva.homework.elegion.myapplication.model.Ingredient;
import olgababeeva.homework.elegion.myapplication.model.Relations;

import olgababeeva.homework.elegion.myapplication.model.RecipeDao;
import olgababeeva.homework.elegion.myapplication.model.IngredientDao;
import olgababeeva.homework.elegion.myapplication.model.RelationsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig recipeDaoConfig;
    private final DaoConfig ingredientDaoConfig;
    private final DaoConfig relationsDaoConfig;

    private final RecipeDao recipeDao;
    private final IngredientDao ingredientDao;
    private final RelationsDao relationsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        recipeDaoConfig = daoConfigMap.get(RecipeDao.class).clone();
        recipeDaoConfig.initIdentityScope(type);

        ingredientDaoConfig = daoConfigMap.get(IngredientDao.class).clone();
        ingredientDaoConfig.initIdentityScope(type);

        relationsDaoConfig = daoConfigMap.get(RelationsDao.class).clone();
        relationsDaoConfig.initIdentityScope(type);

        recipeDao = new RecipeDao(recipeDaoConfig, this);
        ingredientDao = new IngredientDao(ingredientDaoConfig, this);
        relationsDao = new RelationsDao(relationsDaoConfig, this);

        registerDao(Recipe.class, recipeDao);
        registerDao(Ingredient.class, ingredientDao);
        registerDao(Relations.class, relationsDao);
    }
    
    public void clear() {
        recipeDaoConfig.getIdentityScope().clear();
        ingredientDaoConfig.getIdentityScope().clear();
        relationsDaoConfig.getIdentityScope().clear();
    }

    public RecipeDao getRecipeDao() {
        return recipeDao;
    }

    public IngredientDao getIngredientDao() {
        return ingredientDao;
    }

    public RelationsDao getRelationsDao() {
        return relationsDao;
    }

}
