package com.melvyn.WineManager;

import java.util.Date;

public class Book {
    private String BookId;
    private String Title;
    private String SubTitle;
    private int Pages;
    private Date Published;
    private String Description;

    public Book(String bookId, String title, String subTitle, int pages, Date published, String description) {
        super();
        this.BookId = bookId;
        this.Title = title;
        this.SubTitle = subTitle;
        this.Pages = pages;
        this.Published = published;
        this.Description = description;
    }
    public Date getPublished() { return Published; }
    public int getPages() { return Pages; }
    public String getBookId() { return BookId; }
    public String getDescription() { return Description; }
    public String getSubTitle() { return SubTitle; }
    public String getTitle() { return Title; }

    public void setBookId(String bookId) { this.BookId = bookId; }
    public void setDescription(String description) { this.Description = description; }
    public void setPages(int pages) { this.Pages = pages; }
    public void setPublished(Date published) { this.Published = published; }
    public void setSubTitle(String subTitle) { this.SubTitle = subTitle; }
    public void setTitle(String title) { this.Title = title; }
}
