package com.example.cms2;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

//table
@Table(name = "Items")
public class Item extends Model {

    //title column of type String
    @Column(name = "Title")
    public String title;

    //description column of type String
    @Column(name = "Description")
    public String description;

    //category column of type int
    @Column(name = "Category")
    public int category;

    //image column of type String
    @Column(name = "Image")
    public String image;

    //empty constructor
    public Item() {
        super();
    }

    //constructor taking paramters
    public Item(String pTitle, String pDescription, String pImage, int pCategory) {
        super();
        //setting parameters to local variables
        this.title = pTitle;
        this.description = pDescription;
        this.image = pImage;
        this.category = pCategory;
    }

    //method to return category
    public int getCategory() {
        return category;
    }

    //method to return all records from database as per category
    //this returns all records to respective screens
    public static List<Item> getAll(int category) {
        //relecting all from the table where category is the category passed as an argument ordering by ascending titles
        return new Select()
                .from(Item.class)
                .where("Category = ?", category)
                .orderBy("Title ASC")
                .execute();
    }

}