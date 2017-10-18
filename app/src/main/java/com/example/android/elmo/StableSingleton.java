package com.example.android.elmo;

import java.util.ArrayList;

/**
 * Created by Anasztázia on 2017.10.06..
 */

public class StableSingleton  {

    // Private constructor.
    private static StableSingleton mInstance;

    private ArrayList<Monster> monsterList = null;
    private ArrayList<Food> foodList = null;

    // Public constructor
    public static StableSingleton getInstance() {
        if(mInstance == null)
            mInstance = new StableSingleton();

        return mInstance;
    }

    private StableSingleton() {
        monsterList = new ArrayList<Monster>();
        foodList = new ArrayList<Food>();
        InitFood();
    }

    // Initialize foodArray
    private void InitFood () {
        this.foodList.add(new Food("Cloud", Constants.FOOD_CLOUD, 10));
        this.foodList.add(new Food("Coal" ,Constants.FOOD_COAL, 10));
        this.foodList.add(new Food("Dirt", Constants.FOOD_DIRT, 10));
        this.foodList.add(new Food("Water", Constants.FOOD_WATER, 10));
    }

    // retrieve array from anywhere
    public ArrayList<Monster> getMonsterArray() {
        return this.monsterList;
    }
    public ArrayList<Food> getFoodArray() {
        return this.foodList;
    }

    //Add element to array
    public void addToMonsterArray(Monster monster) {
        monsterList.add(monster);
    }
    public void addToFoodArray(ArrayList<Food> dropList) {
        int dropSize = dropList.size();
        for (int i = 0; i < dropSize; i++)
        {
            addToFoodArray( dropList.get(i));
        }
    }
    public void addToFoodArray (Food food) {
        ArrayList<Food> foodList = StableSingleton.getInstance().getFoodArray();
        int size = foodList.size();
        for (int i = 0; i < size; i++)
        {
            if (food.getElement() == foodList.get(i).getElement()) {
                foodList.get(i).setAmount(foodList.get(i).getAmount() + food.getAmount());
                return;
            }
        }
        foodList.add(food);
    }

    // Remove food amount from FoodArray
    public void DecreaseFoodAmount (int element, int dec) {
        ArrayList<Food> foodList = StableSingleton.getInstance().getFoodArray();
        int size = foodList.size();
        for (int i = 0; i < size; i++)
        {
            if (element == foodList.get(i).getElement()) {
                foodList.get(i).setAmount(foodList.get(i).getAmount() - dec);
                return;
            }
        }
    }


    // Clear the array
    public void clearAllArrays() {
        monsterList.removeAll(this.getMonsterArray());
        foodList.removeAll(this.getFoodArray());
    }
    public void clearMonsterArray() {
        monsterList.removeAll(this.getMonsterArray());
    }
    public void clearFoodArray() {
        foodList.removeAll(this.getFoodArray());
    }
}
